package com.gev.data.repository.mappers

import com.gev.data.locale.room.entity.ProjectEntity
import com.gev.domain.model.Project

fun Project.toProjectEntity(isSynchronised: Boolean? = null): ProjectEntity {

    val entity = ProjectEntity(
        this.title,
        this.description
    )
    entity.id = this.id
    entity.isSynchronised = isSynchronised?:this.isSynchronized
    return entity
}

fun ProjectEntity.toProject(): Project {

    return Project(
        this.id,
        this.title,
        this.description,
        this.createdAt,
        this.isSynchronised
    )
}

fun List<ProjectEntity>.toProjectList(): List<Project> = this.map { it.toProject() }
