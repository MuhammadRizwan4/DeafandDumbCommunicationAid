package com.example.muhammadrizwan.deafanddumbcommunicationaid.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.muhammadrizwan.deafanddumbcommunicationaid.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val thread = object : Thread()
        {
            override fun run() {
                super.run()
                try {
                    sleep(3000)
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }catch (e: Exception)
                {
                    e.printStackTrace()
                }

            }           }
        thread.start()
    }
}

