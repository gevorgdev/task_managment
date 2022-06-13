package com.gev.task.ui.task.model

import com.gev.domain.model.enums.TaskStatusEnum
import java.util.*

class TaskItem(
    val id: Long,
    val projectId: Long,
    var title: String,
    var description: String,
    var status: TaskStatusEnum,
    val createdAt: Date,
    val isSynchronized: Boolean = false
)