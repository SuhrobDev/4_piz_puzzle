package com.example.four_pic

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.example.four_pic.utils.SharedPreferencesHelper

class NewGameActivity : AppCompatActivity() {
    val shared by lazy {
        SharedPreferencesHelper(this)
    }
    private lateinit var newGame:AppCompatButton
//    private var alertDialog = android.app.AlertDialog

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

    }
    @SuppressLint("CommitPrefEdits")
//    private fun dialogUser(){
//        val builder = AlertDialog.Builder(this@NewGameActivity)
//        setContentView(R.layout.dialog)
//        val userText:EditText = findViewById(R.id.Ed_user)
//        builder.setTitle("USERNAME")
//        val sharedPreferences = getSharedPreferences("Username",Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
////        builder.setPositiveButton("YES", DialogInterface.OnClickListener(){
//////            shared.setUserName(userText.text.toString())
//
////        })
//        builder.setPositiveButton("YES", DialogInterface.OnClickListener { _ , _ ->
//            editor.putString("UserText", userText.text.toString())
////            alertDialog.cancel()
//        })
//        builder.setNegativeButton("NO", DialogInterface.OnClickListener { _ , _ ->
//            builder.setCancelable(true)
//        })
//        builder.show()
//public void dialogUser(View view){
//
// TextView usernameInput
// }
//    }

    fun dialogUser(){
       //var usernameInput = findViewById<TextView>(R.id.t_usernameInput)

    }
}