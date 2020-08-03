package com.dextto.config

import com.dextto.infra.Users
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction

object DbSettings {
    fun init() {
        val dbUser = System.getenv("DB_USER")?: "root"
        val dbPassword = System.getenv("DB_PASSWORD")?: "root"

        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:mysql://localhost/test"
            driverClassName = "com.mysql.jdbc.Driver"
            username = dbUser
            password = dbPassword
            maximumPoolSize = 10
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)

        transaction {
            create(Users)
        }
    }
}

suspend fun <T> query(block: () -> T): T = withContext(Dispatchers.IO) {
    transaction {
        block()
    }
}

