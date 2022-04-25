package com.watasolutions.week7_t7.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private var id: Int? = null
    private var userName: String? = null
    private var passWord: String? = null

    constructor(userName: String?, passWord: String?) {
        this.userName = userName
        this.passWord = passWord
    }

}
