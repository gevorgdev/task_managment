package com.gev.task.ui.task.viewmodel.mapper

import com.gev.domain.model.Comment
import com.gev.domain.model.Task
import com.gev.task.ui.task.model.CommentItem
import com.gev.task.ui.task.model.TaskItem

fun Task.toTaskItem(): TaskItem {

    return TaskItem(
        this.id,
        this.projectId,
        this.title,
        this.description,
        this.status,
        this.createdAt
    )
}

fun List<Task>.toTaskItemList(): List<TaskItem> = this.map { it.toTaskItem() }

fun TaskItem.toTask(): Task {

    return Task(
        this.id,
        this.projectId,
        this.title,
        this.description,
        this.status,
        this.createdAt
    )
}

fun List<TaskItem>.toProjectList(): List<Task> = this.map { it.toTask() }

fun Comment.toCommentItem(): CommentItem {

    return CommentItem(
        this.id,
        this.userName,
        this.content,
        this.taskId,
        this.createdAt
    )
}

fun List<Comment>.toCommentItemList(): List<CommentItem> = this.map { it.toCommentItem() }