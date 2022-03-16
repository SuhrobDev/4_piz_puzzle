package com.example.four_pic.menu
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.four_pic.R
import com.example.four_pic.manager.GameManager
import com.example.four_pic.models.QuestionData
import com.example.four_pic.utils.SharedPreferencesHelper
import kotlin.system.exitProcess

class MenuActivity : AppCompatActivity() {
    private val shared by lazy {
        SharedPreferencesHelper(this)
    }
    private lateinit var btnNewGame:AppCompatButton
    private lateinit var btnSettings:AppCompatButton
    private lateinit var btnExit:AppCompatButton
    private lateinit var questionsList: ArrayList<QuestionData>
    private lateinit var gameManager: GameManager
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
//                    gameManager = GameManager(questionsList, shared.getLevel(), shared.getCoin())
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }else{
                Toast.makeText(this, "USERNAME IS NULL", Toast.LENGTH_SHORT).show()
            }
        }
    }
}