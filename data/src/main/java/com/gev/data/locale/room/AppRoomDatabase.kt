package com.gev.data.locale.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gev.data.locale.room.dao.CommentDao
import com.gev.data.locale.room.dao.ProjectDao
import com.gev.data.locale.room.dao.TaskDao
import com.gev.data.locale.room.entity.CommentEntity
import com.gev.data.locale.room.entity.ProjectEntity
import com.gev.data.locale.room.entity.TaskEntity

@Database(entities = [ProjectEntity::class, TaskEntity::class, CommentEntity::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun taskDao(): TaskDao
    abstract fun commentDao(): CommentDao
}