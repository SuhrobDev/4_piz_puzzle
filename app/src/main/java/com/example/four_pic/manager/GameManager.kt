package com.example.four_pic.manager

import uz.micro.star.lesson_9.models.QuestionData
import java.util.*

class GameManager(
    var questionsList: ArrayList<QuestionData>,
    var level: Int,
    var coins: Int,
) {
    private fun question() = questionsList[level]
    fun getQuestions() = question().imageList
    fun getWord() = question().word
    fun getWordSize() = question().word.length
    fun getLetters() = question().letters
    fun getLettersSize() = question().letters.length
    fun questionsSize() = questionsList.size
    fun check(word: String):Boolean{
        //bu joyda logika bo`ladi
        //
        return getWord().trim().lowercase() == word.lowercase()
    }

}