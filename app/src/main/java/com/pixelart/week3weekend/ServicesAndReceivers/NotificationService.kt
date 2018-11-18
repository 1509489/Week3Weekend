package com.pixelart.week3weekend.ServicesAndReceivers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.pixelart.week3weekend.NotificationActivity
import com.pixelart.week3weekend.R

class NotificationService : Service() {
    private val TAG = "NotificationService"
    private val CHANNEL_ID = "myNotificationChannel"

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")

        when(intent?.action)
        {
            "startNotification" ->{
                initNotification()
            }
        }

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    private fun initNotification() {

        var contentTitle: CharSequence = "Notification"
        var contentText: CharSequence = "This is a notification"

        val channel_name = "TestNotification"
        val channel_description = "This is an example notification"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, channel_name, importance)
            channel.description = channel_description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }


        val notificationIntent = Intent(this, NotificationActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(contentTitle).setContentText(contentText)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true).build()

        startForeground(1, notificationBuilder)
    }
}
