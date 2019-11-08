package com.example.cafe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cafe.Database.CafeDatabase
import com.example.cafe.Database.CafeModels
import com.example.cafe.Database.SuggestDatabase
import com.example.cafe.Database.SuggestModels
import kotlinx.coroutines.launch

class CafeControllerModels(application: Application) : AndroidViewModel(application) {
    private val repositoryOne: CafeControllerRepository
    val allListCafe: LiveData<List<CafeModels>>

    init {
        val foodListDao = CafeDatabase.getDatabase(application.applicationContext).listCafeDAO()
        repositoryOne = CafeControllerRepository(foodListDao)
        allListCafe = repositoryOne.allCafe
    }

    fun insert(food: CafeModels) = viewModelScope.launch {
        repositoryOne.insert(food)
    }

    fun find(item: String) = repositoryOne.findByName(item)
}

class SuggestControllerModels(application: Application) : AndroidViewModel(application) {
    private val repositoryTwo: SuggestControllerRepository
    val allSuggest: LiveData<List<SuggestModels>>

    init {
        val foodListDao = SuggestDatabase.getDatabase(application.applicationContext).listSuggestDAO()
        repositoryTwo = SuggestControllerRepository(foodListDao)
        allSuggest = repositoryTwo.allSuggest
    }

    fun insert(suggest: SuggestModels) = viewModelScope.launch {
        repositoryTwo.insert(suggest)
    }

    fun delete(item: String) = viewModelScope.launch {
        repositoryTwo.delete(item)
    }
}