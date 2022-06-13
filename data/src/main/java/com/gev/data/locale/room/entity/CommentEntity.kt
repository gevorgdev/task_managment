package com.gev.data.locale.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.gev.data.locale.room.base.BaseEntity

@Entity(
    tableName = "comments",
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = ["id"],
            childColumns = ["task_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class CommentEntity(
    @ColumnInfo(name = "user_name")
    val userName: String = "User664",
    @ColumnInfo(name = "content", )
    val content: String,
    @ColumnInfo(name = "task_id")
    val taskId: Long
): BaseEntity()