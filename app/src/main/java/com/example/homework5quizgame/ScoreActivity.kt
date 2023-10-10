package com.example.homework5quizgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.score_display)

        // Retrieve the score from intent extras
        val score = intent.getIntExtra("score", 0)

        val scoreDisplay = findViewById<TextView>(R.id.scoreDisplay)
        val restart = findViewById<Button>(R.id.restart)

        // Set the text of the scoreDisplay
        scoreDisplay.text = "Your Score: $score/7"

        // Set a click listener for the "Restart Quiz" button
        restart.setOnClickListener {
            // Start the MainActivity when the button is clicked
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}