package com.example.routeTest

import com.example.utils.*
import com.example.utils.endPoints.ADMIN_PATH
import com.example.utils.endPoints.FLIGHT_PATH
import com.example.utils.endPoints.PASS_PATH
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class AdminRouteTest {




    @Test
    fun testInsertPass() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val responce = client.post(ADMIN_PATH + PASS_PATH) {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(passenger)
        }
        assertEquals(HttpStatusCode.Created, responce.status)
    }

    @Test
    fun testGetPass() = testApplication {
        val responce = client.get(ADMIN_PATH + PASS_PATH) {}
        assertEquals(HttpStatusCode.OK, responce.status)
    }

    @Test
    fun testGetFlight() = testApplication {
        val responce = client.get(ADMIN_PATH + FLIGHT_PATH) {}
        assertEquals(HttpStatusCode.OK, responce.status)
    }


}