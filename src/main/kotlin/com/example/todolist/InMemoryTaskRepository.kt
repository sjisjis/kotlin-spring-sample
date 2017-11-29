package com.example.todolist

import org.springframework.stereotype.Repository

@Repository
class InMemoryTaskRepository: TaskRepository {
    private val tasks: MutableList<Task> = mutableListOf()
    private val maxId: Long
        get() = tasks.map(Task::id).max() ?: 0

    override fun create(content: String): Task {
        val id = maxId + 1
        val task = Task(id, content, false)
        tasks += task
        return task
    }

    override fun update(task: Task) {
        tasks.replaceAll { t ->
            if (t.id  == task.id) task
            else t
        }
    }

    override fun findAll(): List<Task> = tasks.toList()
    override fun findById(id: Long): Task? = tasks.find { it.id == id }
}