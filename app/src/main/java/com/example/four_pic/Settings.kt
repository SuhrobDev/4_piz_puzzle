package com.example.four_pic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SwitchCompat
import com.example.four_pic.utils.SharedPreferencesHelper

class Settings : AppCompatActivity() {

    private val shared by lazy {
        SharedPreferencesHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val actionBar : ActionBar? = supportActionBar
        actionBar?.hide()
        findViewById<AppCompatButton>(R.id.uzb).setOnClickListener {
            shared.setLanguage("uz", this)
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        findViewById<AppCompatButton>(R.id.eng).setOnClickListener {
            shared.setLanguage("en", this)
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        findViewById<AppCompatButton>(R.id.ru).setOnClickListener {
            shared.setLanguage("ru", this)
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        // Night Mode or Light Mode
        val nightModeButton = findViewById<SwitchCompat>(R.id.theme_mode)
        nightModeButton.isChecked = shared.getNightMode()
        nightModeButton.setOnClickListener {
            shared.setNightMode(nightModeButton.isChecked)
            if (nightModeButton.isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            finish()
        }
        // Night Mode or Light Mode
        // mute unmute
//        val volume = findViewById<SwitchCompat>(R.id.volume)
//        nightModeButton.isChecked = shared.getNightMode()
//        nightModeButton.setOnClickListener {
//            shared.setNightMode(nightModeButton.isChecked)
//            if (nightModeButton.isChecked){
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            }else{
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            }
//        }
        //mute unmute
    }
    
}