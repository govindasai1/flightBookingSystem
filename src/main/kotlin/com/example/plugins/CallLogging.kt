package com.example.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.callloging.*
import org.slf4j.event.Level

fun Application.callLogging(){
    install(CallLogging){
        level = Level.INFO
    }
}