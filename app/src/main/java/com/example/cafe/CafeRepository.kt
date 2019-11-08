package com.example.cafe

import androidx.lifecycle.LiveData
import com.example.cafe.Database.CafeDataBaseDAO
import com.example.cafe.Database.CafeModels
import com.example.cafe.Database.SuggestDatabaseDAO
import com.example.cafe.Database.SuggestModels

class CafeControllerRepository(private val cafeDAO: CafeDataBaseDAO) {
    val allCafe: LiveData<List<CafeModels>> = cafeDAO.getAllCafe()

    fun insert(car: CafeModels) {
        cafeDAO.insert(car)
    }

    fun findByName(item: String): LiveData<List<CafeModels>> = cafeDAO.find(item)
}

class SuggestControllerRepository(private val suggestDAO: SuggestDatabaseDAO) {

    val allSuggest: LiveData<List<SuggestModels>> = suggestDAO.getAllSuggest()

    fun insert(suggest: SuggestModels) {
        suggestDAO.insert(suggest)
    }

    fun delete(item: String) {
        suggestDAO.delete(item)
    }

}