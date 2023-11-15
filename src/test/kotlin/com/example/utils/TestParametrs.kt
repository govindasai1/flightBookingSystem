@file:Suppress("UNUSED_VARIABLE")

package com.example.utils

import com.example.models.*
import com.example.tables.BookingTable
import com.example.tables.FlightsTable
import com.example.tables.PassengersTable
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.insertAndGetId

@Serializable
val flight = Flight("123456", "chni", "dlhi", "10:00", "15:30")

@Serializable
val passenger = PassengerName("govindasai0")

val passenger1 = PassengerName("sharath")

@Serializable
val flightNumber = FlightNumber("123567")

@Serializable
val flight2 = Flight("123567", "chni", "goa", "01:00", "13:30")

@Serializable
val flightDet = FlightDet("chni", "goa", "01:00")

@Serializable
val passNameFlightNo = PassNameFlightNo("govindasai0", "123567")


object TestMock{
    fun init(){
        val flightId = FlightsTable.insertAndGetId {
            it[flightNumber] = flight2.flightNumber
            it[sources] = flight2.source
            it[destination] = flight2.destination
            it[departureTime] = flight2.departureTime
            it[arriveTime] = flight2.arrivalTime
        }.value
        val userId = PassengersTable.insertAndGetId {
            it[name] = passenger.name
        }.value
        val bookingId = BookingTable.insertAndGetId {
            it[passengerId] = userId
            it[this.flightId] = flightId
        }.value
    }
}
