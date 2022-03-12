package com.example.four_pic

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
//import com.example.four_pic.databinding.ActivityMenuBinding
import com.example.four_pic.utils.SharedPreferencesHelper
import kotlin.system.exitProcess

class MenuActivity : AppCompatActivity() {
    val shared by lazy {
        SharedPreferencesHelper(this)
    }
    lateinit var btnNewGame:AppCompatButton
    lateinit var btnSettings:AppCompatButton
    lateinit var btnExit:AppCompatButton
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        btnNewGame=findViewById(R.id.btn_new_game)
        btnSettings=findViewById(R.id.btn_settings)
        btnExit=findViewById(R.id.btn_Exit)
        btnExit.setOnClickListener {
            finish()
            exitProcess(0)
        }
        btnNewGame.setOnClickListener {
            val intent = Intent(this, CreateUserName::class.java)
            startActivity(intent)
            finish()

//            val dialog = Dialog(this)
//            dialog.setCancelable(false)
//            dialog.setContentView(R.layout.activity_dialog)
//            val btnClose=dialog.findViewById<ImageView>(R.id.btnClose)
//            val btnDismiss=dialog.findViewById<Button>(R.id.btnSubmit)

//            btnDismiss.setOnClickListener {
//             //   checkUserName()
//                val userName : AppCompatEditText = findViewById(R.id.userName)
//
//                val name:String = userName.text.toString()
//                shared.setUserName(name)
//                val intent = Intent(this@MenuActivity, MainActivity::class.java)
//                startActivity(intent)
//            }
//            btnClose.setOnClickListener {
//                dialog.dismiss()
//            }
//            dialog.show()

        }
        btnSettings.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
            finish()
        }
        val resumeGame: AppCompatButton = findViewById(R.id.btn_resume)
        resumeGame.setOnClickListener {
            val userName = shared.getUserName()
            if (userName != null) {
                if (userName.isEmpty()){
                    Toast.makeText(this, "CREATE NEW GAME", Toast.LENGTH_SHORT).show()
                }else{
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }else{
                Toast.makeText(this, "USERNAME IS NULL", Toast.LENGTH_SHORT).show()
            }
        }
    }
//    private fun checkUserName() {
//        val name:String = userName.text.toString()
//        if (name.trim().length in 3..8) {
//            val intent = Intent(applicationContext , MainActivity::class.java)
//            intent.putExtra("myname" , name)
//            shared.setUserName(name)
//            startActivity(intent)
//        } else {
//            // Toast.makeText(this , "please enter valid name" , Toast.LENGTH_SHORT).show()
//            Toast.makeText(
//                applicationContext ,
//                "please enter valid name" ,
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//        shared.setUserName(name)
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//    }
}