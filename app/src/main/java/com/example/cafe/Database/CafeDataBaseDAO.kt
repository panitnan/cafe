package com.example.cafe.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CafeDataBaseDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(food: CafeModels)

    @Query("SELECT * FROM list_all_cafe ORDER BY nameThai ASC")
    fun getAllCafe(): LiveData<List<CafeModels>>

    @Query("SELECT * FROM list_all_cafe WHERE nameThai = :name ")
    fun getOneCafe(name: String): CafeModels

    @Query("SELECT * FROM list_all_cafe WHERE nameThai LIKE :name LIMIT 1")
    fun find(name: String): LiveData<List<CafeModels>>

}

@Dao
interface SuggestDatabaseDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(suggest: SuggestModels)

    @Query("SELECT * FROM all_suggest ORDER BY nameThai ASC")
    fun getAllSuggest(): LiveData<List<SuggestModels>>

    @Query("DELETE  FROM all_suggest WHERE nameThai=:name")
    fun delete(name: String)


}