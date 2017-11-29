package com.example.todolist

interface TaskRepository {

    fun create(content: String): Task

    fun update(task: Task)

    fun findAll(): List<Task>

    fun findById(id: Long): Task?
}