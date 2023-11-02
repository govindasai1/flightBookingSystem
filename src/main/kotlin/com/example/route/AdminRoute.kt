package com.example.route

import com.example.models.Flight
import com.example.models.FlightNumber
import com.example.models.PassengerName
import com.example.service.AdminServices
import com.example.utils.endPoints.ADMIN_PATH
import com.example.utils.endPoints.FLIGHT_PATH
import com.example.utils.endPoints.PASS_PATH
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.adminRoute() {
    val adminOnj by inject<AdminServices>()
    route(ADMIN_PATH) {
        post(FLIGHT_PATH) {
            val request = call.receive<Flight>()
            call.respond(HttpStatusCode.Created, adminOnj.addingFlights(request))
        }

        delete(FLIGHT_PATH) {
            val request = call.receive<FlightNumber>()
            call.respond(adminOnj.removingFlights(request.number))
        }

        post(PASS_PATH) {
            val request = call.receive<PassengerName>()
            call.respond(HttpStatusCode.Created, adminOnj.addingPassengers(request))
        }

        delete(PASS_PATH) {
            val request = call.receive<PassengerName>()
            call.respond(adminOnj.removingPassengers(request))
        }

        get(PASS_PATH) {
            call.respond(adminOnj.gettingAllPass())
        }

        get(FLIGHT_PATH) {
            call.respond(adminOnj.gettingAllFlights())
        }
    }
}