package com.gev.data.locale.room.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface BaseDao<T> {

    /**
     * Insert an object in the database.
     * @param entity the object to be inserted.
     */
    @Insert
    fun insert(entity: T): Long

    /**
     * Insert an array of objects in the database.
     * @param entities the objects to be inserted.
     */
    @Insert
    fun insert(entities: List<T>)

    /**
     * Update an object from the database.
     * @param entity the object to be updated
     */
    @Update
    fun update(entity: T)

    /**
     * Delete an object from the database
     * @param entity the object to be deleted
     */
    @Delete
    fun delete(entity: T)
}