package com.gev.data.locale.room.base

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gev.data.locale.room.converters.DateConverter
import java.util.*

open class BaseEntity(
    @ColumnInfo(name = "created_at")
    @field:TypeConverters(DateConverter::class)
    var createdAt: Date = Date(),
    @ColumnInfo(name = "updated_at")
    @field:TypeConverters(DateConverter::class)
    var updatedAt: Date = Date(),
    @ColumnInfo(name = "is_synchronised")
    var isSynchronised: Boolean = false
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}