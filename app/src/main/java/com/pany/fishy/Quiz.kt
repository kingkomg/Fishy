package com.pany.fishy


import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File


class Quiz : View.OnClickListener {

  lateinit var questions: List<Question>
  lateinit var progress: Progress

  override fun onClick(view: View) {
    when (view.id) {
      R.id.buttonA -> checkAnswer(0)
      R.id.buttonB -> checkAnswer(1)
      R.id.buttonC -> checkAnswer(2)
      R.id.buttonNext -> initQuestion()
      R.id.buttonReset -> resetProgress()
    }
  }

  fun start() {
    loadData()
    updateProgress()
    initQuestion()
  }

  fun loadData() {
    questions = loadQuestions()
    progress = loadProgress()
  }

  fun loadQuestions(): List<Question> {
    val text = File("src/main/res/questions.json").bufferedReader().use { it.readText() }
    val type = object : TypeToken<ArrayList<Question>>() {}.type
    return Gson().fromJson(text, type)
  }

  fun loadProgress(): Progress {
    val text = File("src/main/res/status.json").bufferedReader().use { it.readText() }
    return Gson().fromJson(text, Progress::class.java)
  }

  fun resetProgress() {
    // move all ids to list "new" and update progress
  }

  fun updateProgress() {
    // calculate percentage of correct answers
    // save lists
    // update ui elements
  }

  fun initQuestion() {
    // get first new if new list is not empty
    // else
    // get random list
    // get random question id from list - state
    // update ui elements
  }

  fun checkAnswer(id: Int) {
    // check answer
    // move id in lists (sure = 5x correct, correct 1x-4x, new, wrong)
  }


}
