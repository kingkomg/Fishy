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
      progress = Gson().fromJson(text, Progress::class.java)
    }
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
    var questionId = 0
    if (progress.new.isNotEmpty()) {
      questionId = progress.new[0]
    } else {
      val listId = Random.nextInt(0, 100)
      when {
        progress.wrong.isNotEmpty() && listId < 60 -> {
          val questionNumber = if (progress.wrong.size == 1) {
            0
          } else {
            Random.nextInt(0, progress.wrong.size - 1)
          }
          questionId = progress.wrong[questionNumber]
        }
        progress.correct.isNotEmpty() && listId < 90 -> {
          val questionNumber = if (progress.correct.size == 1) {
            0
          } else {
            Random.nextInt(0, progress.correct.size - 1)
          }
          questionId = progress.correct.keys.toIntArray()[questionNumber]
        }
        progress.save.isNotEmpty() -> {
          val questionNumber = if (progress.save.size == 1) {
            0
          } else {
            Random.nextInt(0, progress.save.size - 1)
          }
          questionId = progress.save[questionNumber]
        }
      }
    }
    randomQuestion = RandomQuestion(questions[questionId] ?: error("not found"), questionId)
    return randomQuestion
  }

  fun getCorrectPercent(): Int {
    return getCorrectPercent(
      progress.save.size,
      progress.correct.size,
      progress.wrong.size,
      progress.new.size
    ).roundToInt()
  }

  fun getCorrectPercent(saveSize: Int, correctSize: Int, wrongSize: Int, newSize: Int): Double {
    val corrects = saveSize.plus(correctSize)
    val wrongs = wrongSize.plus(newSize)
    return (100.00 / wrongs.plus(corrects)) * corrects
  }

  fun saveProgress(file: File) {
    file.writeText(Gson().toJson(progress))
  }

  fun resetProgress(file: File) {
    file.writeText("")
    loadProgress(file)
  }
}
