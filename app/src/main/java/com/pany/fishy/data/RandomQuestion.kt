package com.pany.fishy.data

class RandomQuestion(question: Question, id: Int) {
  var id: Int
  var questionText: String
  val answerA: String
  val answerB: String
  val answerC: String
  val correctAnswer: Int

  init {
    this.id = id
    this.questionText = question.questionText
    this.answerA = question.answerA
    this.answerB = question.answerB
    this.answerC = question.answerC
    this.correctAnswer = 0
  }
}
