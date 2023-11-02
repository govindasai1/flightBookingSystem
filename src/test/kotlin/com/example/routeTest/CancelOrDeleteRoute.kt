package com.example.routeTest

import com.example.utils.endPoints.ADMIN_PATH
import com.example.utils.endPoints.BOOKING_PATH
import com.example.utils.endPoints.FLIGHT_PATH
import com.example.utils.endPoints.PASS_PATH
import com.example.utils.flightNumber
import com.example.utils.passNameFlightNo
import com.example.utils.passenger
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class CancelOrDeleteRoute {
    @Test
    fun testCancelFlight() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.delete(BOOKING_PATH + FLIGHT_PATH) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(passNameFlightNo)
        }
        assertEquals(HttpStatusCode.OK, responce.status)
    }

    @Test
    fun testDeletePass() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.delete(ADMIN_PATH + PASS_PATH) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(passenger)
        }
        assertEquals(HttpStatusCode.OK, responce.status)
    }

    @Test
    fun testDeleteFlight() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.delete(ADMIN_PATH + FLIGHT_PATH) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(flightNumber)
        }
        println("        ${responce.bodyAsText()}")
        assertEquals(HttpStatusCode.OK, responce.status)
    }

}