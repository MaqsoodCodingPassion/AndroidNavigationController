package com.servicenow.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.servicenow.GamesListRepository
import com.servicenow.GamesListViewModel
import javax.inject.Inject

class ViewModelFactory
    @Inject constructor(private val repository: GamesListRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(GamesListViewModel::class.java) -> GamesListViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel Class ${modelClass.name}")
        }
    }
}