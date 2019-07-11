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
    val order = listOf(0, 1, 2).shuffled()
    val answers = listOf(question.answerA, question.answerB, question.answerC)
    this.answerA = answers[order[0]]
    this.answerB = answers[order[1]]
    this.answerC = answers[order[2]]
    this.correctAnswer = order.indexOf(0)
  }
}
