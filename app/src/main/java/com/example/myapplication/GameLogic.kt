package com.example.myapplication


fun checkGuess(targetWord: String, guess: String): String {
    var result = ""
    for (i in guess.indices) {
        result += when {
            guess[i] == targetWord[i] -> "O"  // Correct letter, correct position
            guess[i] in targetWord -> "+"     // Correct letter, wrong position
            else -> "X"                       // Incorrect letter
        }
    }
    return result
}
