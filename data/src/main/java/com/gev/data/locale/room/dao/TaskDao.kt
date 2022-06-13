package com.gev.data.locale.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.gev.data.locale.room.base.BaseDao
import com.gev.data.locale.room.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao: BaseDao<TaskEntity> {

    @Query("SELECT * FROM `tasks` ORDER BY `tasks`.created_at DESC")
    fun findAll(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM `tasks` WHERE id = :id")
    fun findById(id: Long): Flow<TaskEntity>

    @Query("SELECT * FROM `tasks` WHERE project_id = :projectId ORDER BY `tasks`.created_at DESC")
    fun findByProjectId(projectId: Long): Flow<List<TaskEntity>>

    @Query("SELECT * FROM `tasks` WHERE is_synchronised = :isSynchronized")
    fun findBySyncState(isSynchronized: Boolean): Flow<List<TaskEntity>>

    @Query("UPDATE `tasks` SET `status` = :statusEnum, `is_synchronised` = 0 WHERE id = :id")
    fun updateTaskStatus(id: Long, statusEnum: Int)
}