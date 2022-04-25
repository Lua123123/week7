package com.watasolutions.week7_t7.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.watasolutions.week7_t7.model.Users

@Dao
interface DAO {
    @Insert
    fun insertUser(user: Users?)

    @Query("SELECT * FROM user")
    fun listUser() : List<Users?>?
}