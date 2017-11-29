package com.example.todolist

import org.springframework.jdbc.core.JdbcTemplate

inline fun <reified T> JdbcTemplate.queryForObject(sql: String): T =
        queryForObject(sql, T::class.java)