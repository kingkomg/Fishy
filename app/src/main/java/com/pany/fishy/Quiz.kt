package com.pany.fishy


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pany.fishy.data.Progress
import com.pany.fishy.data.Question
import com.pany.fishy.data.RandomQuestion
import java.io.File


class Quiz {
  private lateinit var questions: Map<Int, Question>
  private lateinit var progress: Progress
  private lateinit var randomQuestion: RandomQuestion

  fun loadQuestions(text: String) {
    val type = object : TypeToken<Map<Int, Question>>() {}.type
    questions = Gson().fromJson(text, type)
  }

  fun loadProgress(file: File) {
    var text = ""
    if (file.exists()) {
      text = file.bufferedReader().use { it.readText() }
    }
    if (text.isBlank()) {
      progress = Progress()
      progress.new = questions.keys.toMutableList()
    } else {
      progress = Gson().fromJson(text, Progress::class.java)
    }
  }

  fun evaluate(selected: Int): Int {
    if (selected == randomQuestion.correctAnswer) {
      progress.moveCorrect(selected)
    } else {
      progress.moveWrong(selected)
    }
    return randomQuestion.correctAnswer
  }

  fun getNextQuestion(): RandomQuestion {
    val questionId = progress.new[0]
    randomQuestion = RandomQuestion(questions[questionId] ?: error("not found"), questionId)
    return randomQuestion
// get first new if new list is not empty
// else
// get random list
// get random question id from list - state
  }

  fun saveProgress(file: File) {
    file.writeText(Gson().toJson(progress))
  }

  fun resetProgress() {
    // move all ids to list "new" and update progress
  }

  fun getRandomQuestionId() {

  }

}
