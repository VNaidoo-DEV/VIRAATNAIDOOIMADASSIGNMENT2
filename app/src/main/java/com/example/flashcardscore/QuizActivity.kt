package com.example.flashcardscore

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
//import android.os.CountDownTimer
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.color.utilities.Score

class QuizActivity : AppCompatActivity() {
    lateinit var txtQuestion: TextView
    lateinit var btnTrue: Button
    lateinit var btnFalse: Button
    lateinit var txtFeedback: TextView
    lateinit var btnNext: Button

    var currentQuestion = 0
    var score = 0
    val questions = arrayOf(
        "Using strong passwords improves security.",
        "Public WiFi is always safe.",
        "Antivirus is unnecessary.",
        "Updating apps improves security."
    )

    val correctAnswers = booleanArrayOf(true, false, false, true)
    lateinit var userAnswers: BooleanArray
    var selectedAnswer: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        txtQuestion = findViewById(R.id.txtQuestion)
        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        txtFeedback = findViewById(R.id.txtFeedback)
        btnNext = findViewById(R.id.btnNext)

        userAnswers = BooleanArray(questions.size) // Initialize userAnswers array

        txtQuestion.text = questions[currentQuestion] //FIRST QUESTION

        btnTrue.setOnClickListener {
            selectedAnswer = true
            txtFeedback.text = "Selected: True "
        }

        btnFalse.setOnClickListener {
            selectedAnswer = false
           txtFeedback.text = "Selected: False"
        }

        btnNext.setOnClickListener {
            if (selectedAnswer == null) {
                txtFeedback.text = "Please select an answer!"
                return@setOnClickListener
            }
            // Save answer
            userAnswers[currentQuestion] = selectedAnswer!!

            // Check answer
            if (selectedAnswer == correctAnswers[currentQuestion]) {
                score++
                txtFeedback.text = "Correct!"
            } else {
                txtFeedback.text = "Incorrect!"
            }

            // Move to next question
            currentQuestion++

            if (currentQuestion < questions.size) {

                // UPDATE QUESTION TEXT
                txtQuestion.text = questions[currentQuestion]
                selectedAnswer = null
                //  RESET FEEDBACK
                txtFeedback.text = ""

            } else {

                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                intent.putExtra("questions", questions)
                intent.putExtra("correctAnswers", correctAnswers)
                intent.putExtra("userAnswers", userAnswers)

                startActivity(intent)
                finish()
            }
        }


    }

}









