package com.example.plugins

import com.example.route.adminRoute
import com.example.route.bookingRoute
import io.ktor.server.application.Application
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        adminRoute()
        bookingRoute()
    }
}
