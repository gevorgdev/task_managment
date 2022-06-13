package com.gev.domain.di

import com.gev.domain.interactors.ProjectInteractor
import com.gev.domain.interactors.TaskInteractor
import com.gev.domain.interactors.impl.ProjectInteractorImpl
import com.gev.domain.interactors.impl.TaskInteractorImpl
import com.gev.domain.repository.ProjectRepository
import com.gev.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {

    @Provides
    fun provideProjectInteractor(projectRepository: ProjectRepository): ProjectInteractor {
        return ProjectInteractorImpl(projectRepository)
    }

    @Provides
    fun provideTaskInteractor(taskRepository: TaskRepository): TaskInteractor {
        return TaskInteractorImpl(taskRepository)
    }
}