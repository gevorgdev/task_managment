package com.gev.task.ui.task.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.gev.domain.excaptions.CreateCommentException
import com.gev.domain.excaptions.CreateTaskException
import com.gev.domain.excaptions.UpdateTaskException
import com.gev.domain.excaptions.UpdateTaskStatusException
import com.gev.domain.interactors.TaskInteractor
import com.gev.domain.model.enums.TaskStatusEnum
import com.gev.task.R
import com.gev.task.ui.task.model.CommentItem
import com.gev.task.ui.task.model.TaskItem
import com.gev.task.ui.task.viewmodel.mapper.toCommentItemList
import com.gev.task.ui.task.viewmodel.mapper.toTaskItem
import com.gev.task.ui.task.viewmodel.mapper.toTaskItemList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskInteractor: TaskInteractor,
    val context: Application,
) : AndroidViewModel(context) {

    // Error state
    val errorState: MutableLiveData<String?> = MutableLiveData(null)

    // Task Created status
    val taskCreateState: MutableLiveData<Boolean> = MutableLiveData(false)
    val taskEditState: MutableLiveData<Boolean> = MutableLiveData(false)
    val commentSubmitted: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getTasks(projectId: Long): LiveData<List<TaskItem>> =
        taskInteractor.getProjectTasks(projectId)
            .catch { error -> handleError(error) }
            .map { it.toTaskItemList() }.asLiveData()

    fun getTask(taskId: Long): LiveData<TaskItem> = taskInteractor.getTask(taskId)
        .catch { error -> handleError(error) }
        .map { it.toTaskItem() }.asLiveData()


    fun getComments(taskId: Long): LiveData<List<CommentItem>> =
        taskInteractor.getTaskComments(taskId)
            .catch { error -> handleError(error) }
            .map { it.toCommentItemList() }.asLiveData()

    fun updateTask(id: Long, title: String, description: String) {
        if(title.isBlank() || description.isBlank()){
            handleError(UpdateTaskException())
            return
        }
        viewModelScope.launch {
            taskInteractor.updateTask(id, title, description)
                .catch { error -> handleError(error) }
                .onEach { taskEditState.value = true }.collect()
        }
    }

    fun submitTask(taskId: Long){
        viewModelScope.launch {
            taskInteractor.updateTaskStatus(taskId, TaskStatusEnum.UNDER_REVIEW)
                .catch { error -> handleError(error) }.collect()
        }
    }

    fun createTask(projectId: Long, title: String, description: String) {
        if(title.isBlank() || description.isBlank()){
            handleError(CreateTaskException())
            return
        }
        viewModelScope.launch {
            taskInteractor.createTask(projectId, title, description)
                .catch { error -> handleError(error) }
                .onEach { taskCreateState.value = true }.collect()
        }
    }

    fun addComment(taskId: Long, userName: String, comment: String) {
        if(userName.isEmpty() || comment.isEmpty()){
            handleError(CreateCommentException())
            return
        }
        viewModelScope.launch {
            taskInteractor.addCommentToTask(taskId, userName, comment)
                .onEach { commentSubmitted.value = true }
                .catch { error -> handleError(error) }.collect()
        }
    }

    private fun handleError(exception: Throwable?) {
        val error = when (exception) {
            is CreateTaskException -> context.getString(R.string.task_create_error)
            is UpdateTaskException -> context.getString(R.string.task_update_error)
            is UpdateTaskStatusException -> context.getString(R.string.task_update_status_error)
            is CreateCommentException -> context.getString(R.string.task_create_comment_error)
            else -> context.getString(R.string.default_error)
        }
        errorState.value = error
    }
}