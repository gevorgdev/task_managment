package com.gev.domain.interactors

import com.gev.domain.model.Comment
import com.gev.domain.model.Task
import com.gev.domain.model.enums.TaskStatusEnum
import kotlinx.coroutines.flow.Flow


interface TaskInteractor {

    /**
     * Get all projects
     */
    fun getProjectTasks(projectId: Long): Flow<List<Task>>

    /**
     * Get task by id
     */
    fun getTask(taskId: Long): Flow<Task>

    /**
     * Create task
     */
    fun createTask(projectId: Long, title: String, description: String): Flow<Task>

    /**
     * Update Task
     */
    fun updateTask(id: Long, title: String, description: String): Flow<Task>

    /**
     * Update Task
     */
    fun updateTaskStatus(taskId: Long, status: TaskStatusEnum): Flow<Task>

    /**
     * Get task comments
     */
    fun getTaskComments(taskId: Long): Flow<List<Comment>>

    /**
     * Add comment to task
     */
    fun addCommentToTask(taskId: Long, userName: String, content: String): Flow<Comment>
}