package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Flight(
    val flightNumber: String,
    val source: String,
    val destination: String,
    val departureTime: String,
    val arrivalTime: String
)

@Serializable
data class Passenger(val id: Int, val name: String)

@Serializable
data class FlightDet(var source: String, var destination: String, var departureTime: String)

@Serializable
data class Message(val text: String)

@Serializable
data class PassNameFlightNo(val passName: String, val flightNumber: String)

@Serializable
data class PassengerName(val name: String)

@Serializable
data class FlightNumber(val number: String)
