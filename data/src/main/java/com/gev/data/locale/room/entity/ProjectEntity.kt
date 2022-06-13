package com.gev.data.locale.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.gev.data.locale.room.base.BaseEntity

@Entity(tableName = "projects")
data class ProjectEntity(
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String
): BaseEntity()