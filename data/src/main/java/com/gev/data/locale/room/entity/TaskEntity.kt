package com.gev.data.locale.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.TypeConverters
import com.gev.data.locale.room.base.BaseEntity
import com.gev.data.locale.room.converters.TaskStatusConverter
import com.gev.domain.model.enums.TaskStatusEnum

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = ProjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["project_id"],
            onDelete = CASCADE
        )
    ]
)
data class TaskEntity(
    @ColumnInfo(name = "project_id")
    val projectId: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "status")
    @field:TypeConverters(TaskStatusConverter::class)
    val status: TaskStatusEnum = TaskStatusEnum.NEW
): BaseEntity()