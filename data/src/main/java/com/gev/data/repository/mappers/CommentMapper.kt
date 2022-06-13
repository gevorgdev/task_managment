package com.gev.data.repository.mappers

import com.gev.data.locale.room.entity.CommentEntity
import com.gev.domain.model.Comment

fun Comment.toCommentEntity(isSynchronised: Boolean? = null): CommentEntity {

    val entity = CommentEntity(
        this.userName,
        this.content,
        this.taskId
    )
    entity.id = this.id
    entity.isSynchronised = isSynchronised?:this.isSynchronized
    return entity
}

fun CommentEntity.toComment(): Comment {

    return Comment(
        this.id,
        this.userName,
        this.content,
        this.taskId,
        this.createdAt,
        this.isSynchronised
    )
}

fun List<CommentEntity>.toCommentList(): List<Comment> = this.map { it.toComment() }