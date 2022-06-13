package com.gev.domain.repository

import com.gev.domain.model.Comment
import com.gev.domain.model.Task
import com.gev.domain.model.enums.TaskStatusEnum
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    /**
     * Create task
     */
    suspend fun createTask(task: Task): Task

    /**
     * Add Comment
     */
    suspend fun createComment(comment: Comment): Comment

    /**
     * Get all tasks by project
     */
    fun getProjectTasks(projectId: Long): Flow<List<Task>>

    /**
     * Get tasks by id
     */
    fun getTaskById(taskId: Long): Flow<Task>

    /**
     * Get all tasks
     */
    fun getAllTasks(): Flow<List<Task>>

    /**
     * Get all comments for specific task
     */
    fun getTaskComments(taskId: Long): Flow<List<Comment>>

    /**
     * Update task
     */
    suspend fun updateTask(task: Task): Task

    /**
     * Update task status
     */
    suspend fun updateTaskStatus(id: Long, status: TaskStatusEnum): Task
}