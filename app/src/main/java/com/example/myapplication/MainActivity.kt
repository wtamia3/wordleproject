package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Random target word generated for the game
    private var targetWord: String = FourLetterWordList.getRandomFourLetterWord()
    private var attempts = 0
    private val maxAttempts = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        val submitButton: Button = findViewById(R.id.submitButton)
        val resetButton: Button = findViewById(R.id.resetButton)
        val inputField: EditText = findViewById(R.id.inputField)
        val resultView: TextView = findViewById(R.id.resultView)

        submitButton.setOnClickListener {
            // Check if attempts are within limit
            if (attempts < maxAttempts) {
                val userGuess = inputField.text.toString().uppercase()
                val result = checkGuess(targetWord, userGuess)
                resultView.text = result
                attempts++

                if (result == "OOOO") {
                    // User has guessed correctly
                    Toast.makeText(this, "Congratulations, you've guessed it!", Toast.LENGTH_SHORT).show()
                    submitButton.isEnabled = false
                    resetButton.visibility = View.VISIBLE
                } else if (attempts == maxAttempts) {
                    // User has reached max attempts
                    Toast.makeText(this, "No more attempts. The word was $targetWord", Toast.LENGTH_SHORT).show()
                    submitButton.isEnabled = false
                    resetButton.visibility = View.VISIBLE
                }
            }
        }

        resetButton.setOnClickListener {
            // Reset the game state for a new game
            targetWord = FourLetterWordList.getRandomFourLetterWord()
            attempts = 0
            inputField.text.clear()
            resultView.text = ""
            submitButton.isEnabled = true
            resetButton.visibility = View.GONE
        }
    }

    // Function to check guess against the target word
    private fun checkGuess(targetWord: String, guess: String): String {
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
}
