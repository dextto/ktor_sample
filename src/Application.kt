package com.dextto

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                call.respondText("Hello, world!", ContentType.Text.Html)
            }
        }
    }
    server.start(wait = true)
}

//
//import io.ktor.application.*
//import io.ktor.response.*
//import io.ktor.request.*
//import io.ktor.client.*
//
//fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)
//
//@Suppress("unused") // Referenced in application.conf
//@kotlin.jvm.JvmOverloads
//fun Application.module(testing: Boolean = false) {
//    val client = HttpClient() {
//    }
//
//}
//
