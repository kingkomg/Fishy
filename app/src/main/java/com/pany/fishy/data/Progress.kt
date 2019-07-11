package com.pany.fishy.data

class Progress {
  var save: MutableList<Int> = mutableListOf()
  var correct: MutableMap<Int, Int> = mutableMapOf()
  var wrong: MutableList<Int> = mutableListOf()
  var new: MutableList<Int> = mutableListOf()

  fun isInitial(): Boolean {
    return save.isEmpty()
        && correct.isEmpty()
        && wrong.isEmpty()
        && new.isEmpty()
  }

  fun isFirstRun(): Boolean {
    return new.isNotEmpty()
  }

  fun moveWrong(questionId: Int) {
    when {
      save.contains(questionId) -> {
        save.remove(questionId)
        wrong.add(questionId)
      }
      new.contains(questionId) -> {
        new.remove(questionId)
        wrong.add(questionId)
      }
      correct.containsKey(questionId) -> {
        correct.remove(questionId)
        wrong.add(questionId)
      }
    }
  }

  fun moveCorrect(questionId: Int) {
    when {
      wrong.contains(questionId) -> {
        wrong.remove(questionId)
        correct[questionId] = 1
      }
      new.contains(questionId) -> {
        new.remove(questionId)
        correct[questionId] = 1
      }
      correct.containsKey(questionId) -> {
        val corrects = correct[questionId]!!.plus(1)
        if (corrects >= 5) {
          correct.remove(questionId)
          save.add(questionId)
        } else {
          correct[questionId] = corrects
        }
      }
    }
  }


}
