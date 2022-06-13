package com.gev.domain.repository

import com.gev.domain.model.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {

    /**
     * Get project by id
     */
    fun getProject(projectId: Long): Flow<Project>

    /**
     * Create project
     */
    suspend fun createProject(project: Project): Project

    /**
     * Get all projects from database
     */
    fun getAllProjects(): Flow<List<Project>>
}