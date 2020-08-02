package com.dextto

import io.ktor.application.call
import io.ktor.features.BadRequestException
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.put
import io.ktor.routing.route
import com.dextto.domain.model.UserRequest

//@KtorExperimentalAPI
fun Routing.user(service: UserService) { // 1
    route("users") { // 2
        get {
            call.respond(service.getAll())
        }
        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw BadRequestException("Parameter id is null")
            call.respond(service.getById(id))
        }
        post {
            val body = call.receive<UserRequest>()
            service.new(body.email)
            call.response.status(HttpStatusCode.Created)
        }
        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw BadRequestException("Parameter id is null")
            val body = call.receive<UserRequest>()
            service.renew(id, body)
            call.response.status(HttpStatusCode.NoContent)
        }
        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: throw BadRequestException("Parameter id is null")
            service.delete(id)
            call.response.status(HttpStatusCode.NoContent)
        }
    }
}