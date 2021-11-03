package com.example.notelab.DB

import androidx.lifecycle.LiveData


class NoteRapository(private val noteDao: NoteDao) {

    val getAllNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insertNote(note: Note) {
        noteDao.addNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    suspend fun update(note: Note){
        noteDao.update(note)
    }


}