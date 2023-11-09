package com.example.serviceTest

import com.example.databaseTest.DatabaseTest
import com.example.di.koinModule
import com.example.service.AdminServices
import com.example.service.BookingSystemServices
import com.example.tables.BookingTable
import com.example.tables.FlightsTable
import com.example.tables.PassengersTable
import com.example.utils.*
import com.example.utils.responces.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import java.sql.Connection
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AdminServicesTest : KoinComponent {
    private lateinit var database: Database
    private val adminServObj = AdminServices()
    private val bookingServObj = BookingSystemServices()

    @Before
    fun start() {
        startKoin { modules(koinModule) }
        database = DatabaseTest.init()
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_REPEATABLE_READ
        transaction(database) {
            SchemaUtils.create(FlightsTable, PassengersTable, BookingTable)
            TestMock.init()
        }
    }

    @After
    fun end() {
        stopKoin()
        transaction(database) { SchemaUtils.drop(FlightsTable, PassengersTable, BookingTable) }
    }

    @Test
    fun addingFlights(): Unit = runBlocking {
        adminServObj.addingFlights(flight).apply {
            assertEquals(successFlight, this)
        }
    }

    @Test
    fun addingPassengers(): Unit = runBlocking {
        adminServObj.addingPassengers(passenger1).apply {
            assertTrue(this.text.isNotEmpty())
        }
    }

    @Test
    fun gettingAllPass(): Unit = runBlocking {
        adminServObj.gettingAllPass().apply {
            assertTrue(this.isNotEmpty())
        }

    }

    @Test
    fun gettingAllFlights(): Unit = runBlocking {
        adminServObj.gettingAllFlights().apply {
            assertTrue(this.isNotEmpty())
        }
    }

    @Test
    fun bookingFlightTest() = runBlocking {
        val result = bookingServObj.bookFlight(passenger.name, "123567")
        assertEquals(result.text, successFlightBook.text)
    }

    @Test
    fun getTotalTimeTest() = runBlocking {
        val result = bookingServObj.getTotalTime(passenger.name)
        assertTrue(result.text.isNotEmpty())
    }

    @Test
    fun searchFlightTest() = runBlocking {
        val result = bookingServObj.searchFlight(flightDet)
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun getBookedFlight() = runBlocking {
        val result = bookingServObj.getBookedFlights(passenger.name)
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun passDetailsTest() = runBlocking {
        val result = bookingServObj.passengerDetails(passenger.name)
        assertEquals(result.name, passenger.name)
    }

    @Test
    fun cancelFlightTest() = runBlocking {
        val result = bookingServObj.cancelFlight(passenger.name, "123567")
        assertEquals(result.text, successFlightCancel.text)
    }

    @Test
    fun removingFlights(): Unit = runBlocking {
        adminServObj.removingFlights("123567").apply {
            assertEquals(this, successFlightRemoval)
        }
    }

    @Test
    fun removingPassengers(): Unit = runBlocking {
        adminServObj.removingPassengers(passenger).apply {
            assertEquals(this, successPassenger)
        }
    }

}