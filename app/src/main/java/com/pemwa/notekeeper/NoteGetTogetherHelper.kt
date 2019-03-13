package com.pemwa.notekeeper

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.util.Log
import com.pemwa.notekeeper.model.NoteInfo
import com.pemwa.notekeeper.util.PseudoLocationManager
import com.pemwa.notekeeper.util.PseudoMessagingConnection
import com.pemwa.notekeeper.util.PseudoMessagingManager

class NoteGetTogetherHelper(val context: Context, val lifecycle: Lifecycle) :LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }


    val tag = this::class.simpleName
    var currentLat = 0.0
    var currentLon = 0.0

    var locManager = PseudoLocationManager(context) { lat, lon ->
        currentLat = lat
        currentLon = lon
        Log.d(tag, "Location Callback Lat:$currentLat Lon$currentLon")
    }

    val msgManager = PseudoMessagingManager(context)
    var msgConnection: PseudoMessagingConnection? = null

    fun sendMessage(note: NoteInfo) {
        val getTogetherMsg = "$currentLat | $currentLon | ${note.noteTitle} | ${note.course?.courseTitle}"
        msgConnection?.send(getTogetherMsg)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startHandler(){
        locManager.start()
        msgManager.connect { connection ->
            Log.d(tag, "Connection callback!! - Lifecycle state:${lifecycle.currentState}")
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))
                msgConnection = connection
            else
                connection.disconnect()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopHandler(){
        locManager.stop()
        msgConnection?.disconnect()
    }
}