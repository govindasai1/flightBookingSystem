package com.example.database

import com.example.tables.BookingTable
import com.example.tables.FlightsTable
import com.example.tables.PassengersTable
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val url = "jdbc:postgresql://localhost:8080/FlightBooking"
        val driver = "org.postgresql.Driver"
        val userName = "postgres"
        val password = "root"

        Database.connect(url, driver, userName, password)

        transaction { SchemaUtils.createMissingTablesAndColumns(FlightsTable, PassengersTable, BookingTable) }
    }

    suspend fun <T> dbQuery(block: () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) {
            block()
        }
}