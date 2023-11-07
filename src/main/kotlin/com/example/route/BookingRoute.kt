package com.example.route

import com.example.models.*
import com.example.service.BookingSystemServices
import com.example.utils.endPoints.*
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
            call.application.environment.log.info("request data received")
            call.respond(bookingObj.bookFlight(request.passName, request.flightNumber))
        }

        delete(FLIGHT_PATH) {
            val request = call.receive<PassNameFlightNo>()
            call.application.environment.log.info("request data received")
            call.respond(bookingObj.cancelFlight(request.passName, request.flightNumber))
        }

        get(PASS_TOTALTIME) {
            val request = call.receive<PassengerName>()
            call.application.environment.log.info("request data received")
            call.respond(bookingObj.getTotalTime(request.name))
            call.application.environment.log.info("responce submitted successfully")
        }

        get(BOOKED_FLIGHTS) {
            val request = call.receive<PassengerName>()
            call.application.environment.log.info("request data received")
            call.respond(bookingObj.getBookedFlights(request.name))
            call.application.environment.log.info("responce submitted successfully")
        }

        get(SEARCH_FLIGHT) {
            val request = call.receive<FlightDet>()
            call.application.environment.log.info("request data received")
            call.respond(bookingObj.searchFlight(request))
            call.application.environment.log.info("responce submitted successfully")
        }

        get(PASS_PATH) {
            val request = call.receive<PassengerName>()
            call.application.environment.log.info("request data received")
            call.respond(bookingObj.passengerDetails(request.name))
            call.application.environment.log.info("responce submitted successfully")
        }
    }
}