package com.gev.data.repository.mappers

import com.gev.data.locale.room.entity.TaskEntity
import com.gev.domain.model.Task

fun Task.toTaskEntity(isSynchronised: Boolean? = null): TaskEntity {

    val entity = TaskEntity(
        this.projectId,
        this.title,
        this.description,
        this.status
    )
    entity.id = this.id
    entity.isSynchronised = isSynchronised?:this.isSynchronized
    return entity
}

fun TaskEntity.toTask(): Task {

    return Task(
        this.id,
        this.projectId,
        this.title,
        this.description,
        this.status,
        this.createdAt,
        this.isSynchronised
    )
}

fun List<TaskEntity>.toTaskList(): List<Task> = this.map { it.toTask() }