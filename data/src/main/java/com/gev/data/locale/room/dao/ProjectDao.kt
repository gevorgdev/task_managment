package com.gev.data.locale.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.gev.data.locale.room.base.BaseDao
import com.gev.data.locale.room.entity.ProjectEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ProjectDao: BaseDao<ProjectEntity> {

    @Query("SELECT * FROM `projects` ORDER BY `projects`.created_at DESC")
    fun findAll(): Flow<List<ProjectEntity>>

    @Query("SELECT * FROM `projects` WHERE id = :id")
    fun findById(id: Long): Flow<ProjectEntity>

    @Query("SELECT * FROM `projects` WHERE is_synchronised = :isSynchronized")
    fun findBySyncState(isSynchronized: Boolean): Flow<List<ProjectEntity>>
}