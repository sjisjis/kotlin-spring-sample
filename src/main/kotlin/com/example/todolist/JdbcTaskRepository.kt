package com.example.todolist

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class JdbcTaskRepository(private val jdbcTemplate: JdbcTemplate) : TaskRepository {

    private val rowMapper = RowMapper<Task> { rs, _ ->
        Task(rs.getLong("id"), rs.getString("content"), rs.getBoolean("done"))
    }

    override fun create(content: String): Task {
        jdbcTemplate.update("INSERT INTO task(content) VALUES(?)", content)
        //val id = jdbcTemplate.queryForObject("SELECT last_insert_id()", Long::class.java)
        val id: Long = jdbcTemplate.queryForObject("SELECT last_insert_id()")
        return Task(id, content, false)
    }

    override fun update(task: Task) {
        jdbcTemplate.update("UPDATE task SET content = ?, done =? WHERE id = ?",
                task.content,
                task.done,
                task.id)
    }

    override fun findAll(): List<Task> =
            jdbcTemplate.query("SELECT id, content, done FROM task", rowMapper)

    override fun findById(id: Long): Task? =
            jdbcTemplate.query("SELECT id, content, done FROM task WHERE id = ?", rowMapper, id).firstOrNull()
}