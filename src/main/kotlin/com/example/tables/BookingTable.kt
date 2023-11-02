package com.example.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object BookingTable : IntIdTable("BookingTable") {
    val passengerId = reference("PassengerId", PassengersTable.id)
    val flightId = reference("FlightId", FlightsTable.id)
}