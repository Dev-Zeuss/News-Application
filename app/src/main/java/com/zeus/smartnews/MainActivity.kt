package com.zeus.smartnews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zeus.smartnews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        finishAffinity()
    }
}


