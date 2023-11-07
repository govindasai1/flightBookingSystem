package com.example.plugins

import com.example.exceptions.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configStatusPage(){
    install(StatusPages) {
        exception<PassengersNotFoundException> { call, cause ->
            call.respondText(text = cause.text , status = cause.code)
        }
        exception<DuplicatePassengerException>{ call, cause ->
            call.respondText(text = cause.text , status = cause.code)
        }
        exception<FlightNotFoundException>{ call, cause ->
            call.respondText(text = cause.text , status = cause.code)
        }
        exception<DuplicateFlightException>{ call, cause ->
            call.respondText(text = cause.text , status = cause.code)
        }
        exception<DatabaseException>{ call, cause ->
            call.respondText(text = cause.text , status = cause.code)
        }
    }
}
