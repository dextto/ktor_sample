package com.dextto

import com.dextto.config.query
import com.dextto.domain.model.UserRequest
import com.dextto.domain.model.UserResponse
import com.dextto.infra.User
import com.dextto.infra.Users
import io.ktor.features.NotFoundException
import org.jetbrains.exposed.sql.SortOrder
import java.time.LocalDateTime

class UserService {
    suspend fun getAll() = query {
        User.all()
            .orderBy(Users.id to SortOrder.DESC)
            .map(UserResponse.Companion::of)
            .toList()
    }
    suspend fun getById(id: Int) = query {
        User.findById(id)?.run(UserResponse.Companion::of) ?: throw NotFoundException()
    }
    suspend fun new(content: String) = query {
        User.new { this.email = content }
    }
    suspend fun renew(id: Int, req: UserRequest) = query {
        val todo = User.findById(id) ?: throw NotFoundException()
        todo.apply {
            email = req.email
            updatedAt = LocalDateTime.now()
        }
    }
    suspend fun delete(id: Int) = query {
        User.findById(id)?.delete() ?: throw NotFoundException()
    }
}