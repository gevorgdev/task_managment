package com.gev.task.ui.task.model

import java.util.*

class CommentItem(
    val id: Long,
    val userName: String,
    val content: String,
    val taskId: Long,
    val createdAt: Date,
    val isSynchronized: Boolean = false
)