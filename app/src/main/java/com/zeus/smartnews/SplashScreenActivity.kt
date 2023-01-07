package com.zeus.smartnews

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.zeus.smartnews.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(binding.root)

//        val window = this.window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.statusBarColor = ContextCompat.getColor(this, R.color.dark_blue)

        supportActionBar?.hide()

        val animation1 = AnimationUtils.loadAnimation(this, R.anim.scale_to_big)
        val animation2 = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top)

        binding.splashScreenPic.startAnimation(animation1)
        binding.appNameText.startAnimation(animation2)

        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            Animatoo.animateSwipeLeft(this)
        }, 3000)

    }

    override fun onBackPressed() {

    }
}