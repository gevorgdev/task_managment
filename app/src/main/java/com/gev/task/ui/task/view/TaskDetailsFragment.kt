package com.gev.task.ui.task.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gev.domain.model.enums.TaskStatusEnum
import com.gev.domain.model.enums.getStringName
import com.gev.task.R
import com.gev.task.base.fragment.BaseFragment
import com.gev.task.constants.BundleKeys
import com.gev.task.databinding.FragmentTaskDetailsBinding
import com.gev.task.helpers.DateHelper
import com.gev.task.ui.task.model.TaskItem
import com.gev.task.ui.task.view.adapter.CommentListAdapter
import com.gev.task.ui.task.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskDetailsFragment : BaseFragment(R.layout.fragment_task_details) {

    // view models
    private val taskViewModel: TaskViewModel by viewModels()

    // View Binding
    private var _binding: FragmentTaskDetailsBinding? = null
    private val binding get() = _binding!!

    // Data
    private var taskId: Long = -1

    // Adapters
    private lateinit var commentListAdapter: CommentListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTaskDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromArguments()
        initRecyclerViewAdapter()
        initObservers()
        getTaskInfo()
        getTaskComments()
        initOnClickListeners()
    }

    private fun initObservers() {
        taskViewModel.commentSubmitted.observe(viewLifecycleOwner) { submitted ->
            // If submitted emty comment imputs
            if (submitted) {
                binding.userName.setText("")
                binding.commentBody.setText("")
            }
        }
    }

    private fun initRecyclerViewAdapter() {
        commentListAdapter = CommentListAdapter()
        binding.commentListRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.commentListRecyclerView.adapter = commentListAdapter
    }

    private fun getTaskInfo() {
        taskViewModel.getTask(taskId).observe(viewLifecycleOwner) {
            configureUI(it)
        }
    }

    private fun configureUI(task: TaskItem) {
        // Set task info
        binding.taskName.text = task.title
        binding.createdDate.text =
            getString(R.string.create_detail_date_label, DateHelper.dateToString(task.createdAt))
        binding.taskDescription.text = task.description
        binding.taskStatus.text = getString(R.string.task_status_txt, task.status.getStringName())
        binding.submitTaskBtn.isEnabled = task.status != TaskStatusEnum.UNDER_REVIEW
    }

    private fun getTaskComments() {
        taskViewModel.getComments(taskId).observe(viewLifecycleOwner) {
            commentListAdapter.submitData(it)
        }
    }

    private fun getDataFromArguments() {
        this.taskId = arguments?.getLong(BundleKeys.TASK_ID_KEY) ?: 0
    }

    private fun initOnClickListeners() {

        // On edit task button clicked
        binding.editTaskBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_taskDetailsFragment_to_editTaskFragment,
                bundleOf(Pair(BundleKeys.TASK_ID_KEY, taskId))
            )
        }

        // On post comment clicked
        binding.postBommentBtn.setOnClickListener {
            taskViewModel.addComment(
                taskId,
                binding.userName.text.toString(),
                binding.commentBody.text.toString())
        }

        // Submit task
        binding.submitTaskBtn.setOnClickListener {
            taskViewModel.submitTask(taskId)
        }
    }
}