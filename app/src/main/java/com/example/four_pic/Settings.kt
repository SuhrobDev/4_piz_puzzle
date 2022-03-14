package com.example.four_pic

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SwitchCompat
import com.example.four_pic.databinding.ActivitySettingBinding
import com.example.four_pic.utils.SharedPreferencesHelper

class Settings : AppCompatActivity() {
private lateinit var binding:ActivitySettingBinding

    private val shared by lazy {
        SharedPreferencesHelper(this)
    }
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySettingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setContentView(R.layout.activity_setting)
        val actionBar : ActionBar? = supportActionBar
        actionBar?.hide()

        val switchCompat = findViewById<SwitchCompat>(R.id.theme_mode)

        switchCompat.isChecked = shared.getNightMode()
        switchCompat.setOnClickListener {
            shared.setNightMode(switchCompat.isChecked)
            if (switchCompat.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

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
        val music: MediaPlayer = MediaPlayer.create(this@Settings, R.raw.darkside1)
        binding.volume.setOnClickListener {
            if (binding.volume.isChecked){
                music.start()
                music.isLooping=true
            }
            else{
                music.isLooping=false
                music.pause()
            }
        }
        //

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