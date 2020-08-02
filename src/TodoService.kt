package com.dextto

import io.ktor.features.NotFoundException
import io.ktor.util.KtorExperimentalAPI
import com.dextto.config.query
import com.dextto.infra.Todo
import com.dextto.infra.Todos
import com.dextto.domain.model.TodoRequest
import com.dextto.domain.model.TodoResponse
import org.jetbrains.exposed.sql.SortOrder
import java.time.LocalDateTime

@KtorExperimentalAPI
class TodoService {
    suspend fun getAll() = query {
        Todo.all()
            .orderBy(Todos.id to SortOrder.DESC)
            .map(TodoResponse.Companion::of)
            .toList()
    }
    suspend fun getById(id: Int) = query {
        Todo.findById(id)?.run(TodoResponse.Companion::of) ?: throw NotFoundException()
    }
    suspend fun new(content: String) = query {
        Todo.new { this.content = content }
    }
    suspend fun renew(id: Int, req: TodoRequest) = query {
        val todo = Todo.findById(id) ?: throw NotFoundException()
        todo.apply {
            content = req.content
            done = req.done ?: false
            updatedAt = LocalDateTime.now()
        }
    }
    suspend fun delete(id: Int) = query {
        Todo.findById(id)?.delete() ?: throw NotFoundException()
    }
}