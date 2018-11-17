package com.pixelart.week3weekend

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_splash.*
import android.widget.TextView



/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        animateLoading()

        Thread(Runnable {
            Thread.sleep(2000)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }).start()

    }

    private fun animateLoading() {
        val handler = Handler()
        Thread(Runnable {
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {
            }

            handler.post {
                val txt = tvLoading
                if (txt.visibility == View.VISIBLE) {
                    txt.visibility = View.INVISIBLE
                } else {
                    txt.visibility = View.VISIBLE
                }
                animateLoading()
            }
        }).start()
    }

}
