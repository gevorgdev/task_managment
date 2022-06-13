package com.gev.task.ui.task.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gev.task.databinding.VhCommentItemBinding
import com.gev.task.helpers.DateHelper
import com.gev.task.ui.task.model.CommentItem

class CommentListAdapter : RecyclerView.Adapter<CommentListAdapter.CommentItemViewHolder>() {

    private var items: List<CommentItem> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(items: List<CommentItem>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentItemViewHolder {

        val binding = VhCommentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommentItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentItemViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount() = items.size

    class CommentItemViewHolder(val binding: VhCommentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var commentItem: CommentItem

        fun onBind(commentItem: CommentItem) {
            this.commentItem = commentItem
            setContent()
        }

        fun setContent() {
            binding.userName.text = commentItem.userName
            binding.comment.text = commentItem.content
            binding.creationDateText.text = DateHelper.dateToString(commentItem.createdAt)
        }
    }
}