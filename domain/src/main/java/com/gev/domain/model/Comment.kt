package com.gev.domain.model

import java.util.*

class Comment(
    var id: Long = 0,
    val userName: String,
    val content: String,
    val taskId: Long,
    val createdAt: Date = Date(),
    val isSynchronized: Boolean = false
)