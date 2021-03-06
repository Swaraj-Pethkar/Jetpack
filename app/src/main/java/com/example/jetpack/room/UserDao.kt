package com.example.jetpack.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Update


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user:User)

    @Query("SELECT * FROM tbluser")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

}