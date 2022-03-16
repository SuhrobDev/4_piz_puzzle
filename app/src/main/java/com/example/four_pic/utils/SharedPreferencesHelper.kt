package com.example.four_pic.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Build
import java.util.*
import kotlin.collections.ArrayList


class SharedPreferencesHelper(var context: Context) {

    private var preferences: SharedPreferences

    private lateinit var editor: SharedPreferences.Editor

    init {
        preferences = context.getSharedPreferences("APP_PREFS_NAME", MODE_PRIVATE)
    }

    var t = 0
    var v = 0
    
    // Set Night or Light Mode
    fun setNightMode(isNightMode: Boolean) {
        editor = preferences.edit()
        editor.putBoolean("IS_NIGHT", isNightMode)
        editor.apply()
    }

    // Get Night or Light Mode
    fun getNightMode() = preferences.getBoolean("IS_NIGHT", false)

    fun getLanguage() = preferences.getString("LANG", "ru")

    fun loadLocale(context: Context) {
        setLanguage(getLanguage()!!, context)
    }

    fun setLanguage(lang: String, context: Context) {
        editor = preferences.edit()
        editor.putString("LANG", lang)
        editor.apply()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, lang)
        }
        updateResourcesLegacy(context, lang)
    }

    fun setLevel(level:Int){
        editor = preferences.edit()
        editor.putInt("LEVEL",level)
        editor.apply()
    }

    fun getLevel() = preferences.getInt("LEVEL",0)

    fun setCoin(coin:Int){
        editor = preferences.edit()
        editor.putInt("COIN",coin)
        editor.apply()
    }

    fun getCoin() = preferences.getInt("COIN",0)

    private fun updateResources(context: Context, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)//bu joyni uchirib kor
        return context.createConfigurationContext(configuration)
    }


    private fun updateResourcesLegacy(context: Context, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        configuration.setLayoutDirection(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }

    fun setAllNumbers(numbers: ArrayList<Int>) {
        editor = preferences.edit()
        numbers.forEachIndexed { index, number ->
            editor.putInt("NUMBER_${index}", number)
        }
        editor.apply()
    }

    fun getAllNumbers(): ArrayList<Int> {
        val numbers = ArrayList<Int>()
        var t = 1
        for (i in 0..15) {
            if (i == 15) {
                numbers.add(preferences.getInt("NUMBER_${i}", -1))
            } else {
                numbers.add(preferences.getInt("NUMBER_${i}", t++))
            }
        }
        return numbers
    }

    fun setLastCoinCount(CoinCount:Int){
        editor = preferences.edit()
        editor.putInt("LAST_COIN_COUNT",CoinCount)
        editor.apply()
    }

    fun getLastCoinCount() = preferences.getInt("LAST_COIN_COUNT",0)

    // Set Username
    fun setUserName(UserName : String){
        editor = preferences.edit()
        editor.putString("USERNAME", UserName)
        editor.apply()
    }

    // get UserName
    fun getUserName() = preferences.getString("USERNAME","")

    fun setMusicMode(MusicMode:Boolean){
        editor = preferences.edit()
        editor.putBoolean("MUSIC_MODE",MusicMode)
        editor.apply()
    }

    fun getMusicMode() = preferences.getBoolean("MUSIC_MODE",true)

    fun setPassword(password: String){
        editor = preferences.edit()
        editor.putString("PASSWORD",password)
        editor.apply()
    }

    fun getPassword() = preferences.getString("PASSWORD","")

    fun setAllTexts(wordText: ArrayList<String>) {
        editor = preferences.edit()
        for(i in 0 until wordText.size){
            ++t
            editor.putString("WORDTEXT_${i}", wordText.toString())
        }
        editor.apply()
    }

    fun getAllTexts(): ArrayList<String> {
        val wordText = ArrayList<String>()
        for (i in 0 until t){
            wordText.add(preferences.getString("WORDTEXT_${i}","").toString())
        }
        t = 0
        return wordText
    }
    
    fun setVisibilityLetter(visibility: ArrayList<Int>){
        editor = preferences.edit()
        for (i in 0 until visibility.size){
            ++v
            editor.putInt("VISIBILITY_${i}", visibility[i].toInt())
        }
        editor.apply()
    }
    
    fun getVisibilityLetter(): ArrayList<Int>{
        val visibilityLetter = ArrayList<Int>()
        for (i in 0 until v){
            visibilityLetter.add(preferences.getInt("VISIBILITY_${i}", 1).toInt())
        }
        return visibilityLetter
    }
    // Commit
}