package com.example.notelab.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notelab.DB.Note
import com.example.notelab.DB.NoteViewModel
import com.example.notelab.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), NoteClickInterface {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        val noteRVAdapter = NoteRVAdapter(this, this)
        recyclerView.adapter = noteRVAdapter


        viewModel = ViewModelProvider(this, ViewModelProvider
                .AndroidViewModelFactory.getInstance(application))
                .get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                noteRVAdapter.updateList(it)
                if(it.isEmpty()) {
                    imageView.isVisible = true
                    textView.isVisible = true
                    recyclerView.isVisible = false
                } else {
                    imageView.isVisible = false
                    textView.isVisible = false
                    recyclerView.isVisible = true
                }
            }
        })






        btAddNote.setOnClickListener {
            Intent(this@MainActivity, AddNoteActivity::class.java).let {
                startActivity(it)
            }
        }





    }

    override fun onNoteClick(note: Note) {
        Intent(this@MainActivity, AddNoteActivity::class.java).let {
            it.putExtra("noteType", "Edit")
            it.putExtra("noteTitle", note.title)
            it.putExtra("noteDescription", note.note)
            it.putExtra("noteId", note.id)
            startActivity(it)
        }

    }



}