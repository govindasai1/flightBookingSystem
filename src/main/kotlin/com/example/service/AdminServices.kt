package com.example.service

import com.example.dao.AdminDao
import com.example.exceptions.DuplicateFlightException
import com.example.exceptions.DuplicatePassengerException
import com.example.exceptions.FlightNotFoundException
import com.example.exceptions.PassengersNotFoundException
import com.example.models.Flight
import com.example.models.Message
import com.example.models.Passenger
import com.example.models.PassengerName
import com.example.utils.responces.failureFlight
import io.ktor.http.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AdminServices : KoinComponent {
    private val adminObject by inject<AdminDao>()

    suspend fun addingFlights(flight: Flight): Message {
        try {
            return adminObject.addFlights(flight)
        } catch (e: Exception) {

            throw DuplicateFlightException("DUPLICATE FLIGHT CANT BE ADDED", HttpStatusCode.NotAcceptable)
        }
    }

    suspend fun removingFlights(flightNumber: String): Message {
            return adminObject.removeFlights(flightNumber)
    }

    suspend fun addingPassengers(passenger: PassengerName): Message {
        try {
            return adminObject.addPassenger(passenger)
        } catch (e: Exception) {
            throw DuplicatePassengerException("CANT ADD DUPLICATE PASSENGER", HttpStatusCode.NotAcceptable)
        }
    }

    suspend fun removingPassengers(passengerName: PassengerName): Message {
        return adminObject.removePassenger(passengerName)
    }

    suspend fun gettingAllPass(): Array<Passenger> {
        try {
            return adminObject.allPassengers()
        } catch (e: Exception) {
            throw PassengersNotFoundException("PASSENGERS NOT FOUND", HttpStatusCode.NotFound)
        }
    }

    suspend fun gettingAllFlights(): Array<Flight> {
        try {
            return adminObject.allFlights()
        } catch (e: Exception) {
            throw FlightNotFoundException(failureFlight.text, HttpStatusCode.NotFound)
        }
    }
}