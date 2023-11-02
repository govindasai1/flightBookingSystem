package com.example.serviceTest

import com.example.databaseTest.DatabaseTest
import com.example.di.koinModule
import com.example.models.Message
import com.example.service.BookingSystemServices
import com.example.tables.BookingTable
import com.example.tables.FlightsTable
import com.example.tables.PassengersTable
import com.example.utils.flightDet
import com.example.utils.passenger
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
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BookingServiceTest : KoinComponent {
    private lateinit var database: Database
    private val bookingServObj = BookingSystemServices()

    @Before
    fun start() {
        startKoin { modules(koinModule) }
        database = DatabaseTest.init()
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_REPEATABLE_READ
        transaction(database) {
            SchemaUtils.create(FlightsTable, PassengersTable, BookingTable)
        }
    }

    @After
    fun end() {
        stopKoin()
        transaction(database) { SchemaUtils.drop(FlightsTable, PassengersTable, BookingTable) }
    }

    @Test
    fun bookingFlightTest() = runBlocking {
        val result = bookingServObj.bookFlight(passenger.name, "123456")
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun getTotalTimeTest() = runBlocking {
        val result = bookingServObj.getTotalTime(passenger.name)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun searchFlightTest() = runBlocking {
        val result = bookingServObj.searchFlight(flightDet)
        if (result.isNotEmpty()) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun getBookedFlight() = runBlocking {
        val result = bookingServObj.getBookedFlights(passenger.name)
        if (result.isNotEmpty()) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun passDetailsTest() = runBlocking {
        val result = bookingServObj.passengerDetails(passenger.name)
        if (result != null) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun cancelFlightTest() = runBlocking {
        val result = bookingServObj.cancelFlight(passenger.name, "123456")
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }
}