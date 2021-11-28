package com.uzabase.playtest.db

import org.dbunit.JdbcDatabaseTester
import org.dbunit.database.DatabaseConfig
import org.dbunit.database.IDatabaseConnection
import org.dbunit.dataset.IDataSet
import org.dbunit.dataset.ReplacementDataSet
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
    fun cleanInsert(csvDir: File, emptyToNull: Boolean = false, replaceWith: Map<String, String> = mapOf()) =
        connection { conn ->
            convertDataset(CsvDataSet(csvDir), emptyToNull, replaceWith)
                .let { DatabaseOperation.CLEAN_INSERT.execute(conn, it) }
        }

    fun insert(csvDir: File, emptyToNull: Boolean = false, replaceWith: Map<String, String> = mapOf()) =
        connection { conn ->
            convertDataset(CsvDataSet(csvDir), emptyToNull, replaceWith)
                .let { DatabaseOperation.INSERT.execute(conn, it) }
        }

    fun insert(entity: Entity) = connection { conn ->
        DatabaseOperation.INSERT.execute(conn, entity.toDataSet())
    }

    fun insert(dataset: IDataSet) = connection { conn ->
        DatabaseOperation.INSERT.execute(conn, dataset)
    }

    fun delete(entity: Entity) = connection { conn ->
        DatabaseOperation.DELETE.execute(conn, entity.toDataSet())
    }

    fun delete(dataset: IDataSet) = connection { conn ->
        DatabaseOperation.DELETE.execute(conn, dataset)
    }

    private fun convertDataset(
        dataset: IDataSet,
        emptyToNull: Boolean,
        replaceWith: Map<String, String>
    ): ReplacementDataSet = (dataset.takeIf { emptyToNull }?.emptyToNull() ?: dataset).replace(replaceWith)

    fun connection(function: (connection: IDatabaseConnection) -> Unit) {
        val con: IDatabaseConnection =
            JdbcDatabaseTester(driverClass, url, username, password, schema).connection
        kotlin.runCatching { function.invoke(setConfig(con)) }
            .also { con.close() }
            .onFailure { throw it }
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

    fun truncate(vararg tableNames: String) = connection { conn ->
        DatabaseOperation.DELETE_ALL.execute(conn, conn.createDataSet(tableNames))
    }
}
