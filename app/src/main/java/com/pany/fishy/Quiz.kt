package com.pany.fishy


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pany.fishy.data.Progress
import com.pany.fishy.data.Question
import com.pany.fishy.data.RandomQuestion
import java.io.File
import kotlin.math.roundToInt
import kotlin.random.Random


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
      setProgress(Gson().fromJson(text, Progress::class.java))
    }
  }

  fun setProgress(progress: Progress) {
    this.progress = progress
  }

  fun evaluate(selected: Int): Int {
    if (selected == randomQuestion.correctAnswer) {
      progress.moveCorrect(randomQuestion.id)
    } else {
      progress.moveWrong(randomQuestion.id)
    }
    return randomQuestion.correctAnswer
  }

  fun getNextQuestion(): RandomQuestion {
    var questionId = -1
    val questionsLeft = progress.wrong.size + progress.correct.size
    if (progress.new.isNotEmpty()) {
      questionId = progress.new[0]
    } else if (questionsLeft == 0) {
      randomQuestion = RandomQuestion(
        Question(
          "The END",
          "Ready for test!",
          "Liked the app",
          "Petri heil"
        ), questionId
      )
    } else {
      val itemNumber = Random.nextInt(1, questionsLeft)
      if (itemNumber <= progress.wrong.size) {
        questionId = progress.wrong[itemNumber - 1]
      } else {
        questionId = ArrayList(progress.correct.keys)[itemNumber - progress.wrong.size - 1]
      }
    }
    if (questionId != -1) {
      randomQuestion = RandomQuestion(questions[questionId] ?: error("not found"), questionId)
    }
    return randomQuestion
  }

  fun getProcessNumbers(): String {
//    return String.format("%d %d %d %d", progress.new.size, progress.wrong.size, progress.correct.size, progress.save.size)
    return getSavePercent().toString() + "% | " + getCorrectPercent().toString() + "%"
  }

  fun getCorrectPercent(): Int {
    return getCorrectPercent(progress.correct.size).roundToInt()
  }

  fun getSavePercent(): Int {
    return getCorrectPercent(progress.save.size).roundToInt()
  }

  private fun getCorrectPercent(num: Int): Double {
    val total = progress.save.size + progress.correct.size + progress.wrong.size + progress.new.size
    return (100.00 / total) * num
  }

  fun saveProgress(file: File) {
    file.writeText(Gson().toJson(progress))
  }

  fun resetProgress(file: File) {
    file.writeText("")
    loadProgress(file)
  }
}
