package com.uzabase.playtest.db

import org.dbunit.JdbcDatabaseTester
import org.dbunit.database.DatabaseConfig
import org.dbunit.database.IDatabaseConnection
import org.dbunit.dataset.csv.CsvDataSet
import org.dbunit.ext.mysql.MySqlDataTypeFactory
import org.dbunit.ext.mysql.MySqlMetadataHandler
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory
import org.dbunit.operation.DatabaseOperation
import java.io.File


open class Database(
    private val driverClass: String,
    private val url: String,
    private val username: String,
    private val password: String,
    private val schema: String
) {
    fun cleanInsert(csvFile: File, emptyToNull: Boolean = false, replaceWith: Map<String, String> = mapOf()) =
        connection { conn ->
            val csvDataSet = CsvDataSet(csvFile)
            val dataSet = csvDataSet.takeIf { emptyToNull }?.emptyToNull() ?: csvDataSet
            DatabaseOperation.CLEAN_INSERT.execute(conn, dataSet.replace(replaceWith))
        }

    private fun connection(function: (connection: IDatabaseConnection) -> Unit) {
        val con: IDatabaseConnection =
            JdbcDatabaseTester(driverClass, url, username, password, schema).connection
        kotlin.runCatching { function.invoke(setConfig(con)) }
            .onFailure { it.printStackTrace() }
            .also { con.close() }
    }

    open fun setConfig(connection: IDatabaseConnection): IDatabaseConnection {
        connection.config.setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true)
        if (this.driverClass == "org.postgresql.Driver") {
            connection.config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, PostgresqlDataTypeFactory())
        }
        if (this.driverClass == "com.mysql.jdbc.Driver") {
            connection.config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, MySqlDataTypeFactory())
            connection.config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, MySqlMetadataHandler())
        }
        return connection
    }
}
