package com.uzabase.playtest.db

import org.dbunit.JdbcDatabaseTester
import org.dbunit.database.DatabaseConfig
import org.dbunit.database.IDatabaseConnection
import org.dbunit.dataset.csv.CsvDataSet
import org.dbunit.operation.DatabaseOperation
import java.io.File


data class Database(
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

    fun connection(function: (connection: IDatabaseConnection) -> Unit) {
        val con: IDatabaseConnection =
            JdbcDatabaseTester(driverClass, url, username, password, schema).connection

        kotlin.runCatching { function.invoke(setProperty(con)) }
            .onFailure { it.printStackTrace() }
            .also { con.close() }
    }

    fun setProperty(connection: IDatabaseConnection): IDatabaseConnection {
        connection.config.setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true)
        return connection
    }
}
