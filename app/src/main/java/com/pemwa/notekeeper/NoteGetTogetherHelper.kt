package com.pemwa.notekeeper

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.util.Log
import com.pemwa.notekeeper.util.PseudoLocationManager

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

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startHandler(){
        locManager.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopHandler(){
        locManager.stop()
    }
}