package com.example.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object FlightsTable : IntIdTable("FlightsTable") {
    val flightNumber = varchar("FlightNumber", 50).uniqueIndex()
    val sources = varchar("Source", 50)
    val destination = varchar("Destination", 50)
    val departureTime = varchar("DepartureTime", 50)
    val arriveTime = varchar("ArriveTime", 50)
}