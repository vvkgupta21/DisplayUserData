package com.example.assigmentbypyramidion.viewmodel

import androidx.lifecycle.ViewModel
import com.example.assigmentbypyramidion.model.UserData
import com.example.assigmentbypyramidion.repository.Repository
import com.example.trucksmap.activity.StateLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repo: Repository): ViewModel() {

    private lateinit var viewModelJob: Job

    fun getUsersData(): StateLiveData<UserData> {
        val data = StateLiveData<UserData>()
        data.postLoading()
        viewModelJob = CoroutineScope(Job() + Dispatchers.Main).launch {
            try {
                val response = repo.getUserData()
                response.value?.let { data.postSuccess(it) }
            } catch (e: Exception) {
                data.postError(e)
            }
        }
        return data
    }
}