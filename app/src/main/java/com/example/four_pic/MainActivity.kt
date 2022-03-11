package com.example.four_pic

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.four_pic.databinding.ActivityMainBinding
import com.example.four_pic.manager.GameManager
import com.example.four_pic.models.QuestionData
import com.example.four_pic.utils.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var questionsList: ArrayList<QuestionData>
    lateinit var imagesList: ArrayList<ImageView>
    lateinit var wordList: ArrayList<Button>
    lateinit var lettersList: ArrayList<Button>
    lateinit var gameManager: GameManager

    private val shared by lazy {
        SharedPreferencesHelper(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        resumeGame.setOnClickListener {
//            val userName = shared.getUserName()
//            if (userName != null) {
//                if (userName.isEmpty()){
//                    Toast.makeText(this, "CREATE NEW GAME", Toast.LENGTH_SHORT).show()
//                }else{
//                    val intent = Intent(this, ResumeGame::class.java)
//                    startActivity(intent)
//                }
//            }else{
//                Toast.makeText(this, "USERNAME IS NULL", Toast.LENGTH_SHORT).show()
//            }
//        }
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
        val userNAME = shared.getUserName()
        binding.userNameID.text = userNAME.toString()
        getAllQuestions()
        gameManager = GameManager(questionsList, 0, 0)
        loadViews()
        loadDataToView()
    }

    private fun getAllQuestions() {
        questionsList = ArrayList()
        questionsList.add(
            QuestionData(
                arrayListOf(
                    R.drawable.img1,
                    R.drawable.img2,
                    R.drawable.img3,
                    R.drawable.img4,
                ),
                "Bridge",
                "bhjrkidfge"
            )
        )
        questionsList.add(
            QuestionData(
                arrayListOf(
                    R.drawable.img5,
                    R.drawable.img6,
                    R.drawable.img7,
                    R.drawable.img8,
                ),
                "Card",
                "acjlrsudm"
            )
        )
        questionsList.add(
            QuestionData(
                arrayListOf(
                    R.drawable.img9,
                    R.drawable.img10,
                    R.drawable.img11,
                    R.drawable.img12,
                ),
                "Water",
                "rfwtoanyek"
            )
        )
        questionsList.add(
            QuestionData(
            arrayListOf(
                R.drawable.img13,
                R.drawable.img14,
                R.drawable.img15,
                R.drawable.img16,
            ),
                "Old",
                "ocjlrsudm"
        )
        )
    }

    private fun loadViews() {
        imagesList = ArrayList()
        for (i in 0 until binding.imagesLayout.childCount) {
            imagesList.add(binding.imagesLayout.getChildAt(i) as ImageView)
        }
        /////////
        wordList = ArrayList()
        for (i in 0 until binding.wordLayout.childCount) {
            wordList.add(binding.wordLayout.getChildAt(i) as Button)
            wordList[i].setOnClickListener {
                wordBtnClick(it as Button)
            }
        }
        ///////////
        lettersList = ArrayList()
        for (i in 0 until binding.letterLayout.childCount) {
            lettersList.add(binding.letterLayout.getChildAt(i) as Button)
            lettersList[i].setOnClickListener {
                letterBtnClick(it as Button)
            }
        }
    }

    private fun letterBtnClick(button: Button) {
        if (button.isVisible && wordList[gameManager.getWordSize()-1].text.isEmpty()) {
            button.invisible()
            val word = button.text.toString()
            for (i in 0 until wordList.size) {
                if (wordList[i].text.isEmpty()) {
                    wordList[i].text = word
                    break
                }
            }
        }
    }

    private fun wordBtnClick(it: Button) {
        if (it.text.isNotEmpty()) {
            val word = it.text.toString()
            it.text = ""
            for (i in 0 until lettersList.size) {
                if (lettersList[i].isInvisible()
                    && lettersList[i].text.toString().lowercase() == word.lowercase()
                ) {
                    lettersList[i].visible()
                    break
                }
            }
        }
    }

    private fun loadDataToView() {
        for (i in 0 until imagesList.size) {
            imagesList[i].setImageResource(gameManager.getQuestions()[i])
        }
        /////////
        for (i in 0 until wordList.size) {
            if (gameManager.getWordSize() > i) {
                wordList[i].visible()
                wordList[i].text = ""
            } else {
                wordList[i].gone()
            }
        }
        ////////
        for (i in 0 until lettersList.size) {
            lettersList[i].visible()
            lettersList[i].text = "${gameManager.getLetters()[i]}"
        }
    }
    private fun saveData(){

    }
}