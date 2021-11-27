package com.example.assigmentbypyramidion.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assigmentbypyramidion.api.ApiInterface
import com.example.assigmentbypyramidion.api.UserDataService
import com.example.assigmentbypyramidion.model.UserData

class Repository(private val userService: UserDataService) {

    suspend fun getUserData(): LiveData<UserData> {
        val data = MutableLiveData<UserData>()
        val response = userService.userInstance.getUserData().await()
        data.value = response
        return data
    }
}