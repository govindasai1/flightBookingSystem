package com.example.reposotory

import com.example.dao.BookingSystemDao
import com.example.database.DatabaseFactory
import com.example.models.Flight
import com.example.models.FlightDet
import com.example.models.Message
import com.example.models.Passenger
import com.example.tables.BookingTable
import com.example.tables.FlightsTable
import com.example.tables.PassengersTable
import com.example.utils.checking.*
import com.example.utils.methods.*
import com.example.utils.responces.failureCancel
import com.example.utils.responces.failureFlightBook
import com.example.utils.responces.successFlightBook
import com.example.utils.responces.successFlightCancel
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class BookingSystemDaoImp : BookingSystemDao {
    override suspend fun bookingFlight(passName: String, flightNumber: String): Message {
        val flightI = gettingFlightId(flightNumber)
        val passId = gettingPassengerId(passName)
        return if (flightI != null && passId != null) {
            DatabaseFactory.dbQuery {
                BookingTable.insert {
                    it[passengerId] = passId
                    it[flightId] = flightI
                }
            }
            successFlightBook
        } else failureFlightBook
    }

    override suspend fun cancelFlight(passName: String, flightNumber: String): Message {
        val passId = gettingPassengerId(passName)
        return if (passId != null && checkingFlight(flightNumber)) {
            val flightI = gettingFlightId(flightNumber)
            val res =
                DatabaseFactory.dbQuery { BookingTable.deleteWhere { passengerId.eq(passId) and flightId.eq(flightI) } } > 0
            if (res) successFlightCancel
            else failureCancel
        } else failureFlightBook
    }

    override suspend fun getBookedFlight(passName: String): ArrayList<Flight> {
        val passId = gettingPassengerId(passName)
        val x = DatabaseFactory.dbQuery {
            BookingTable.select { BookingTable.passengerId.eq(passId) }.map { rowToBookingFlightId(it) }
        }
        val arrayList = ArrayList<Flight>()
        for (element in x) {
            arrayList.add(gettingFlightById(element))
        }
        return arrayList
    }


    override suspend fun getTotalTravelTime(passName: String): Message {
        var totalTime = 0f
        val result = getBookedFlight(passName).toList()
        result.map { totalTime += timeTaken(it.departureTime, it.arrivalTime) }
        return Message("TOTAL TIME TAKEN IS $totalTime HOURS")
    }

    override suspend fun searchFlight(flightDet: FlightDet): Array<Flight> {
        return DatabaseFactory.dbQuery {
            FlightsTable.select {
                FlightsTable.sources.eq(flightDet.source) and
                        FlightsTable.destination.eq(flightDet.destination) and FlightsTable.departureTime.eq(flightDet.departureTime)
            }.map { rowToFlight(it) }
        }.toTypedArray()
    }

    override suspend fun passDetails(passName: String): Passenger? {
        return DatabaseFactory.dbQuery {
            PassengersTable.select { PassengersTable.name.eq(passName) }.map { rowToPassenger(it) }
        }.singleOrNull()
    }
}