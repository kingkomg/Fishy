package com.pany.fishy.data

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ProgressTest {

  lateinit var progress: Progress

  @Before
  fun setUp() {
    progress = Progress()
  }

  @Test
  fun isInitial_true() {
    assertTrue(progress.isInitial())
  }

  @Test
  fun isInitial_false() {
    progress.new.add(1)
    assertFalse(progress.isInitial())
  }

  @Test
  fun isFirstRun_true() {
    progress.new.add(1)
    assertTrue(progress.isFirstRun())
  }

  @Test
  fun isFirstRun_false() {
    assertFalse(progress.isFirstRun())
  }

  @Test
  fun moveWrong_save() {
    initListsAfterFirstRun()
    progress.moveWrong(1)

    assertFalse(progress.save.contains(1))
    assertTrue(progress.save.containsAll(listOf(2, 3)))
    assertTrue(progress.wrong.containsAll(listOf(1, 7, 8, 9)))
  }

  @Test
  fun moveWrong_new() {
    progress.new = mutableListOf(1, 2, 3)
    progress.moveWrong(1)

    assertFalse(progress.new.contains(1))
    assertTrue(progress.new.containsAll(listOf(2, 3)))
    assertTrue(progress.wrong.contains(1))
  }

  @Test
  fun moveWrong_correct() {
    initListsAfterFirstRun()
    progress.moveWrong(4)

    assertFalse(progress.correct.containsKey(4))
    assertTrue(progress.correct.containsKey(5))
    assertTrue(progress.save.containsAll(listOf(1, 2, 3)))
    assertTrue(progress.wrong.containsAll(listOf(4, 7, 8, 9)))
  }

  @Test
  fun moveCorrect_wrong() {
    initListsAfterFirstRun()
    progress.moveCorrect(7)

    assertTrue(progress.correct.containsKey(7))
    assertFalse(progress.wrong.contains(7))
    assertTrue(progress.save.containsAll(listOf(1, 2, 3)))
    assertTrue(progress.wrong.containsAll(listOf(8, 9)))
  }

  @Test
  fun moveCorrect_new() {
    progress.new = mutableListOf(1, 2, 3)
    progress.moveCorrect(2)

    assertFalse(progress.new.contains(2))
    assertTrue(progress.new.containsAll(listOf(1, 3)))
    assertTrue(progress.correct.containsKey(2))
  }

  @Test
  fun moveCorrect_correct() {
    initListsAfterFirstRun()
    progress.moveCorrect(4)

    assertTrue(progress.correct.containsKey(4))
    assertEquals(2, progress.correct[4])
  }

  @Test
  fun moveCorrect_correct_to_save() {
    initListsAfterFirstRun()
    progress.moveCorrect(5)

    assertFalse(progress.correct.containsKey(5))
    assertTrue(progress.save.containsAll(listOf(1, 2, 3, 5)))
  }

  fun initListsAfterFirstRun() {
    progress.save = mutableListOf(1, 2, 3)
    progress.correct = mutableMapOf(4 to 1, 5 to 4)
    progress.wrong = mutableListOf(7, 8, 9)
  }
}
