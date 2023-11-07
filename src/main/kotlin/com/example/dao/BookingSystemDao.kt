package com.example.dao

import com.example.models.Flight
import com.example.models.FlightDet
import com.example.models.Message
import com.example.models.Passenger

interface BookingSystemDao {
    suspend fun bookingFlight(passName: String, flightNumber: String): Message
    suspend fun cancelFlight(passName: String, flightNumber: String): Message
    suspend fun getBookedFlight(passName: String): ArrayList<Flight>
    suspend fun getTotalTravelTime(passName: String): Message
    suspend fun searchFlight(flightDet: FlightDet): Array<Flight>
    suspend fun passDetails(passName: String): Passenger

}