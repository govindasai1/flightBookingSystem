package com.example.reposotory

import com.example.dao.AdminDao
import com.example.database.DatabaseFactory
import com.example.models.Flight
import com.example.models.Message
import com.example.models.Passenger
import com.example.models.PassengerName
import com.example.tables.BookingTable
import com.example.tables.FlightsTable
import com.example.tables.PassengersTable
import com.example.utils.checking.gettingFlightId
import com.example.utils.checking.gettingPassengerId
import com.example.utils.methods.rowToFlight
import com.example.utils.methods.rowToPassenger
import com.example.utils.responces.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class AdminDaoImp : AdminDao {
    override suspend fun addFlights(flight: Flight): Message {
        DatabaseFactory.dbQuery {
            FlightsTable.insert {
                it[flightNumber] = flight.flightNumber
                it[sources] = flight.source
                it[destination] = flight.destination
                it[departureTime] = flight.departureTime
                it[arriveTime] = flight.arrivalTime
            }
        }
        return successFlight
    }

    override suspend fun addPassenger(passenger: PassengerName): Message {
        val passengerId = DatabaseFactory.dbQuery {
            PassengersTable.insertAndGetId {
                it[name] = passenger.name
            }
        }.value
        return Message("$passengerId")
    }

    override suspend fun removeFlights(flightNumber: String): Message {
        val flight = gettingFlightId(flightNumber)
        println("    $flight")
        val result = DatabaseFactory.dbQuery {
            BookingTable.deleteWhere { flightId.eq(flight) } or FlightsTable.deleteWhere {
                FlightsTable.flightNumber.eq(flightNumber)
            }
        } > 0

        println("       $result   ")
        return if (result) successFlightRemoval
        else failureFlight
    }

    override suspend fun removePassenger(passengerName: PassengerName): Message {
        val passId = gettingPassengerId(passengerName.name)
        println("  $passId")
        val result = DatabaseFactory.dbQuery {
            BookingTable.deleteWhere { passengerId.eq(passId) } or PassengersTable.deleteWhere {
                name.eq(passengerName.name)
            }
        } > 0

        println("   $result  ")
        return if (result) successPassenger
        else failurePassenger
    }

    override suspend fun allPassengers(): Array<Passenger> {
        return DatabaseFactory.dbQuery { PassengersTable.selectAll().map { rowToPassenger(it) } }.toTypedArray()
    }

    override suspend fun allFlights(): Array<Flight> {
        return DatabaseFactory.dbQuery { FlightsTable.selectAll().map { rowToFlight(it) } }.toTypedArray()
    }

}