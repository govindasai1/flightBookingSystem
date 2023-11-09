package com.example.exceptions

import io.ktor.http.HttpStatusCode

class DuplicateFlightException(val text:String,val code:HttpStatusCode):RuntimeException()
class FlightNotFoundException(val text: String,val code: HttpStatusCode):RuntimeException()
class PassengersNotFoundException(val text: String, val code: HttpStatusCode):RuntimeException()
class DuplicatePassengerException(val text: String,val code: HttpStatusCode):RuntimeException()
class DatabaseException(val text: String,val code: HttpStatusCode):RuntimeException()