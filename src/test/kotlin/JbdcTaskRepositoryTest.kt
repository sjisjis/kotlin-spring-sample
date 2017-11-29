package com.example.todolist

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringRunner

@RunWith(value = SpringRunner::class)
@SpringBootTest
@Sql(statements = arrayOf("DELETE FROM task"))
class JbdcTaskRepositoryTest {

    @Autowired
    private lateinit var sut: JdbcTaskRepository //private val sut:JdbcTaskRepository? = null

    @Test
    fun 何も作成しなければfindAllはからのリストを返すこと() {
        val got = sut.findAll()
        assertThat(got).isNotEmpty()
    }

    @Test
    fun createで作成したタスクをfindByidで取得出来ること() {
        val task = sut.create("TEST")
        val got = sut.findById(task.id)
        assertThat(got).isEqualTo(task)
    }
}