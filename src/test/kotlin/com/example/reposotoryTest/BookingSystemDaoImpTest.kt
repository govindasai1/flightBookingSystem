package com.example.reposotoryTest

import com.example.databaseTest.DatabaseTest
import com.example.models.Message
import com.example.reposotory.BookingSystemDaoImp
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
import java.sql.Connection
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BookingSystemDaoImpTest {
    private lateinit var database: Database
    private val bookingObj = BookingSystemDaoImp()

    @Before
    fun start() {
        database = DatabaseTest.init()
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_REPEATABLE_READ
        transaction(database) {
            SchemaUtils.create(FlightsTable, PassengersTable, BookingTable)
        }
    }

    @After
    fun end() {
        transaction(database) { SchemaUtils.drop(FlightsTable, PassengersTable, BookingTable) }
    }

    @Test
    fun bookingFlightTest() = runBlocking {
        val result = bookingObj.bookingFlight(passenger.name, "123456")
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun getTotalTimeTest() = runBlocking {
        val result = bookingObj.getTotalTravelTime(passenger.name)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun searchFlightTest() = runBlocking {
        val result = bookingObj.searchFlight(flightDet)
        if (result.isNotEmpty()) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun getBookedFlight() = runBlocking {
        val result = bookingObj.getBookedFlight(passenger.name)
        if (result.isNotEmpty()) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun passDetailsTest() = runBlocking {
        val result = bookingObj.passDetails(passenger.name)
        if (result != null) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun cancelFlightTest() = runBlocking {
        val result = bookingObj.cancelFlight(passenger.name, "123456")
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }
}