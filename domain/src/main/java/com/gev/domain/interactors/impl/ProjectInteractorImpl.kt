package com.gev.domain.interactors.impl

import com.gev.domain.interactors.ProjectInteractor
import com.gev.domain.model.Project
import com.gev.domain.repository.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProjectInteractorImpl @Inject constructor(
    private val projectRepository: ProjectRepository,
) : ProjectInteractor {

    override fun getProject(projectId: Long): Flow<Project> =
        projectRepository.getProject(projectId)

    override fun getAllProjects(): Flow<List<Project>> = projectRepository.getAllProjects()

    override fun createProject(title: String, description: String) = flow {
        val project = Project(title = title, description = description)
        emit(projectRepository.createProject(project))
    }.flowOn(Dispatchers.IO)
}