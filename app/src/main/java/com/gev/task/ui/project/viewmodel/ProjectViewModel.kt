package com.gev.task.ui.project.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.gev.domain.excaptions.CreateProjectException
import com.gev.domain.interactors.ProjectInteractor
import com.gev.task.R
import com.gev.task.ui.project.model.ProjectItem
import com.gev.task.ui.project.viewmodel.mapper.toProjectItem
import com.gev.task.ui.project.viewmodel.mapper.toProjectItemList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val projectInteractor: ProjectInteractor,
    val context: Application,
) : AndroidViewModel(context) {

    // Error state
    val errorState: MutableLiveData<String?> = MutableLiveData(null)

    // Create project state
    val projectCreateState: MutableLiveData<ProjectItem?> = MutableLiveData(null)

    fun getProject(projectId: Long): LiveData<ProjectItem> =
        projectInteractor.getProject(projectId)
            .catch { error -> handleError(error) }
            .map { it.toProjectItem() }.asLiveData()

    fun getProjects(): LiveData<List<ProjectItem>> = projectInteractor.getAllProjects()
        .catch { error -> handleError(error) }
        .map { it.toProjectItemList() }.asLiveData()

    fun createProject(
        title: String,
        description: String,
    ) {
        if(title.isBlank() || description.isBlank()){
            handleError(CreateProjectException())
            return
        }
        viewModelScope.launch {
            projectInteractor.createProject(title, description).onEach { project ->
                projectCreateState.value = project.toProjectItem()
            }.catch { error -> handleError(error) }.collect()
        }
    }

    private fun handleError(exception: Throwable?) {
        val error = when (exception) {
            is CreateProjectException -> context.getString(R.string.project_create_error)
            else -> context.getString(R.string.default_error)
        }
        errorState.value = error
    }
}