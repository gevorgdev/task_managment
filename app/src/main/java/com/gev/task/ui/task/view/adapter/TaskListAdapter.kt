package com.gev.task.ui.task.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gev.task.databinding.VhTaskItemBinding
import com.gev.task.helpers.DateHelper
import com.gev.task.ui.task.model.TaskItem

class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.TaskItemViewHolder>() {

    private var items: List<TaskItem> = arrayListOf()

    private var onItemClickListener : ((item: TaskItem) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(items: List<TaskItem>){
        this.items = items
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (item: TaskItem) -> Unit){
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {

        val binding = VhTaskItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        holder.onBind(items[position], onItemClickListener)
    }

    override fun getItemCount() = items.size

    class TaskItemViewHolder(val binding: VhTaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var taskItem: TaskItem

        fun onBind(taskItem: TaskItem, onItemClickListener: ((item: TaskItem) -> Unit)?) {
            this.taskItem = taskItem
            setContent()
            initOnClickListener(onItemClickListener)
        }

        fun initOnClickListener(onItemClickListener: ((item: TaskItem) -> Unit)?) {
            binding.root.setOnClickListener { onItemClickListener?.invoke(taskItem) }
        }

        fun setContent() {
            binding.taskTitle.text = taskItem.title
            binding.taskDescription.text = taskItem.description
            binding.creationDateText.text = DateHelper.dateToString(taskItem.createdAt)
        }
    }
}