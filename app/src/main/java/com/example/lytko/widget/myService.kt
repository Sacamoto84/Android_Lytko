package com.example.lytko.widget

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast

class MyService : Service() {


    // declaring object of MediaPlayer
    private lateinit var player: MediaPlayer



    override fun onBind(intent: Intent?): IBinder? = null


    //Тут запуск рутины
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(this::class.simpleName, "..............................Action Demo Service: $intent")
        Toast.makeText(this, "Сервис onStartCommand", Toast.LENGTH_SHORT).show()

        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        player.isLooping = false
        player.start()

        //return super.onStartCommand(intent, flags, startId)


        return START_STICKY


    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Служба onCreate", Toast.LENGTH_SHORT).show()
        println(".....................Запуск службы")

    }

    override fun onDestroy() {
        super.onDestroy()
        //Toast.makeText(this, "Service onDestroy", Toast.LENGTH_LONG).show()
        println(".....................onDestroy службы")
        //player.stop()
    }
}