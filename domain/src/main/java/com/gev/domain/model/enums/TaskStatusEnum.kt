package com.gev.domain.model.enums

enum class TaskStatusEnum {
    NEW,
    IN_PROGRESS,
    UNDER_REVIEW,
    REOPEN,
    UNKNOWN
}

fun TaskStatusEnum.getStringName(): String = this.name.replace("_", " ")