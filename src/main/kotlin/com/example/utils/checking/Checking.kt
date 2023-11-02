package com.example.utils.checking

import com.example.database.DatabaseFactory
import com.example.models.Flight
import com.example.tables.FlightsTable
import com.example.tables.PassengersTable
import com.example.utils.methods.rowToFlight
import com.example.utils.methods.rowToFlightId
import com.example.utils.methods.rowToPassengerId
import org.jetbrains.exposed.sql.select

suspend fun checkingPassPresence(passName: String): Boolean {
    val c = DatabaseFactory.dbQuery { PassengersTable.select { PassengersTable.name.eq(passName) }.map { it } }
        .singleOrNull()
    return c != null
}

suspend fun checkingFlight(flightNumber: String): Boolean {
    val c = DatabaseFactory.dbQuery { FlightsTable.select { FlightsTable.flightNumber.eq(flightNumber) }.map { it } }
        .singleOrNull()
    return c != null
}

suspend fun gettingFlightId(flightNumber: String): Int? {
    return DatabaseFactory.dbQuery {
        FlightsTable.select { FlightsTable.flightNumber.eq(flightNumber) }.map { rowToFlightId(it) }
    }.singleOrNull()
}

suspend fun gettingPassengerId(passengerName: String): Int? {
    return DatabaseFactory.dbQuery {
        PassengersTable.select { PassengersTable.name.eq(passengerName) }.map { rowToPassengerId(it) }
    }.singleOrNull()
}

suspend fun gettingFlightById(flightId: Int): Flight {
    return DatabaseFactory.dbQuery { FlightsTable.select { FlightsTable.id.eq(flightId) }.map { rowToFlight(it) } }
        .single()
}