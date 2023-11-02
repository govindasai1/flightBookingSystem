package com.example.utils.methods

import com.example.models.Flight
import com.example.models.Passenger
import com.example.tables.BookingTable
import com.example.tables.FlightsTable
import com.example.tables.PassengersTable
import org.jetbrains.exposed.sql.ResultRow
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun rowToPassengerId(row: ResultRow): Int {
    return row[PassengersTable.id].value
}

fun rowToPassenger(row: ResultRow): Passenger {
    return Passenger(row[PassengersTable.id].value, row[PassengersTable.name])
}

fun rowToFlightId(row: ResultRow): Int {
    return row[FlightsTable.id].value
}

fun rowToFlight(row: ResultRow): Flight {
    return Flight(
        row[FlightsTable.flightNumber],
        row[FlightsTable.sources],
        row[FlightsTable.destination],
        row[FlightsTable.departureTime],
        row[FlightsTable.arriveTime]
    )
}

fun timeTaken(departureTime: String, arrivalTime: String): Float {
    val startTime = LocalTime.parse(departureTime, DateTimeFormatter.ofPattern("HH:mm")).toSecondOfDay()
    println(startTime)
    val endTime = LocalTime.parse(arrivalTime, DateTimeFormatter.ofPattern("HH:mm")).toSecondOfDay()
    return ((endTime - startTime) / 3600f)
}

fun rowToBookingFlightId(row: ResultRow): Int {
    return row[BookingTable.flightId].value
}
