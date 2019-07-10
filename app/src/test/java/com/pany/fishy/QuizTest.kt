package com.pany.fishy

import org.junit.Assert.assertTrue
import org.junit.Test

class QuizTest {

  @Test
  fun loadQuestions() {
    val quiz = Quiz()
    assertTrue(quiz.loadQuestions().isNotEmpty())
  }

  @Test
  fun loadProgress() {
    val quiz = Quiz()
    val progress = quiz.loadProgress()
    assertTrue(progress.save.isNotEmpty())
    assertTrue(progress.correct.isNotEmpty())
    assertTrue(progress.wrong.isNotEmpty())
    assertTrue(progress.new.isEmpty())
  }
}
