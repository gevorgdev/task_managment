package com.gev.task.ui.project.viewmodel.mapper

import com.gev.domain.model.Project
import com.gev.task.ui.project.model.ProjectItem

fun Project.toProjectItem(): ProjectItem {

    return ProjectItem(
        this.id,
        this.title,
        this.description,
        this.createdAt
    )
}

fun List<Project>.toProjectItemList(): List<ProjectItem> = this.map { it.toProjectItem() }

fun ProjectItem.toProject(): Project {

    return Project(
        this.id,
        this.title,
        this.description,
        this.createdAt
    )
}

fun List<ProjectItem>.toProjectList(): List<Project> = this.map { it.toProject() }