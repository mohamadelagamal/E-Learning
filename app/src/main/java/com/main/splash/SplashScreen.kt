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
import com.database.getUser
import com.example.e_learning.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.main.ui.home.HomeActivity
import com.model.ApplicationUser
import com.model.DataUtil
import com.user.intro.navigation.slider.SliderActivity
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
           checkLoginInFirebase()
        }, 4000)
    }


    private fun checkLoginInFirebase() {
        val firebaseUser = Firebase.auth.currentUser
        when{
            firebaseUser==null->{
                openLoginAccount()
            }
            else->{
                getUser(firebaseUser.uid, OnSuccessListener {
                    val user = it.toObject(ApplicationUser::class.java)
                    DataUtil.user=user
                    openHome()
                }, OnFailureListener {
                    openLoginAccount()
                })
            }
        }
    }
    private fun openHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    private fun openLoginAccount() {
        startActivity(Intent(this, SliderActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
}