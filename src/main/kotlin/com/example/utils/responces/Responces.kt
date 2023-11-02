package com.example.utils.responces

import com.example.models.Message

val successFlight = Message("FLIGHT INSERTED TO BOOKING SYSTEM SUCCESSFULLY")
val successFlightBook = Message("FLIGHT BOOKED SUCCESSFULLY")
val successFlightCancel = Message("FLIGHT CANCELED SUCCESSFULLY")
val failureFlightBook = Message("FLIGHT NUMBER OR PASSENGER NOT FOUND")
val failureFlight = Message("FLIGHT NOT FOUND")
val failureCancel = Message("NOT DELETED")
val failurePassenger = Message("PASSENGER NOT FOUND")
val successPassenger = Message("PASSENGERS REMOVED SUCCESSFULLY")
val successFlightRemoval = Message("FLIGHT REMOVED FROM BOOKING SYSTEM SUCCESSFULLY")