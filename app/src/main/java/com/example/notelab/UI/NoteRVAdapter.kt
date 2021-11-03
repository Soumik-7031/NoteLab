package com.example.notelab.UI

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notelab.R
import com.example.notelab.DB.Note

class NoteRVAdapter(private val context: Context,
                    val noteClickInterface: NoteClickInterface): RecyclerView
.Adapter<NoteRVAdapter.NoteViewHolder>() {

     val allNotes = ArrayList<Note>()
    var arrList = ArrayList<Note>()
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView = itemView.findViewById<TextView>(R.id.tvTitle)
        val noteBody = itemView.findViewById<TextView>(R.id.tvDesc)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes.get(position)
        var color: String? = null
        holder.titleView.setText(currentNote.title)
        holder.noteBody.setText(currentNote.note)
        if(position%6 == 0) {
            color = "#ffd633"
        } else if(position%6 == 1) {
            color = "#ffffff"
        } else if(position%6 == 2) {
            color = "#ae3b76"
        } else if(position%6 == 3) {
            color = "#0aebaf"
        } else if(position%6 == 4) {
            color = "#5DADE2"
        } else if(position%6 == 5) {
            color = "#CD6155"
        }
        holder.titleView.setBackgroundColor(Color.parseColor(color))
        holder.noteBody .setBackgroundColor(Color.parseColor(color))
        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(currentNote)
        }

    }

    override fun getItemCount():Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }

}



interface NoteClickInterface {
    fun onNoteClick(note: Note)
}



