package com.example.reposotoryTest

import com.example.databaseTest.DatabaseTest
import com.example.models.Message
import com.example.reposotory.AdminDaoImp
import com.example.tables.BookingTable
import com.example.tables.FlightsTable
import com.example.tables.PassengersTable
import com.example.utils.flight
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

class AdminDaoImpTest {
    private lateinit var database: Database
    private val adminObj = AdminDaoImp()

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
    fun addFlightsTest() = runBlocking {
        val result = adminObj.addFlights(flight)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun addPassengerTest() = runBlocking {
        val result = adminObj.addPassenger(passenger)
        println("      ${result.text}")
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun allFlightsTest() = runBlocking {
        val result = adminObj.allFlights()

        if (result.isNotEmpty()) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun allPassengersTest() = runBlocking {
        val result = adminObj.allPassengers()
        if (result.isNotEmpty()) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun removeFlightTest() = runBlocking {
        val result = adminObj.removeFlights("123456")
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun removePassengerTest() = runBlocking {
        val result = adminObj.removePassenger(passenger)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }


}