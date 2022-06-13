package com.gev.domain.interactors

import com.gev.domain.model.Project
import kotlinx.coroutines.flow.Flow


interface ProjectInteractor {

    /**
     * Get all projects
     */
    fun getProject(projectId: Long): Flow<Project>

    /**
     * Get all projects
     */
    fun getAllProjects(): Flow<List<Project>>

    /**
     * Create new project
     */
    fun createProject(title: String, description: String): Flow<Project>
}