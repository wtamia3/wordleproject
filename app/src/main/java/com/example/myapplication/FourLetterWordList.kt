package com.example.myapplication

object FourLetterWordList {
    private val fourLetterWords = "AREA, ARMY, BABY, BACK, BALL, BAND, BANK, BASE, BODY, BOOK".split(", ")

    fun getRandomFourLetterWord(): String {
        return fourLetterWords.random().uppercase()
    }
}
