package com.example.reposotoryTest

import com.example.databaseTest.DatabaseTest
import com.example.reposotory.AdminDaoImp
import com.example.reposotory.BookingSystemDaoImp
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
import java.sql.Connection
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AdminDaoImpTest {
    private lateinit var database: Database
    private val adminObj = AdminDaoImp()
    private val bookingObj = BookingSystemDaoImp()

    @Before
    fun start() {
        database = DatabaseTest.init()
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_REPEATABLE_READ
        transaction(database) {
            SchemaUtils.create(FlightsTable, PassengersTable, BookingTable)
            TestMock.init()
        }

    }

    @After
    fun end() {
        transaction(database) { SchemaUtils.drop(FlightsTable, PassengersTable, BookingTable) }
    }


    @Test
    fun addFlightsTest() = runBlocking {
        val result = adminObj.addFlights(flight)
        assertEquals(result.text, successFlight.text)
    }

    @Test
    fun addPassengerTest() = runBlocking {
        val result = adminObj.addPassenger(passenger1)
        assertTrue(result.text.isNotEmpty())
    }

    @Test
    fun allFlightsTest() = runBlocking {
        val result = adminObj.allFlights()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun allPassengersTest() = runBlocking {
        val result = adminObj.allPassengers()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun bookingFlightTest() = runBlocking {
        val result = bookingObj.bookingFlight(passenger.name, "123567")
        assertEquals(result.text, successFlightBook.text)
    }

    @Test
    fun getTotalTimeTest() = runBlocking {
        val result = bookingObj.getTotalTravelTime(passenger.name)
        assertTrue(result.text.isNotEmpty())
    }

    @Test
    fun searchFlightTest() = runBlocking {
        val result = bookingObj.searchFlight(flightDet)
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun getBookedFlight() = runBlocking {
        val result = bookingObj.getBookedFlight(passenger.name)
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun passDetailsTest() = runBlocking {
        val result = bookingObj.passDetails(passenger.name)
        assertEquals(result.name, passenger.name)
    }

    @Test
    fun cancelFlightTest() = runBlocking {
        val result = bookingObj.cancelFlight(passenger.name, "123567")
        assertEquals(result.text, successFlightCancel.text)
    }

    @Test
    fun removeFlightTest() = runBlocking {
        val result = adminObj.removeFlights("123567")
        assertEquals(result.text, successFlightRemoval.text)
    }

    @Test
    fun removePassengerTest() = runBlocking {
        val result = adminObj.removePassenger(passenger)
        assertEquals(result.text, successPassenger.text)
    }


}