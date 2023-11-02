package com.example.serviceTest

import com.example.databaseTest.DatabaseTest
import com.example.di.koinModule
import com.example.models.Message
import com.example.service.AdminServices
import com.example.tables.BookingTable
import com.example.tables.FlightsTable
import com.example.tables.PassengersTable
import com.example.utils.flight
import com.example.utils.passenger
import com.example.utils.responces.successFlight
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
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AdminServicesTest : KoinComponent {
    private lateinit var database: Database
    private val adminServObj = AdminServices()

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
    fun addingFlights(): Unit = runBlocking {
        adminServObj.addingFlights(flight).apply {
            assertEquals(successFlight, this)
        }
    }

    @Test
    fun addingPassengers() = runBlocking {
        val result = adminServObj.addingPassengers(passenger)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun gettingAllPass() = runBlocking {
        val result = adminServObj.gettingAllPass()
        if (result.isNotEmpty()) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun gettingAllFlights() = runBlocking {
        val result = adminServObj.gettingAllFlights()
        if (result.isNotEmpty()) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun removingFlights() = runBlocking {
        val result = adminServObj.removingFlights("123456")
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun removingPassengers() = runBlocking {
        val result = adminServObj.removingPassengers(passenger)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

}