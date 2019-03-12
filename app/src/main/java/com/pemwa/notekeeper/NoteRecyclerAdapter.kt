package com.pemwa.notekeeper

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class NoteRecyclerAdapter (private val context: Context,
                           private val notes: List<NoteInfo>):
    RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    private var onNoteSelectedListener : OnNoteSelectedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_note_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]

        holder.textCourse?.text = note.course?.courseTitle
        holder.textTitle?.text = note.noteTitle
        holder.notePosition = position
    }

    interface OnNoteSelectedListener {
        fun onNoteSelected(note: NoteInfo)
    }

    fun setOnSelectedListener(listener: OnNoteSelectedListener) {
        onNoteSelectedListener = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textCourse = itemView.findViewById<TextView?>(R.id.textCourse)
        val textTitle = itemView.findViewById<TextView?>(R.id.textTitle)

        var notePosition = 0

        init {
            itemView.setOnClickListener {
                onNoteSelectedListener?.onNoteSelected(notes[notePosition])
                val intent = Intent(context, NoteActivity::class.java)
                intent.putExtra(NOTE_POSITION, notePosition)
                context.startActivity(intent)
            }
        }

    }
}