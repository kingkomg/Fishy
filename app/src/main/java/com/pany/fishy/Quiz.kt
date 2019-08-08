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
    var questionId = -1
    if (progress.new.isNotEmpty()) {
      questionId = progress.new[0]
    } else {
      val listId = Random.nextInt(0, 100)
      when {
        progress.wrong.isNotEmpty() && listId < 80 -> {
          questionId = getRandomQuestionIdFromList(progress.wrong)
        }
        progress.correct.isNotEmpty() -> {
          questionId = getRandomQuestionIdFromList(ArrayList(progress.correct.keys))
        }
        progress.wrong.isNotEmpty() -> {
          questionId = getRandomQuestionIdFromList(progress.wrong)
        }

      }
    }
    if(questionId == -1) {
     randomQuestion = RandomQuestion(Question("The END", "Ready for test!", "Liked the app", "Petri heil"), -1);
    } else {
      randomQuestion = RandomQuestion(questions[questionId] ?: error("not found"), questionId)
    }
    return randomQuestion
  }

  fun getRandomQuestionIdFromList(list: List<Int>): Int {
    val questionNumber = if (list.size == 1) {
      0
    } else {
      Random.nextInt(0, progress.wrong.size - 1)
    }
    return list[questionNumber]
  }

  fun getProcessNumbers(): String {
//    return String.format("%d %d %d %d", progress.new.size, progress.wrong.size, progress.correct.size, progress.save.size)
    return getSavePercent().toString() + "% | " + getCorrectPercent().toString() +"%"
  }

  fun getCorrectPercent(): Int {
    return getCorrectPercent(progress.correct.size).roundToInt()
  }

  fun getSavePercent(): Int {
    return getCorrectPercent(progress.save.size).roundToInt()
  }

  fun getCorrectPercent(num: Int): Double {
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
