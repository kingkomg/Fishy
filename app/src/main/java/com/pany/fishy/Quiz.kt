package com.pany.fishy


import android.content.res.Resources
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Quiz {
  lateinit var questions: List<Question>
  lateinit var progress: Progress
  lateinit var question: RandomQuestion

  fun loadData(resources: Resources) {
    questions = loadQuestions(resources)
    progress = loadProgress(resources)
  }

  fun loadQuestions(resources: Resources): List<Question> {
    val text = resources.openRawResource(R.raw.questions).bufferedReader().use { it.readText() }
    val type = object : TypeToken<ArrayList<Question>>() {}.type
    return Gson().fromJson(text, type)
  }

  fun loadProgress(resources: Resources): Progress {
    val text = resources.openRawResource(R.raw.status).bufferedReader().use { it.readText() }
    return Gson().fromJson(text, Progress::class.java)
  }

  fun setNextQuestion() {
    question = RandomQuestion(questions.get(0), 0)
    // get first new if new list is not empty
    // else
    // get random list
    // get random question id from list - state
  }

  fun updateProgress(view: View) {
    // calculate percentage of correct answers
    // save lists
    // update ui elements
  }

  fun resetProgress() {
    // move all ids to list "new" and update progress
  }


}
