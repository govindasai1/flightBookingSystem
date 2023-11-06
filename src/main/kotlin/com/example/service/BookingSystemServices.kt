package com.example.service

import com.example.dao.BookingSystemDao
import com.example.exceptions.DatabaseException
import com.example.exceptions.FlightNotFoundException
import com.example.exceptions.PassengersNotFound
import com.example.models.Flight
import com.example.models.FlightDet
import com.example.models.Message
import com.example.models.Passenger
import com.example.utils.responces.failureFlight
import com.example.utils.responces.failurePassenger
import io.ktor.http.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BookingSystemServices:KoinComponent {
    private val bookingObject by inject<BookingSystemDao>()

    suspend fun bookFlight(passName:String,flightNumber:String):Message {
        try {
            return bookingObject.bookingFlight(passName, flightNumber)
        }catch (e:Exception){
            throw DatabaseException("CONFLICT OCCURRED AT DATABASE", HttpStatusCode.InternalServerError)
        }
    }

    suspend fun cancelFlight(passName:String,flightNumber:String):Message{
        return bookingObject.cancelFlight(passName, flightNumber)
    }

    suspend fun getBookedFlights(passName:String):ArrayList<Flight> {
        try {
            return bookingObject.getBookedFlight(passName)
        }catch (e:Exception){
            throw FlightNotFoundException("FLIGHTS NOT FOUND IN BOOKINGS",HttpStatusCode.NotFound)
        }
    }

    suspend fun getTotalTime(passName:String):Message {
        try {
            return bookingObject.getTotalTravelTime(passName)
        }catch (e:Exception){
            throw PassengersNotFound(failurePassenger.text,HttpStatusCode.NotFound)
        }
    }

    suspend fun searchFlight(flightDet: FlightDet):Array<Flight> {
        try {
            return bookingObject.searchFlight(flightDet)
        }catch (e:Exception){
            throw FlightNotFoundException(failureFlight.text,HttpStatusCode.NotFound)
        }
    }

    suspend fun passengerDetails(passName:String):Passenger?{
        return bookingObject.passDetails(passName)
    }
}