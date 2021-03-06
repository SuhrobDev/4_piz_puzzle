package com.example.four_pic.menu

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.four_pic.R
import com.example.four_pic.databinding.ActivityMainBinding
import com.example.four_pic.manager.GameManager
import com.example.four_pic.models.QuestionData
import com.example.four_pic.utils.*

class ResumeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var questionsList: ArrayList<QuestionData>
    private lateinit var imagesList: ArrayList<ImageView>
    private lateinit var wordList: ArrayList<Button>
    private lateinit var lettersList: ArrayList<Button>
    private lateinit var gameManager: GameManager
    private lateinit var userName : TextView
    private var wordCheck = ""
    private val shared by lazy {
        SharedPreferencesHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            onStop()
            startActivity(intent)
            finish()
        }
        val userNAME = shared.getUserName()
        userName = findViewById(R.id.userNameID)
        userName.text=userNAME.toString()
        getAllQuestions()
        gameManager = GameManager(questionsList, shared.getCoin(), shared.getLevel())
        loadViews()
        loadDataToView()
        ////////////////////////////////////////////////////////////////////
        binding.submit.setOnClickListener {
            if (gameManager.hasNextQuestion()) {
                if (check1()) {
                    Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show()
                    Thread.sleep(500)
                    gameManager.coins += 10
                    binding.coins.text = gameManager.coins.toString()

                    gameManager.level++

                    var level1 = gameManager.level

                    binding.level.text = (++level1).toString()
                    getAllQuestions()
                    loadDataToView()
                    wordCheck = ""
                } else {
                    Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
                }
            }else{
                custom()
                startTimer()
            }
        }

        binding.btnClean.setOnClickListener {
            for (i in 0 until wordList.size){
                wordList[i].text=""
            }
            for (i in 0 until lettersList.size) {
                    lettersList[i].visible()
            }
        }
        binding.btnHelp.setOnClickListener {
            if (gameManager.coins>=5) {
                var n = 0
                for (i in 0 until wordList.size) {
                    n = i
                    if (wordList[i].text.isEmpty()) {
                        wordList[n].text = gameManager.getWord()[n].toString()
                        break
                    }
                }
                for (i in 0 until lettersList.size) {
                    if (lettersList[i].text.toString() == wordList[n].text.toString()) {
                        lettersList[i].invisible()
                        break
                    }
                }
                gameManager.coins-=5
                binding.coins.text = gameManager.coins.toString()
            }else{
                Toast.makeText(this@ResumeActivity, "Tangalaringiz miqdori yetmaydi", Toast.LENGTH_SHORT).show()
            }
//               wordList[wordList.size-2].text = gameManager.getWord()[wordList.size-2].toString()
//            for (i in 0 until lettersList.size){
//                if (lettersList[i].text.toString()==wordList[wordList.size-2].text){
//                    lettersList[i].invisible()
//                    break
//                }
        }
    }
    override fun onStop() {
        val wordTexts = ArrayList<String>()
        for (i in 0 until wordList.size) {
            val d = (wordList[i] as AppCompatButton).text.toString()
            wordTexts.add(d)
        }
        val letterVisibility = ArrayList<Int>()
        for (i in 0 until lettersList.size){
            if (lettersList[i].isVisible){
                letterVisibility.add(1)
            }else if (lettersList[i].isInvisible){
                letterVisibility.add(-1)
            }
        }
        shared.setVisibilityLetter(letterVisibility)
        shared.setAllTexts(wordTexts)
        shared.setLevel(gameManager.level)
        shared.setCoin(gameManager.coins)
        super.onStop()
    }

    private fun startTimer() {
        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                startActivity(Intent(this@ResumeActivity, MenuActivity::class.java))
            }
            override fun onTick(millisUntilFinished: Long) {
            }
        }.start()
    }

    private fun custom() {
        val alertDialog:AlertDialog.Builder = AlertDialog.Builder(this@ResumeActivity,
            R.style.fullScreenAlert
        )
        val view:View = layoutInflater.inflate(R.layout.dialog_win, null)
        alertDialog.setView(view)
        val dialog = alertDialog.create()
        dialog.show()
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
                "bridge",
                "blrfghedoi"
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
                "card",
                "ctajldsrom"
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
                "water",
                "rwqtoanyek"
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
                "old",
                "lwqtoanydk"
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

    private fun check1(): Boolean {
        for (i in 0 until (gameManager.getWordSize())){
            wordCheck+=wordList[i].text.toString()
        }
        return gameManager.check(wordCheck)
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
                    wordCheck+=word
                    print(wordCheck)
                    break
                }
            }
            for (i in 0 until wordList.size){
                if(wordList[i].text.isNotEmpty()){
                    wordCheck+=wordList[i].toString()
                }else wordCheck=""
            }
        }
        wordCheck = ""
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
            lettersList[i].text = gameManager.getLetters()[i].toString()
        }
    }
}