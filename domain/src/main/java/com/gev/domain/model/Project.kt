package com.gev.domain.model

import java.util.*

class Project(
    var id: Long = 0,
    val title: String,
    val description: String,
    val createdAt: Date = Date(),
    val isSynchronized: Boolean = false
)