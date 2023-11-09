package com.example

import com.example.diTest.KoinModuleTest
import com.example.reposotoryTest.AdminDaoImpTest
//import com.example.reposotoryTest.BookingSystemDaoImpTest
import com.example.routeTest.AdminRouteTest
import com.example.routeTest.BookingRouteTest
import com.example.routeTest.CancelOrDeleteRoute
import com.example.serviceTest.AdminServicesTest
//import com.example.serviceTest.BookingServiceTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    KoinModuleTest::class,
    AdminDaoImpTest::class,
    AdminServicesTest::class,
    AdminRouteTest::class,
    BookingRouteTest::class,
    CancelOrDeleteRoute::class
)
class TestSuites