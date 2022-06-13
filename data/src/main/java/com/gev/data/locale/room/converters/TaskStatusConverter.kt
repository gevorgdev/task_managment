package com.gev.data.locale.room.converters

import androidx.room.TypeConverter
import com.gev.domain.model.enums.TaskStatusEnum

object TaskStatusConverter {
    
    @TypeConverter
    fun toStatusEnum(status: Int?): TaskStatusEnum {

        return when(status){
            0 -> TaskStatusEnum.NEW
            1 -> TaskStatusEnum.IN_PROGRESS
            2 -> TaskStatusEnum.UNDER_REVIEW
            3 -> TaskStatusEnum.REOPEN
            else -> TaskStatusEnum.UNKNOWN
        }
    }

    @TypeConverter
    fun fromStatusEnum(status: TaskStatusEnum?): Int {

        return when(status){
            TaskStatusEnum.NEW  -> 0
            TaskStatusEnum.IN_PROGRESS -> 1
            TaskStatusEnum.UNDER_REVIEW -> 2
            TaskStatusEnum.REOPEN -> 3
            else -> -1
        }
    }
}

