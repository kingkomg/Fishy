package com.pany.fishy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

  private lateinit var quiz: Quiz
  private lateinit var textView: TextView
  private lateinit var buttonNext: Button
  private lateinit var buttonReset: Button
  private var buttons = listOf<Button>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    textView = findViewById(R.id.questionText)
    buttonNext = findViewById(R.id.buttonNext)
    buttonReset = findViewById(R.id.buttonReset)
    buttons = listOf(
      findViewById(R.id.buttonA),
      findViewById(R.id.buttonB),
      findViewById(R.id.buttonC)
    )

    startQuiz()
  }

  override fun onClick(view: View) {
    when (view.id) {
      R.id.buttonA -> checkAnswer(0)
      R.id.buttonB -> checkAnswer(1)
      R.id.buttonC -> checkAnswer(2)
      R.id.buttonNext -> initQuestion()
      R.id.buttonReset -> quiz.resetProgress()
    }
  }

  fun startQuiz() {
    quiz = Quiz()
    quiz.loadData(resources)
    initQuestion()
  }

  fun checkAnswer(selected: Int) {
    setAnswerButtonClickablity(false)
    val correct = quiz.question.correctAnswer

    if (correct == selected) {
      setButtonColor(buttons[selected], android.R.color.holo_green_light)
    } else {
      setButtonColor(buttons[selected], android.R.color.holo_red_light)
      setButtonColor(buttons[correct], android.R.color.holo_green_light)
    }
    // move id in lists (sure = 5x correct, correct 1x-4x, new, wrong)
    // serialize lists
  }

  fun initQuestion() {
    quiz.setNextQuestion()

    textView.text = quiz.question.questionText
    setAnswerButtonClickablity(true)
    setButtonColor(buttons[0], android.R.color.white)
    setButtonColor(buttons[1], android.R.color.white)
    setButtonColor(buttons[2], android.R.color.white)
  }

  fun setButtonColor(button: Button, color: Int) {
    button.setBackgroundColor(resources.getColor(color, this.theme))
  }

  fun setAnswerButtonClickablity(clickable: Boolean) {
    buttons[0].isClickable = clickable
    buttons[1].isClickable = clickable
    buttons[2].isClickable = clickable
  }
}
