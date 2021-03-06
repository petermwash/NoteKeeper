package com.pemwa.notekeeper.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.pemwa.notekeeper.NoteGetTogetherHelper
import com.pemwa.notekeeper.R
import com.pemwa.notekeeper.model.CourseInfo
import com.pemwa.notekeeper.model.DataManager
import com.pemwa.notekeeper.model.NoteInfo
import com.pemwa.notekeeper.util.NOTE_POSITION
import com.pemwa.notekeeper.util.POSITION_NOT_SET
import com.pemwa.notekeeper.util.PseudoLocationManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_items.*

class NoteActivity : AppCompatActivity() {

    private var notePosition = POSITION_NOT_SET
    private val TAG = this::class.simpleName

//    private val locManager = PseudoLocationManager(this) { lat, lon ->
//        Log.d(TAG, "Location Callback Lat:$lat Lon:$lon")
//    }

    val noteGetTogetherHelper = NoteGetTogetherHelper(this, lifecycle)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapterCourses = ArrayAdapter<CourseInfo>(this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerCourses.adapter = adapterCourses

        notePosition = savedInstanceState?.getInt(
            NOTE_POSITION,
            POSITION_NOT_SET
        ) ?:
            intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)
        if (notePosition != POSITION_NOT_SET)
            displayNote()

        else {
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex
        }

    }

//    override fun onStart() {
//        super.onStart()
//        locManager.start()
//    }
//
//    override fun onStop() {
//        locManager.stop()
//        super.onStop()
//    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(NOTE_POSITION, notePosition)
    }

    private fun displayNote() {
        val note = DataManager.notes[notePosition]
        textNoteTitle.setText(note.noteTitle)
        textNoteText.setText(note.noteText)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        spinnerCourses.setSelection(coursePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_message -> {
                noteGetTogetherHelper.sendMessage(DataManager.notes[notePosition])
                true
            }
            R.id.action_next -> {
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        ++notePosition
        displayNote()
        invalidateOptionsMenu()
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (notePosition >= DataManager.notes.lastIndex) {
            val menuItem = menu?.findItem(R.id.action_next)
            if (menuItem != null) {
                menuItem.icon = getDrawable(R.drawable.ic_block_white_24dp)
                menuItem.isEnabled = false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()

        saveNote()
    }

    private fun saveNote() {
        val note = DataManager.notes[notePosition]

        if (textNoteText.text != null && textNoteTitle.text != null) {
            note.noteTitle = textNoteTitle.text.toString()
            note.noteText = textNoteText.text.toString()
            note.course = spinnerCourses.selectedItem as CourseInfo
        } else {
            Snackbar.make(textView, "You didn't provide any data", Snackbar.LENGTH_SHORT).show()
        }

    }
}
