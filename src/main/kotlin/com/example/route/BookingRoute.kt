package com.example.route

import com.example.models.*
import com.example.service.BookingSystemServices
import com.example.utils.endPoints.*
import com.example.utils.responces.failurePassenger
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.bookingRoute() {
    val bookingObj by inject<BookingSystemServices>()

    route(BOOKING_PATH) {
        post(FLIGHT_PATH) {
            val request = call.receive<PassNameFlightNo>()
            call.respond(bookingObj.bookFlight(request.passName, request.flightNumber))
        }

        delete(FLIGHT_PATH) {
            val request = call.receive<PassNameFlightNo>()
            call.respond(bookingObj.cancelFlight(request.passName, request.flightNumber))
        }

        get(PASS_TOTALTIME) {
            val request = call.receive<PassengerName>()
            call.respond(bookingObj.getTotalTime(request.name))
        }

        get(BOOKED_FLIGHTS) {
            val request = call.receive<PassengerName>()
            call.respond(bookingObj.getBookedFlights(request.name))
        }

        get(SEARCH_FLIGHT) {
            val request = call.receive<FlightDet>()
            call.respond(bookingObj.searchFlight(request))
        }

        get(PASS_PATH) {
            val request = call.receive<PassengerName>()
            val result = bookingObj.passengerDetails(request.name)
            if (result == null) call.respond(failurePassenger)
            else call.respond(result)
        }
    }
}