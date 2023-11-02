package com.example.service

import com.example.dao.AdminDao
import com.example.models.Flight
import com.example.models.Message
import com.example.models.Passenger
import com.example.models.PassengerName
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AdminServices : KoinComponent {
    private val adminObject by inject<AdminDao>()

    suspend fun addingFlights(flight: Flight): Message {
        return adminObject.addFlights(flight)
    }

    suspend fun removingFlights(flightNumber: String): Message {
        return adminObject.removeFlights(flightNumber)
    }

    suspend fun addingPassengers(passenger: PassengerName): Message {
        return adminObject.addPassenger(passenger)
    }

    suspend fun removingPassengers(passengerName: PassengerName): Message {
        return adminObject.removePassenger(passengerName)
    }

    suspend fun gettingAllPass(): Array<Passenger> {
        return adminObject.allPassengers()
    }

    suspend fun gettingAllFlights(): Array<Flight> {
        return adminObject.allFlights()
    }
}