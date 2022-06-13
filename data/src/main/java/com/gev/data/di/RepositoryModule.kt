package com.gev.data.di

import com.gev.data.locale.room.AppRoomDatabase
import com.gev.data.repository.ProjectRepositoryImpl
import com.gev.data.repository.TaskRepositoryImpl
import com.gev.domain.repository.ProjectRepository
import com.gev.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideProjectRepository(addDatabase: AppRoomDatabase): ProjectRepository {
        return ProjectRepositoryImpl(addDatabase)
    }

    @Provides
    fun provideTaskRepository(addDatabase: AppRoomDatabase): TaskRepository {
        return TaskRepositoryImpl(addDatabase)
    }
}