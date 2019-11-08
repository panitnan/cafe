package com.example.cafe.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_all_cafe")
data class CafeModels(
    @PrimaryKey

    @ColumnInfo(name = "nameThai")
    val nameThai: String,

    @ColumnInfo(name = "nameEng")
    val nameEng: String,

    @ColumnInfo(name = "detail")
    var detail: String,

    @ColumnInfo(name = "image")
    var image: String
)

@Entity(tableName = "all_suggest")
data class SuggestModels(
    @PrimaryKey

    @ColumnInfo(name = "nameThai")
    val nameThai: String,

    @ColumnInfo(name = "image")
    var image: String
)