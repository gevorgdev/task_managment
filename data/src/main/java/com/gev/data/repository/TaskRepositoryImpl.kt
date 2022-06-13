package com.gev.data.repository

import com.gev.data.locale.room.AppRoomDatabase
import com.gev.data.locale.room.converters.TaskStatusConverter
import com.gev.data.repository.mappers.*
import com.gev.domain.excaptions.CreateCommentException
import com.gev.domain.excaptions.CreateTaskException
import com.gev.domain.excaptions.UpdateTaskException
import com.gev.domain.model.Comment
import com.gev.domain.model.Task
import com.gev.domain.model.enums.TaskStatusEnum
import com.gev.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val appDatabase: AppRoomDatabase,
) : TaskRepository {

    override suspend fun createTask(task: Task): Task {
        try {
            appDatabase.taskDao().insert(task.toTaskEntity(isSynchronised = false))
            return task
        } catch (e: Exception) {
            throw CreateTaskException()
        }
    }

    override suspend fun createComment(comment: Comment): Comment {
        try {
            val id = appDatabase.commentDao().insert(comment.toCommentEntity(isSynchronised = false))
            comment.id = id
            return comment
        } catch (e: Exception) {
            throw CreateCommentException()
        }
    }

    override fun getProjectTasks(projectId: Long): Flow<List<Task>> =
        appDatabase.taskDao().findByProjectId(projectId)
            .map { it.toTaskList() }

    override fun getTaskById(taskId: Long): Flow<Task> =
        appDatabase.taskDao().findById(taskId).map { it.toTask() }

    override fun getAllTasks(): Flow<List<Task>> = appDatabase.taskDao().findAll()
        .map { it.toTaskList() }

    override fun getTaskComments(taskId: Long): Flow<List<Comment>> =
        appDatabase.commentDao().findByTaskId(taskId).map { it.toCommentList() }

    override suspend fun updateTask(task: Task): Task {
        try {
            appDatabase.taskDao().update(task.toTaskEntity(isSynchronised = false))
            return task
        } catch (e: Exception) {
            throw UpdateTaskException()
        }
    }

    override suspend fun updateTaskStatus(id: Long, status: TaskStatusEnum): Task {
        try {
            appDatabase.taskDao().updateTaskStatus(id, TaskStatusConverter.fromStatusEnum(status))
            val task = appDatabase.taskDao().findById(id).first()
            return task.toTask()
        } catch (e: Exception) {
            throw UpdateTaskException()
        }
    }
}