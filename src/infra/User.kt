package com.dextto.infra

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime

// Table scheme
object Users : IntIdTable() {  // TODO: extends UUIDTable
    val email = varchar("email", 120).default("")
    val createdAt = datetime("created_at").default(LocalDateTime.now()) // TODO: UTC
    val updatedAt = datetime("updated_at").default(LocalDateTime.now())
}

// Entity
class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)
    var email by Users.email
    var createdAt by Users.createdAt
    var updatedAt by Users.updatedAt
}