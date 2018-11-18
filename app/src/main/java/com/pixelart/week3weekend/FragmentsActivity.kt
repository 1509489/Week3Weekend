package com.pixelart.week3weekend

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class FragmentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)

        val fragment1 = Fragment1.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.flFragment1, fragment1).commit()

        val fragment2 = Fragment2.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.flFragment2, fragment2).commit()
    }
}
