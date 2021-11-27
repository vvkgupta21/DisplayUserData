package com.example.assigmentbypyramidion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assigmentbypyramidion.repository.Repository
import com.example.assigmentbypyramidion.viewmodel.MainViewModel

class ViewModelFactory(private val repo: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}