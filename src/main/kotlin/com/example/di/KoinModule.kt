package com.example.di

import com.example.dao.AdminDao
import com.example.dao.BookingSystemDao
import com.example.reposotory.AdminDaoImp
import com.example.reposotory.BookingSystemDaoImp
import com.example.service.AdminServices
import com.example.service.BookingSystemServices
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.bind
import org.koin.dsl.module

val koinModule = module {
    singleOf(::BookingSystemDaoImp) { bind<BookingSystemDao>() }
    singleOf(::AdminDaoImp) { bind<AdminDao>() }
    singleOf(::AdminServices)
    singleOf(::BookingSystemServices)
}