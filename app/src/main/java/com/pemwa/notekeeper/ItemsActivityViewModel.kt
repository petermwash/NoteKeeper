package com.pemwa.notekeeper

import android.arch.lifecycle.ViewModel

class ItemsActivityViewModel : ViewModel() {

    var navDrawerDisplaySelection = R.id.nav_notes

    private val maxRecentlyViewedNotes = 5
    var recentlyViewedNotes = ArrayList<NoteInfo>(maxRecentlyViewedNotes)

    fun addToRecentlyViewedNotes(note: NoteInfo){
        //Checking if the selection exists in the list
        val existingIndex = recentlyViewedNotes.indexOf(note)
        if (existingIndex == -1) {
            //it isn't in the list...
            //Add a new one in the beginning of the list and remove any beyond max we want to keep
            recentlyViewedNotes.add(0, note)
            for (index in recentlyViewedNotes.lastIndex downTo maxRecentlyViewedNotes)
                recentlyViewedNotes.removeAt(index)
        }else{
            //it is in the list...
            //Shift the ones above down the list and make it first member of the list
            for (index in (existingIndex - 1) downTo 0)
                recentlyViewedNotes[index + 1] = recentlyViewedNotes[index]
            recentlyViewedNotes[0] = note
        }
    }
}