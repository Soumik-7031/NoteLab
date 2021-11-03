package com.example.notelab.UI

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.notelab.DB.Note
import com.example.notelab.DB.NoteViewModel
import com.example.notelab.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_add_note.*


class AddNoteActivity : AppCompatActivity() {

    lateinit var viewModel: NoteViewModel
    var noteId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)


        viewModel = ViewModelProvider(
            this, ViewModelProvider
                .AndroidViewModelFactory.getInstance(application)
        )
            .get(NoteViewModel::class.java)

        val notetype = intent.getStringExtra("noteType")
        if (notetype != null && notetype.equals("Edit")) {
            val title = intent.getStringExtra("noteTitle")
            val body = intent.getStringExtra("noteDescription")
            noteId = intent.getIntExtra("noteId", -1)
            noteTitle.setText(title)
            noteDesc.setText(body)
        }

        btDelete.isVisible = notetype.equals("Edit")


        btDone.setOnClickListener {
            val noteTitle: String = noteTitle.text.toString()
            val noteBody: String = noteDesc.text.toString()
            if (notetype != null && notetype.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteBody.isNotEmpty()) {
                    val updateNote = Note(noteTitle, noteBody)
                    updateNote.id = noteId
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_SHORT).show()
                    this.finish()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteBody.isNotEmpty()) {
                    val note = Note(noteTitle, noteBody)
                    viewModel.insertNote(note)
                    Toast.makeText(this, "Note Saved!", Toast.LENGTH_SHORT).show()
                    finish()
                } else if(noteTitle.isEmpty() && noteBody.isEmpty()) {
                    Toast.makeText(this, "Note title & description missing!", Toast.LENGTH_SHORT).show()
                    finish()
                } else if(!noteTitle.isNotEmpty()){
                    Toast.makeText(this, "Note Title required!", Toast.LENGTH_SHORT).show()
                } else if(!noteBody.isNotEmpty()) {
                    Toast.makeText(this, "Note description required!", Toast.LENGTH_SHORT).show()
                }
            }
        }


        btDelete.setOnClickListener {
            val noteTitle: String = noteTitle.text.toString()
            val noteBody: String = noteDesc.text.toString()
                    val deleteNote = Note(noteTitle, noteBody)
                    deleteNote.id = noteId
            MaterialAlertDialogBuilder(this,R.style.AlertDialogTheme)
                    .setMessage("Are you sure you want to delete this Note?")
                    .setPositiveButton("Delete") { dialog, which ->
                        viewModel.deleteNote(deleteNote)
                            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
                                this.finish()
                    }
                    .setNegativeButton("Cancel") { dialog, which ->

                    }
                    .show()
        }



        btBack.setOnClickListener {
            this.finish()
        }


    }
}