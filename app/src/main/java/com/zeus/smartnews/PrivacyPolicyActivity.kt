package com.zeus.smartnews

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo

class PrivacyPolicyActivity : AppCompatActivity() {

    lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPref = SharedPref(this)
        if (sharedPref.loadNightModeState()) {
            setTheme(R.style.Theme_homeActivityDarkMode)
        } else {
            setTheme(R.style.Theme_homeActivityLightMode)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, SettingsActivity::class.java))
        finish()
        Animatoo.animateSlideRight(this)
    }
}