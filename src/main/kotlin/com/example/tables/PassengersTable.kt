package com.example.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object PassengersTable : IntIdTable("PassengerTable") {
    val name = varchar("Name", 50).uniqueIndex()
}