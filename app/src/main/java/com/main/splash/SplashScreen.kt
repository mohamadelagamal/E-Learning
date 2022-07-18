package com.main.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.e_learning.R
import com.user.intro.navigation.slider.SliderActivity

class SplashScreen : AppCompatActivity() {
    var textView: LinearLayout? = null
    var imageView: ImageView? = null
    var animationUptoDown: Animation? = null
    var animationDownToUp:Animation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        textView = findViewById(R.id.line1)
        imageView = findViewById(R.id.logo)
        animationUptoDown = AnimationUtils.loadAnimation(this, R.anim.uptodownanim)
        animationDownToUp = AnimationUtils.loadAnimation(this, R.anim.downtotopanim)
        imageView?.setAnimation(animationUptoDown)
        textView?.setAnimation(animationDownToUp)
        Handler().postDelayed({
            startActivity(Intent(this, SliderActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 4000)
    }


    private fun openHome() {
//        val intent = Intent(this,UserActivity::class.java)
//        startActivity(intent)
//        finish()
    }

    private fun openLoginAccount() {
        val intent = Intent (this, SliderActivity::class.java)
        startActivity(intent)
        finish()
    }
}