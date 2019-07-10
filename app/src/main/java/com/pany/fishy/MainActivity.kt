package com.pany.fishy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

  lateinit var quiz: Quiz

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    startQuiz()
  }

  override fun onClick(view: View) {
    when (view.id) {
      R.id.buttonA -> quiz.checkAnswer(0)
      R.id.buttonB -> quiz.checkAnswer(1)
      R.id.buttonC -> quiz.checkAnswer(2)
      R.id.buttonNext -> updateView()
      R.id.buttonReset -> quiz.resetProgress()
    }
  }

  fun startQuiz() {
    quiz = Quiz()
    quiz.loadData(resources)

//    updateProgress(view)
//    initQuestion(view)
  }

  fun updateView() {
    val textView = findViewById<TextView>(R.id.questionText)
    textView.text = "new text"
    quiz.getNextQuestion()
    // update ui elements
  }
}
