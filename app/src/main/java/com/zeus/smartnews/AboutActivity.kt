package com.zeus.smartnews

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.zeus.smartnews.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    lateinit var binding: ActivityAboutBinding
    lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPref = SharedPref(this)
        if (sharedPref.loadNightModeState()) {
            setTheme(R.style.Theme_homeActivityDarkMode)
        } else {
            setTheme(R.style.Theme_homeActivityLightMode)
        }

        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backBtn = binding.toolBarAboutUs
        setSupportActionBar(backBtn)

        val toolbarBtn = backBtn.getChildAt(1)
        toolbarBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            Animatoo.animateSlideRight(this)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeActivity::class.java))
        Animatoo.animateSlideRight(this)
    }

}