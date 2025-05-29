package com.example.nomanapp1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launch_screen)

        // Delay then start MainActivity
        window.decorView.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1500) // 1.5 seconds delay
    }
}
