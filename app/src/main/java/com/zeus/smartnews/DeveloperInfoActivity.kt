package com.zeus.smartnews

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.blogspot.atifsoftwares.animatoolib.Animatoo

class DeveloperInfoActivity : AppCompatActivity() {

    lateinit var sharedPref: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {

        sharedPref = SharedPref(this)
        if (sharedPref.loadNightModeState()) {
            setTheme(R.style.Theme_homeActivityDarkMode)
        } else {
            setTheme(R.style.Theme_homeActivityLightMode)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer_info)

        val contactBtn: Button = findViewById(R.id.contactMeBtn)
        try {
            //code to direct users to email app to send email to me on contact button click
            contactBtn.setOnClickListener {
                val link = "https://divineabiloro.dorik.io/"

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.setPackage("com.android.chrome")
                try {
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    intent.setPackage(null)
                    startActivity(Intent.createChooser(intent, "Select Browser"))
                }
            }
        } catch (e: Exception) {

        }

        val toolBar: Toolbar = findViewById(R.id.toolBarDeveloperInfo)
        setSupportActionBar(toolBar)

        val logoView = toolBar.getChildAt(1)
        logoView.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
            finish()
            Animatoo.animateSlideRight(this)
        }
}

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, SettingsActivity::class.java))
        finish()
        Animatoo.animateSlideRight(this)
    }
}