package com.gev.task.ui.project.view

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
import com.gev.task.databinding.FragmentProjectsListBinding
import com.gev.task.ui.project.view.adapter.ProjectListAdapter
import com.gev.task.ui.project.viewmodel.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectsListFragment : BaseFragment(R.layout.fragment_projects_list) {

    // view models
    private val projectViewModel: ProjectViewModel by viewModels()

    // View Binding
    private var _binding: FragmentProjectsListBinding? = null
    private val binding get() = _binding!!

    // Adapters
    private lateinit var projectListAdapter: ProjectListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProjectsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapter()
        initOnClickListeners()
        loadProjects()
    }

    private fun loadProjects() {
        // Observe to projects livedata
        projectViewModel.getProjects().observe(viewLifecycleOwner) {
            projectListAdapter.submitData(it)
        }
    }

    private fun initRecyclerViewAdapter() {
        projectListAdapter = ProjectListAdapter()
        binding.projectListRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.projectListRecyclerView.adapter = projectListAdapter
    }

    private fun initOnClickListeners() {
        // On project item clicked
        projectListAdapter.setOnItemClickListener {
            findNavController().navigate(R.id.action_projectsListFragment_to_taskListFragment,
                bundleOf(Pair(PROJECT_ID_KEY, it.id)))
        }
        // On add item clicked
        binding.creteNewProjectBtn.setOnClickListener {
            // Navigate to create project screen
            findNavController().navigate(R.id.action_projectsListFragment_to_createProjectFragment)
        }
    }
}