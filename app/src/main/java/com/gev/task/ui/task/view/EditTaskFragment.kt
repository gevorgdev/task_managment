package com.gev.task.ui.task.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gev.task.R
import com.gev.task.base.fragment.BaseFragment
import com.gev.task.constants.BundleKeys
import com.gev.task.databinding.FragmentEditTaskBinding
import com.gev.task.ui.task.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditTaskFragment : BaseFragment(R.layout.fragment_edit_task) {

    // view models
    private val taskViewModel: TaskViewModel by viewModels()

    // View Binding
    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!

    // Data
    private var taskId: Long = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromArguments()
        setTaskData()
        initOnClickListeners()
        initObservers()
    }

    private fun setTaskData() {
        taskViewModel.getTask(taskId).observe(viewLifecycleOwner){
            binding.taskName.setText(it.title)
            binding.taskDescription.setText(it.description)
        }
    }

    private fun getDataFromArguments() {
        this.taskId = arguments?.getLong(BundleKeys.TASK_ID_KEY) ?: -1
    }

    private fun initObservers() {
        // Observe error state
        taskViewModel.errorState.observe(viewLifecycleOwner) { error ->
            error?.let { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() }
        }
        // Observe success state
        taskViewModel.taskEditState.observe(viewLifecycleOwner) {
            if(it) findNavController().navigateUp()
        }
    }

    private fun initOnClickListeners() {

        // On Create button clicked
        binding.editTaskBtn.setOnClickListener {
            taskViewModel.updateTask(
                taskId,
                binding.taskName.text.toString(),
                binding.taskDescription.text.toString()
            )
        }
    }
}