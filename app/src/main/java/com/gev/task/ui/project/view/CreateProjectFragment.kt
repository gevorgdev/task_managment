package com.gev.task.ui.project.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gev.task.R
import com.gev.task.base.fragment.BaseFragment
import com.gev.task.databinding.FragmentCreateProjectBinding
import com.gev.task.ui.project.viewmodel.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateProjectFragment : BaseFragment(R.layout.fragment_create_project) {

    // view models
    private val projectViewModel: ProjectViewModel by viewModels()

    // View Binding
    private var _binding: FragmentCreateProjectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreateProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClickListeners()
        initObservers()
    }

    private fun initObservers() {
        // Observe error state
        projectViewModel.errorState.observe(viewLifecycleOwner) { error ->
            error?.let { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() }
        }
        // Observe success state
        projectViewModel.projectCreateState.observe(viewLifecycleOwner) {
            it?.let { findNavController().navigateUp() }
        }
    }


    private fun initOnClickListeners() {

        // On Create button clicked
        binding.createProjectBtn.setOnClickListener {
            projectViewModel.createProject(
                binding.projectName.text.toString(),
                binding.projectDescription.text.toString()
            )
        }
    }
}