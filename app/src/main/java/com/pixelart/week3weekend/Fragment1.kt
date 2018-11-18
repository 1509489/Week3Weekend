package com.pixelart.week3weekend

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment1_layout.*
import java.util.*

class Fragment1 : Fragment() {
   private val TAG = "Fragment1"
    lateinit var timer: Timer
    private var counter: Int = 0

    companion object {
        fun newInstance() = Fragment1()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment1_layout, container, false)
    }

    override fun onResume() {
        super.onResume()

        btnStart.setOnClickListener {
            timer = Timer()
            val counterTask = CounterTask()
            timer.schedule(counterTask, 1000, 1000)
            Toast.makeText(context, "Start Clicked", Toast.LENGTH_SHORT).show()
        }

        btnStop.setOnClickListener {
            if (timer != null)
            {
                timer.cancel()
                timer.purge()
                counter = 0
            }
            Toast.makeText(context, "Stop Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    inner class CounterTask : TimerTask() {

        private var hasStarted = false


        private val broadcastIntent = Intent()

        override fun run() {
            this.hasStarted = true
            counter++

            broadcastIntent.action = "counterBroadcast"
            broadcastIntent.putExtra("counter", counter)
            context?.sendBroadcast(broadcastIntent)

            Log.d(TAG, "counter: $counter")

        }
    }
}