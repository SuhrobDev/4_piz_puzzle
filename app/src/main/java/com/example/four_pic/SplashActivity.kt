package com.example.four_pic

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.four_pic.menu.MenuActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

//    val shared by lazy {
//        SharedPreferencesHelper(this)
//    }

    lateinit var progressText: TextView
    var maxProgress = 100
    var progressStatus = 0
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar : ActionBar? = supportActionBar
        actionBar?.hide()
        setContentView(R.layout.splash_activity)

        //startTimer()
//dfsadfsadfasdf
        progressText = findViewById(R.id.progressText)
        progressBar = findViewById(R.id.progressBar)
        progress()

    }

    private fun progress() {
        val thread = Thread{
            while (!allCheck()){
                count()
                runOnUiThread {
                    progressBar.max = maxProgress
                    progressBar.progress = progressStatus
                    progressText.text = "$progressStatus %"
                }
                if (allCheck()){
                    finish()
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        thread.start()
    }

    private fun allCheck(): Boolean {
        if (progressStatus == maxProgress){
            return true
        }
        return false
    }

    private fun count() {
        progressStatus+=1
        Thread.sleep(25)
    }

//    private fun startTimer() {
//
//        object : CountDownTimer(3000, 1000) {
//            override fun onFinish() {
//                shared.loadLocale(this@SplashActivity)
//                finish()
//                startActivity(Intent(this@SplashActivity, MenuActivity::class.java))
//            }
//
//            override fun onTick(millisUntilFinished: Long) {
//
//            }
//
//        }.start()
//    }
}