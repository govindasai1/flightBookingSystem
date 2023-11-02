package com.example.service

import com.example.dao.BookingSystemDao
import com.example.models.Flight
import com.example.models.FlightDet
import com.example.models.Message
import com.example.models.Passenger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BookingSystemServices:KoinComponent {
    private val bookingObject by inject<BookingSystemDao>()

    suspend fun bookFlight(passName:String,flightNumber:String):Message{
        return bookingObject.bookingFlight(passName,flightNumber)
    }

    suspend fun cancelFlight(passName:String,flightNumber:String):Message{
        return bookingObject.cancelFlight(passName, flightNumber)
    }

    suspend fun getBookedFlights(passName:String):ArrayList<Flight>{
        return bookingObject.getBookedFlight(passName)
    }

    suspend fun getTotalTime(passName:String):Message{
        return bookingObject.getTotalTravelTime(passName)
    }

    suspend fun searchFlight(flightDet: FlightDet):Array<Flight>{
        return bookingObject.searchFlight(flightDet)
    }

    suspend fun passengerDetails(passName:String):Passenger?{
        return bookingObject.passDetails(passName)
    }
}