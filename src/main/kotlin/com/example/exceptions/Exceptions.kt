package com.example.exceptions

import io.ktor.http.*

class DuplicateFlightException(val text:String,val code:HttpStatusCode):RuntimeException()
class FlightNotFoundException(val text: String,val code: HttpStatusCode):RuntimeException()
class PassengersNotFound(val text: String,val code: HttpStatusCode):RuntimeException()
class DuplicatePassengerException(val text: String,val code: HttpStatusCode):RuntimeException()
class DatabaseException(val text: String,val code: HttpStatusCode):RuntimeException()