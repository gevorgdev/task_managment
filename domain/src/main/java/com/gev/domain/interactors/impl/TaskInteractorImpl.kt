package com.gev.domain.interactors.impl

import com.gev.domain.interactors.TaskInteractor
import com.gev.domain.model.Comment
import com.gev.domain.model.Task
import com.gev.domain.model.enums.TaskStatusEnum
import com.gev.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TaskInteractorImpl @Inject constructor(
    private val taskRepository: TaskRepository,
) : TaskInteractor {

    override fun getProjectTasks(projectId: Long): Flow<List<Task>> =
        taskRepository.getProjectTasks(projectId)

    override fun getTask(taskId: Long): Flow<Task> = taskRepository.getTaskById(taskId)

    override fun createTask(projectId: Long, title: String, description: String) = flow {
        val project = Task(
            title = title,
            description = description,
            projectId = projectId,
            status = TaskStatusEnum.NEW
        )
        emit(taskRepository.createTask(project))
    }.flowOn(Dispatchers.IO)

    override fun updateTask(id: Long, title: String, description: String): Flow<Task> = flow {
        val existingTask = taskRepository.getTaskById(taskId = id).first()
        existingTask.title = title
        existingTask.description = description
        existingTask.isSynchronized = false
        emit(taskRepository.updateTask(existingTask))
    }.flowOn(Dispatchers.IO)

    override fun updateTaskStatus(taskId: Long, status: TaskStatusEnum) = flow {
        emit(taskRepository.updateTaskStatus(taskId, status))
    }.flowOn(Dispatchers.IO)

    override fun getTaskComments(taskId: Long): Flow<List<Comment>> =
        taskRepository.getTaskComments(taskId)

    override fun addCommentToTask(taskId: Long, userName: String, content: String) = flow {
        emit(taskRepository.createComment(Comment(taskId = taskId, userName = userName, content = content)))
    }.flowOn(Dispatchers.IO)
}