package com.example.dao

import com.example.models.Flight
import com.example.models.Message
import com.example.models.Passenger
import com.example.models.PassengerName

interface AdminDao {
    suspend fun addFlights(flight: Flight): Message
    suspend fun removeFlights(flightNumber: String): Message
    suspend fun addPassenger(passenger: PassengerName): Message
    suspend fun removePassenger(passengerName: PassengerName): Message
    suspend fun allPassengers(): Array<Passenger>
    suspend fun allFlights(): Array<Flight>
}