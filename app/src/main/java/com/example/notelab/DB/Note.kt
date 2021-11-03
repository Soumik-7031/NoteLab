package com.example.notelab.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
data class Note(
    val title: String,
    val note: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

