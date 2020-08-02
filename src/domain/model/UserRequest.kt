package com.dextto.domain.model

import java.time.LocalDateTime

// TODO: validator
data class UserRequest(val email: String,
                       val createdAt: LocalDateTime?,
                       val updatedAt: LocalDateTime?)