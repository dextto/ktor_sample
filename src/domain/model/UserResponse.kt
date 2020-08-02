package com.dextto.domain.model

import com.dextto.infra.User
import java.time.LocalDateTime
data class UserResponse(val id: Int,
                        val email: String,
                        val createdAt: LocalDateTime,
                        val updatedAt: LocalDateTime) {
    companion object {
        fun of(user: User) =
            UserResponse(
                id = user.id.value,
                email = user.email,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt
            )
    }
}