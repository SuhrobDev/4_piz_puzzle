package com.example.four_pic

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.four_pic.databinding.ActivityDialogBinding
import com.example.four_pic.databinding.ActivityMainBinding
import com.example.four_pic.databinding.DialogWinBinding
//import com.example.four_pic.databinding.ActivityMainBinding
import com.example.four_pic.manager.GameManager
import com.example.four_pic.models.QuestionData
import com.example.four_pic.utils.*

class MainActivity : AppCompatActivity() {
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
            startActivity(intent)
            finish()
        }
        val userNAME = shared.getUserName()
        userName = findViewById(R.id.userNameID)
        userName.text=userNAME.toString()
        getAllQuestions()
        gameManager = GameManager(questionsList, 0, 0)
        loadViews()
        loadDataToView()
        ////////////////////////////////////////////////////////////////////
        binding.submit.setOnClickListener {
            if (gameManager.hasNextQuestion()) {
                if (check_()) {
                    Toast.makeText(this, "Correct!", Toast.LENGTH_LONG).show()
                    Thread.sleep(500)
                    gameManager.coins += 10
                    binding.coins.text = gameManager.coins.toString()

                    gameManager.level++

                    var level_ = gameManager.level

                    binding.level.text = (++level_).toString()
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
                Toast.makeText(this@MainActivity, "Tangalaringiz miqdori yetmaydi", Toast.LENGTH_SHORT).show()
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
        shared.setLevel(gameManager.level)
        shared.setCoin(gameManager.coins)
        super.onStop()
    }

    private fun startTimer() {
        object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                startActivity(Intent(this@MainActivity, MenuActivity::class.java))
            }
            override fun onTick(millisUntilFinished: Long) {
            }
        }.start()
    }

    private fun custom() {
        val alertDialog:AlertDialog.Builder = AlertDialog.Builder(this@MainActivity,
            R.style.fullScreenAlert)
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
        questionsList.add(
            QuestionData(
                arrayListOf(
                    R.drawable.img17,
                    R.drawable.img18,
                    R.drawable.img19,
                    R.drawable.img21, // img20
                ),
                "yellow",
                "lwltoanyke"
            )
        )
        questionsList.add(
            QuestionData(
                arrayListOf(
                    R.drawable.img21,
                    R.drawable.img23, // img22
                    R.drawable.img23,
                    R.drawable.img25, // img23
                ),
                "clock",
                "cwltoanykc"
            )
        )
        questionsList.add(
            QuestionData(
                arrayListOf(
                    R.drawable.img25,
                    R.drawable.img27, // img26
                    R.drawable.img27,
                    R.drawable.img28,
                ),
                "back",
                "cwlboanykc"
            )
        )
        questionsList.add(
            QuestionData(
                arrayListOf(
                    R.drawable.img30, // img29
                    R.drawable.img30,
                    R.drawable.img32, // img31
                    R.drawable.img32,
                ),
                "mouse",
                "eulboanyms"
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

    fun check_(): Boolean {
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