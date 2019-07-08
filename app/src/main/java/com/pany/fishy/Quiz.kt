package com.pany.fishy


import android.view.View
class Quiz : View.OnClickListener {

    override fun onClick(view: View) {
        when(view.id) {
            R.id.buttonA -> checkAnswer(0)
            R.id.buttonB -> checkAnswer(1)
            R.id.buttonC -> checkAnswer(2)
            R.id.buttonNext -> initQuestion()
            R.id.buttonReset -> resetProgress()
        }
    }

    fun start() {
        loadData()
        updateProgress()
        initQuestion()
    }

    fun loadData() {
        // load questions and progress from json files
    }

    fun resetProgress() {
        // move all ids to list "new" and update progress
    }

    fun updateProgress() {
        // calculate percentage of correct answers
        // save lists
        // update ui elements
    }

    fun initQuestion() {
        // get random list
        // get random question id from list - state
        // update ui elements
    }

    fun checkAnswer(id: Int) {
        // check answer
        // move id in lists (sure = 5x correct, correct 1x-4x, new, wrong)
    }


}