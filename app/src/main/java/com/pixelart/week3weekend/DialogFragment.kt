package com.pixelart.week3weekend

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_fragment_layout.view.*

class DialogFragment: DialogFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.dialog_fragment_layout, container, false)

        view.ivImage.setImageResource(R.drawable.android_cofee)

        Thread(Runnable {
            Thread.sleep(3000)
            dialog.dismiss()
        }).start()
        return view
    }
}