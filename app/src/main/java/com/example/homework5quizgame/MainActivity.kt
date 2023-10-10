package com.example.homework5quizgame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homework5quizgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Binding, access any elements w/o declaring or initializing
    private lateinit var binding: ActivityMainBinding

    // Array of questions
    private val questions = arrayOf("What ancient Celtic festival is often considered the precursor to Halloween?", //Q1
        "Which country is believed to be the birthplace of Halloween?", //Q2
        "What is the traditional Halloween activity of carving faces into pumpkins called?", //Q3
        "Which classic monster is often associated with Transylvania and has a fear of garlic and a vulnerability to sunlight?", //Q4
        "What day is Halloween celebrated on?", //Q5
        "In Mexican culture, what holiday is celebrated on November 1st and 2nd, which has similarities to Halloween and honors deceased loved ones?", //Q6
        "What is the name of the practice where children dress up in costumes and go door-to-door asking for candy on Halloween?") //Q7

    // Array of possible answers
    private val options = arrayOf(arrayOf("Samhain", "Beltane", "Lughnasadh", "Imbolc"), //Q1
    arrayOf("United States", "Canada", "Ireland", "Mexico"), //Q2
    arrayOf("Pumpkin Painting", "Pumpkin Sculpting", "Pumpkin Carving", "Pumpkin Etching"), //Q3
    arrayOf("Werewolf", "Mummy", "Vampire", "Frankenstein"), //Q4
    arrayOf("October 30th", "October 31st", "November 1st", "October 29th"), //Q5
    arrayOf("Cinco de Mayo", "Dia de los Muertos (Day of the Dead)", "Mexican Independence Day", "Las Posadas"), //Q6
    arrayOf("Trick-or-Treating", "Candy Questing", "Costume Collecting", "Candy Hunting")) //Q7

    // Array of correct answers (index of correct answer, not string value)
    private val answers = arrayOf(0,2,2,2,1,1,0)

    // Current question Index and Score Value
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion() // display first question

        binding.option1.setOnClickListener{// Q1
            checkAnswer(0)
        }
        binding.option2.setOnClickListener{//Q2
            checkAnswer(1)
        }
        binding.option3.setOnClickListener{//Q3
            checkAnswer(2)
        }
        binding.option4.setOnClickListener{//Q4
            checkAnswer(3)
        }
        binding.restart.setOnClickListener {
            restartQuiz()
        }
    }
    // Correct answer button color
    private fun correctAnsColor(buttonIndex: Int){
        when(buttonIndex) {
            0 -> binding.option1.setBackgroundColor(Color.GREEN)
            1 -> binding.option2.setBackgroundColor(Color.GREEN)
            2 -> binding.option3.setBackgroundColor(Color.GREEN)
            3 -> binding.option4.setBackgroundColor(Color.GREEN)
        }
    }
    // Wrong answer button color
    private fun wrongAnsColor (buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.option1.setBackgroundColor(Color.RED)
            1 -> binding.option2.setBackgroundColor(Color.RED)
            2 -> binding.option3.setBackgroundColor(Color.RED)
            3 -> binding.option4.setBackgroundColor(Color.RED)
        }
    }
    // Reset all button colors after answer (correct or incorrect)
    private fun resetButtonColors() {
        binding.option1.setBackgroundColor(Color.BLACK)
        binding.option2.setBackgroundColor(Color.BLACK)
        binding.option3.setBackgroundColor(Color.BLACK)
        binding.option4.setBackgroundColor(Color.BLACK)
    }
    //  Display question based on index of options
    private fun displayQuestion(){
        binding.questionText.text= questions[currentQuestionIndex]
        binding.option1.text = options[currentQuestionIndex][0]
        binding.option2.text = options[currentQuestionIndex][1]
        binding.option3.text = options[currentQuestionIndex][2]
        binding.option4.text = options[currentQuestionIndex][3]
        resetButtonColors()
    }
    // Check for correct answer
    private fun checkAnswer(selectedAnsIndex: Int) {
        val correctAnsIndex = answers[currentQuestionIndex]

        // Iterate through the answer buttons
        for (i in answers.indices) {
            if (i == selectedAnsIndex) {
                if (i == correctAnsIndex) {
                    // Selected answer is correct, turn it green
                    correctAnsColor(i)
                } else {
                    // Selected answer is incorrect, turn it red
                    wrongAnsColor(i)
                }
            } else if (i == correctAnsIndex) {
                // Correct answer, turn it green
                correctAnsColor(i)
            }
        }

        if (selectedAnsIndex == correctAnsIndex) {
            // Show a Toast message for correct answer
            showToast("Correct :)")
            score++
        } else if (selectedAnsIndex != correctAnsIndex){
            // Show a Toast message for incorrect answer
            showToast("Incorrect :(")
        }

        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            binding.questionText.postDelayed({ displayQuestion() }, 750)
        } else{
            showResult()
        }
    }
    // restart quiz, clear score and display first question
    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.restart.isEnabled = false
    }
    // Toast call method
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    // Show score in ScoreActivity (new UI)
    private fun showResult() {
        val intent = Intent(this, ScoreActivity::class.java)
        // Pass the score as an extra
        intent.putExtra("score", score)
        startActivity(intent)
    }

} // end class