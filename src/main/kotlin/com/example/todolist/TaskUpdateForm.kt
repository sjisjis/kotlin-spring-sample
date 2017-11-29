package com.example.todolist

import org.hibernate.validator.constraints.NotBlank
import javax.validation.constraints.Size

class TaskUpdateForm {

    @NotBlank
    @Size(max = 20)
    var content: String? = null
    var done: Boolean = false
}