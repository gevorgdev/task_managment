package com.gev.domain.model

import com.gev.domain.model.enums.TaskStatusEnum
import java.util.*

class Task(
    val id: Long = 0,
    val projectId: Long,
    var title: String,
    var description: String,
    var status: TaskStatusEnum,
    val createdAt: Date = Date(),
    var isSynchronized: Boolean = false
)