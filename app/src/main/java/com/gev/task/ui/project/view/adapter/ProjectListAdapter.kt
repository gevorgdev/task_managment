package com.gev.task.ui.project.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gev.task.databinding.VhProjectItemBinding
import com.gev.task.helpers.DateHelper
import com.gev.task.ui.project.model.ProjectItem

class ProjectListAdapter : RecyclerView.Adapter<ProjectListAdapter.ProjectItemViewHolder>() {

    private var items: List<ProjectItem> = arrayListOf()

    private var onItemClickListener : ((item: ProjectItem) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(items: List<ProjectItem>){
        this.items = items
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (item: ProjectItem) -> Unit){
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectItemViewHolder {

        val binding = VhProjectItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProjectItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectItemViewHolder, position: Int) {
        holder.onBind(items[position], onItemClickListener)
    }

    override fun getItemCount() = items.size

    class ProjectItemViewHolder(val binding: VhProjectItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var projectItem: ProjectItem

        fun onBind(projectItem: ProjectItem, onItemClickListener: ((item: ProjectItem) -> Unit)?) {
            this.projectItem = projectItem
            setContent()
            initOnClickListener(onItemClickListener)
        }

        fun initOnClickListener(onItemClickListener: ((item: ProjectItem) -> Unit)?) {
            binding.root.setOnClickListener { onItemClickListener?.invoke(projectItem) }
        }

        fun setContent() {
            binding.projectTitle.text = projectItem.title
            binding.projectDescription.text = projectItem.description
            binding.creationDateText.text = DateHelper.dateToString(projectItem.createdAt)
        }
    }
}