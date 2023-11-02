package com.example.routeTest

import com.example.utils.endPoints.*
import com.example.utils.flightDet
import com.example.utils.passNameFlightNo
import com.example.utils.passenger
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class BookingRouteTest {

    @Test
    fun testBookingFlight() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.post(BOOKING_PATH + FLIGHT_PATH) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(passNameFlightNo)
        }
        assertEquals(HttpStatusCode.OK, responce.status)
    }


    @Test
    fun testGetTotalTime() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.get(BOOKING_PATH + PASS_TOTALTIME) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(passenger)
        }
        assertEquals(HttpStatusCode.OK, responce.status)
    }

    @Test
    fun testGetBookedFlight() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.get(BOOKING_PATH + BOOKED_FLIGHTS) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(passenger)
        }
        assertEquals(HttpStatusCode.OK, responce.status)
    }

    @Test
    fun testGetSearchFlight() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.get(BOOKING_PATH + SEARCH_FLIGHT) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(flightDet)
        }
        assertEquals(HttpStatusCode.OK, responce.status)
    }

    @Test
    fun testGetPassDetails() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.get(BOOKING_PATH + PASS_PATH) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(passenger)
        }
        assertEquals(HttpStatusCode.OK, responce.status)
    }
}