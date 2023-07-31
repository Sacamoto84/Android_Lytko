package com.example.lytko.widget

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.widget.Toast
import java.util.*

class RandomNumberService : Service() {

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Toast.makeText(this, "RandomNumberService Service started.", Toast.LENGTH_SHORT).show()
        println(".....................onStartCommand RandomNumberService")

        // Do a periodic task
        mHandler = Handler()
        mRunnable = Runnable { showRandomNumber() }
        mHandler.postDelayed(mRunnable, 5000)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Сервис RandomNumberService destroyed", Toast.LENGTH_SHORT).show()
        mHandler.removeCallbacks(mRunnable)
        println(".....................onDestroy RandomNumberService")
    }

    // Custom method to do a task
    private fun showRandomNumber() {
        val rand = Random()
        val number = rand.nextInt(100)
        Toast.makeText(this, "Random Number : $number", Toast.LENGTH_SHORT).show()
        mHandler.postDelayed(mRunnable, 5000)
        println(".....................Random Number : $number RandomNumberService")
    }

}