package com.gev.data.repository

import com.gev.data.locale.room.AppRoomDatabase
import com.gev.data.repository.mappers.toProject
import com.gev.data.repository.mappers.toProjectEntity
import com.gev.data.repository.mappers.toProjectList
import com.gev.domain.excaptions.CreateProjectException
import com.gev.domain.model.Project
import com.gev.domain.repository.ProjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val appDatabase: AppRoomDatabase,
) : ProjectRepository {

    override suspend fun createProject(project: Project): Project {
        try {
            val insertedId = appDatabase.projectDao().insert(project.toProjectEntity(isSynchronised = false))
            project.id = insertedId
            return project
        } catch (e: Exception) {
            throw CreateProjectException()
        }
    }

    override fun getProject(projectId: Long): Flow<Project> = appDatabase.projectDao().findById(projectId)
        .map { it.toProject() }

    override fun getAllProjects(): Flow<List<Project>> = appDatabase.projectDao().findAll()
        .map { it.toProjectList() }
}