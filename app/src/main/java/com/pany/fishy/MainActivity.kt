package com.pany.fishy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import java.io.File

class MainActivity : AppCompatActivity(), View.OnClickListener {

  private lateinit var quiz: Quiz
  private lateinit var textView: TextView
  private lateinit var progressText: TextView
  private lateinit var progressBar: ProgressBar
  private lateinit var buttonNext: Button
  private lateinit var buttonReset: Button
  private lateinit var buttons: List<Button>
  private lateinit var progressFile: File

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    textView = findViewById(R.id.questionText)
    progressText = findViewById(R.id.progressText)
    progressBar = findViewById(R.id.progressBar)
    buttonNext = findViewById(R.id.buttonNext)
    buttonReset = findViewById(R.id.buttonReset)
    buttons = listOf(
      findViewById(R.id.buttonA),
      findViewById(R.id.buttonB),
      findViewById(R.id.buttonC)
    )

    progressFile = File(baseContext.filesDir, "progress")

    startQuiz()
  }

  override fun onClick(view: View) {
    when (view.id) {
      R.id.buttonA -> checkAnswer(0)
      R.id.buttonB -> checkAnswer(1)
      R.id.buttonC -> checkAnswer(2)
      R.id.buttonNext -> initQuestion()
      R.id.buttonReset -> resetProgress()
    }
  }

  private fun startQuiz() {
    quiz = Quiz()
    quiz.loadQuestions(resources.openRawResource(R.raw.questions).bufferedReader().use { it.readText() })
    quiz.loadProgress(File(baseContext.filesDir, "progress"))
    initQuestion()
    updateProgress()
  }

  private fun checkAnswer(selected: Int) {
    setAnswerButtonClickable(false)

    val correct = quiz.evaluate(selected)
    if (correct == selected) {
      setButtonColor(buttons[selected], android.R.color.holo_green_light)
    } else {
      setButtonColor(buttons[selected], android.R.color.holo_red_light)
      setButtonColor(buttons[correct], android.R.color.holo_green_light)
    }
    updateProgress()
    quiz.saveProgress(progressFile)
  }

  private fun initQuestion() {
    val nextQuestion = quiz.getNextQuestion()
    textView.text = nextQuestion.questionText
    setAnswerButtonClickable(true)
    setButtonColor(buttons[0], android.R.color.white)
    buttons[0].text = nextQuestion.answerA
    setButtonColor(buttons[1], android.R.color.white)
    buttons[1].text = nextQuestion.answerB
    setButtonColor(buttons[2], android.R.color.white)
    buttons[2].text = nextQuestion.answerC
  }

  private fun resetProgress() {
    quiz.resetProgress(progressFile)
    updateProgress()
  }

  private fun updateProgress() {
    progressText.text = quiz.getProcessNumbers()
    progressBar.secondaryProgress = quiz.getSavePercent()
    progressBar.progress = quiz.getCorrectPercent() + quiz.getSavePercent()
  }

  private fun setButtonColor(button: Button, color: Int) {
    button.setBackgroundColor(resources.getColor(color, this.theme))
  }

  private fun setAnswerButtonClickable(clickable: Boolean) {
    buttons[0].isClickable = clickable
    buttons[1].isClickable = clickable
    buttons[2].isClickable = clickable
  }
}
