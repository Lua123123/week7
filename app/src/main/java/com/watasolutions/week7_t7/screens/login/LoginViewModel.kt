package com.watasolutions.week7_t7.screens.login

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Dao
import com.watasolutions.week7_t7.MySharedPreferences
import com.watasolutions.week7_t7.database.UserDatabase
import com.watasolutions.week7_t7.model.User
import com.watasolutions.week7_t7.model.Users


class LoginViewModel(val sharedPrefs: MySharedPreferences) : ViewModel() {

    private var _saveEventSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val saveEventSuccess: LiveData<Boolean>
        get() = _saveEventSuccess

    private var _loadUserEvent: MutableLiveData<User> = MutableLiveData()
    val loadUserEvent: LiveData<User>
        get() = _loadUserEvent

    fun saveUserInfo(name: String, password: String) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
            return
        }
        var user: Users = Users(name, password)

//        sharedPrefs.saveUsername(name)
//        sharedPrefs.savePassword(password)
        _saveEventSuccess.value = true
    }

    fun loadUserInfo() {
        val username = sharedPrefs.getUsername()
        val password = sharedPrefs.getPassword()
        _loadUserEvent.postValue(User(username ?: "", password ?: ""))
    }
}