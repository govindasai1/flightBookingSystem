package com.example.plugins

import com.example.di.koinModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin

fun Application.koin() {
    install(Koin) {
        modules(koinModule)
    }
}