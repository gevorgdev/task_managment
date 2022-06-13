package com.gev.task.ui.task.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gev.task.R
import com.gev.task.base.fragment.BaseFragment
import com.gev.task.constants.BundleKeys.PROJECT_ID_KEY
import com.gev.task.constants.BundleKeys.TASK_ID_KEY
import com.gev.task.databinding.FragmentTaskListBinding
import com.gev.task.ui.project.viewmodel.ProjectViewModel
import com.gev.task.ui.task.view.adapter.TaskListAdapter
import com.gev.task.ui.task.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListFragment : BaseFragment(R.layout.fragment_task_list) {

    // view models
    private val projectViewModel: ProjectViewModel by viewModels()
    private val taskViewModel: TaskViewModel by viewModels()

    // View Binding
    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    // Data
    private var projectId: Long = -1

    // Adapters
    private lateinit var taskListAdapter: TaskListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromArguments()
        initRecyclerViewAdapter()
        getProjectInfo()
        getProjectTasks()
        initOnClickListeners()
    }

    private fun initRecyclerViewAdapter() {
        taskListAdapter = TaskListAdapter()
        binding.taskListRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.taskListRecyclerView.adapter = taskListAdapter
    }

    private fun getProjectInfo() {

        projectViewModel.getProject(projectId).observe(viewLifecycleOwner){
            binding.projectName.text = it.title
            binding.projectDescription.text = it.description
        }
    }

    private fun getProjectTasks() {
        taskViewModel.getTasks(projectId).observe(viewLifecycleOwner){
            taskListAdapter.submitData(it)
        }
    }

    private fun getDataFromArguments() {
        this.projectId = arguments?.getLong(PROJECT_ID_KEY) ?: 0
    }

    private fun initOnClickListeners() {

        // On create task button clicked
        binding.createTaskBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_taskListFragment_to_createTaskFragment,
                bundleOf(Pair(PROJECT_ID_KEY, projectId))
            )
        }

        // On Task item clicked
        taskListAdapter.setOnItemClickListener {
            findNavController().navigate(
                R.id.action_taskListFragment_to_taskDetailsFragment,
                bundleOf(Pair(TASK_ID_KEY, it.id))
            )
        }
    }
}