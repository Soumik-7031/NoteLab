package com.example.notelab.DB

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes : LiveData<List<Note>>
    private val rapository : NoteRapository
    init {
        val dao = NoteDataBase.getDatabase(application).noteDao()
        rapository = NoteRapository(dao)
        allNotes = rapository.getAllNotes
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        rapository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        rapository.update(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        rapository.deleteNote(note)
    }

    fun getAllNotes() = viewModelScope.launch(Dispatchers.IO) {
        rapository.getAllNotes
    }
}