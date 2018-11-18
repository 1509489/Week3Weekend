package com.pixelart.week3weekend

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pixelart.week3weekend.ServicesAndReceivers.NotificationService
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        stopService(Intent(this, NotificationService::class.java))

        btnReturn.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
