package com.example.todo_jetpackedition

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "DetailTable")
data class Data (
    @PrimaryKey(autoGenerate = true)val id: Int = 0,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "subtitle")val subtitle: String,
    @ColumnInfo(name = "notes")val notes: String,
    @ColumnInfo(name = "color")val color: String
)