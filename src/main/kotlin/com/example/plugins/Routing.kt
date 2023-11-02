package com.example.plugins

import com.example.route.adminRoute
import com.example.route.bookingRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        adminRoute()
        bookingRoute()
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
