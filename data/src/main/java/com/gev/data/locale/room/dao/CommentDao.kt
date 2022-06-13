package com.gev.data.locale.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.gev.data.locale.room.base.BaseDao
import com.gev.data.locale.room.entity.CommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao: BaseDao<CommentEntity> {

    @Query("SELECT * FROM `comments` ORDER BY `comments`.created_at DESC")
    fun findAll(): Flow<List<CommentEntity>>

    @Query("SELECT * FROM `comments` WHERE id = :id")
    fun findById(id: Long): Flow<CommentEntity>

    @Query("SELECT * FROM `comments` WHERE task_id = :taskId ORDER BY `comments`.created_at DESC")
    fun findByTaskId(taskId: Long): Flow<List<CommentEntity>>

    @Query("SELECT * FROM `comments` WHERE is_synchronised = :isSynchronized")
    fun findBySyncState(isSynchronized: Boolean): Flow<List<CommentEntity>>
}