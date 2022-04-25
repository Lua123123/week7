package com.watasolutions.week7_t7.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class Users(
    @PrimaryKey
    var userName: String,
    var passWord: String
)