package com.example.flashcardscore

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.animation.AnimationUtils
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
        "Updating apps improves security.",
        "Backing up data helps protect against ransomware.",
        "Hackers only target big companies, not individuals."
    )

    val correctAnswers = booleanArrayOf(true, false, false, true,true,false)
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
        Log.d("APP DEBUG", "onCreate called")
        userAnswers = BooleanArray(questions.size) // Initialize userAnswers array

        txtQuestion.text = questions[currentQuestion] //FIRST QUESTION
        val anim= AnimationUtils.loadAnimation(this,R.anim.fade_in)
        txtQuestion.startAnimation(anim)
        txtFeedback.startAnimation(anim)

        btnTrue.setOnClickListener {
            selectedAnswer = true
            txtFeedback.text = "Selected: True "
            txtFeedback.setTextColor(android.graphics.Color.GREEN)
        }

        btnFalse.setOnClickListener {
            selectedAnswer = false
           txtFeedback.text = "Selected: False"
            txtFeedback.setTextColor(android.graphics.Color.RED)
        }

        btnNext.setOnClickListener {
            if (selectedAnswer == null) {
                txtFeedback.text = "Please select an answer!"// Display error message if no answer is selected
                return@setOnClickListener

            }
            // Save answer
            userAnswers[currentQuestion] = selectedAnswer!!

            // Check answer
            if (selectedAnswer == correctAnswers[currentQuestion]) {
                score++
                txtFeedback.text = "✔️Correct!"
                txtFeedback.setTextColor(android.graphics.Color.GREEN)
            } else {
                txtFeedback.text = "❌Incorrect!"
                txtFeedback.setTextColor(android.graphics.Color.RED)
            }
            txtFeedback.postDelayed({
            // Move to next question
            currentQuestion++

            if (currentQuestion < questions.size) {

                // UPDATE QUESTION TEXT
                txtQuestion.text = questions[currentQuestion]
                selectedAnswer = null
                //  RESET FEEDBACK
                txtFeedback.text = ""

            } else {

                val intent = Intent(this, ScoreActivity::class.java)//Linking Screens
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                intent.putExtra("questions", questions)
                intent.putExtra("correctAnswers", correctAnswers)
                intent.putExtra("userAnswers", userAnswers)
                Log.d("APP DEBUG", "INTENT CONDITIONS EXECUTED")

                startActivity(intent)
                finish()
            }
        }, 1000) // Delay of 1 second before moving to the next question


    }

}
}









