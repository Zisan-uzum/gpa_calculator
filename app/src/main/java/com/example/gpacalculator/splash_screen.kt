package com.example.gpacalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.splash.*

class splash_screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        var button_animation = AnimationUtils.loadAnimation(this, R.anim.top_down_ballon)
        var button_last = AnimationUtils.loadAnimation(this, R.anim.last_button)
        b.animation = button_animation

        b.setOnClickListener {
            b.startAnimation(button_last)
            object : CountDownTimer(1000, 1000){
                override fun onFinish() {
                    var intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onTick(millisUntilFinished: Long) {

                }

            } .start()

        }
    }

}
