package com.example.notelab.DB

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)
    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes() : LiveData<List<Note>>
    @Delete
    suspend fun deleteNote(note: Note)
    @Update
    suspend fun update(note: Note)


}