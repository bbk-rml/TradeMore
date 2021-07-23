package com.shree.trademore.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.shree.trademore.MainActivity
import com.shree.trademore.R
import com.shree.trademore.ui.home.HomeActivity
import com.shree.trademore.ui.login.LoginActivity
import com.shree.trademore.util.MySharePrefrence

class SplashActivity : AppCompatActivity() {
    val anim by lazy { AnimationUtils.loadAnimation(this,R.anim.blink) }
    lateinit var textView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        initView()

    }

    private fun initView(){
        textView = findViewById(R.id.textView)
        textView.animation = anim

        Handler(Looper.myLooper()!!).postDelayed({

            if (MySharePrefrence.instance?.getUserId()?.isNotEmpty() == true) {
                println("Not Empty = ${MySharePrefrence.instance?.getUserId()}")
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                println("Empty = ${MySharePrefrence.instance?.getUserId()}")
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }

        }, 2000)

    }
}