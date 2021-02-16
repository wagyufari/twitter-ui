package com.mayburger.twitter.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.mayburger.twitter.databinding.ActivitySplashBinding
import com.mayburger.twitter.ui.main.MainActivity
import com.mayburger.twitter.utils.ViewUtils.delay
import com.mayburger.twitter.utils.ViewUtils.scale

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        delay(500) {
            binding.logo.scale(0.4f, duration = 800, interpolator = DecelerateInterpolator(), onEnd = {
                binding.logo.scale(30f, duration = 500, interpolator = AccelerateDecelerateInterpolator(),onEnd = {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                })
            })
        }
    }
}