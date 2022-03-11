package com.example.four_pic

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.four_pic.utils.SharedPreferencesHelper


class CreateUserName : AppCompatActivity() {
    lateinit var newGame : AppCompatButton
    private val shared by lazy {
        SharedPreferencesHelper(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        //val userName : EditText = findViewById(R.id.userName)
        newGame = findViewById(R.id.btnSubmit)
        val btnClose=findViewById<ImageView>(R.id.btnClose)
        newGame.setOnClickListener {
//            val userName2 :String = userName.text.toString()
//            shared.setUserName(userName2)
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
            checkUserName()
            finish()
        }
        btnClose.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun checkUserName() {
        val userName : EditText = findViewById(R.id.userName)
        val name:String = userName.text.toString()
        if (name.trim().length in 3..8) {
            val intent = Intent(applicationContext , MainActivity::class.java)
            intent.putExtra("myname" , name)
            shared.setUserName(name)
            startActivity(intent)
        } else {
            // Toast.makeText(this , "please enter valid name" , Toast.LENGTH_SHORT).show()
            Toast.makeText(
                applicationContext ,
                "please enter valid name" ,
                Toast.LENGTH_SHORT

            ).show()
            val intent = Intent(applicationContext , CreateUserName::class.java)
            Intent(intent)
        }
//        shared.setUserName(name)
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
    }

    //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
}