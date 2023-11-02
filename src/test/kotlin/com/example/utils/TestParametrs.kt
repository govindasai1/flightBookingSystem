package com.example.utils

import com.example.models.*
import kotlinx.serialization.Serializable

@Serializable
val flight = Flight("123456", "chni", "dlhi", "10:00", "15:30")

@Serializable
val passenger = PassengerName("govindasai0")

@Serializable
val flightNumber = FlightNumber("123567")

@Serializable
val flight2 = Flight("123567", "chni", "goa", "01:00", "13:30")

@Serializable
val flightDet = FlightDet("chni", "dlhi", "10:00")

@Serializable
val passNameFlightNo = PassNameFlightNo("govindasai0", "123567")