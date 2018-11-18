package com.pixelart.week3weekend

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment2_layout.view.*

class Fragment2 : Fragment() {
    companion object {
        fun newInstance() = Fragment2()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment2_layout, container, false)

        view.tvCounter.text = "0"

        return view
    }

    var counterReceiver = object :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "counterBroadcast")
            {
                var counter = intent.getIntExtra("counter", 0)

                view?.tvCounter?.text =counter.toString()
                //Toast.makeText(context, "$counter", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter()
            intentFilter.addAction("counterBroadcast")

        context?.registerReceiver(counterReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        context?.unregisterReceiver(counterReceiver)
    }
}