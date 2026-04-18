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

class QuizActivity : AppCompatActivity() {
    lateinit var txtTimer: TextView
    lateinit var txtQuestion: TextView
    lateinit var btnTrue: Button
    lateinit var btnFalse: Button
    lateinit var txtFeedback: TextView
    lateinit var btnNext: Button
    lateinit var timer: CountDownTimer

    var currentIndex = 0
    var score = 0
    val timelimit = 120000L

    val questions = arrayOf(
        "Using strong passwords improves security." to true,
        "Public WiFi is always safe." to false,
        "Antivirus software is unnecessary." to false,
        "Updating apps improve security." to true
    )
    val userAnswers = mutableListOf<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        txtQuestion = findViewById(R.id.txtQuestion)
        txtTimer = findViewById(R.id.txtTimer)
        txtFeedback = findViewById(R.id.txtFeedback)
        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        btnNext = findViewById(R.id.btnNext)

        loadQuestion()
        startTimer()

        btnTrue.setOnClickListener { checkAnswer(true) }
        btnFalse.setOnClickListener { checkAnswer(false) }
        btnNext.setOnClickListener { nextQuestion() }

    }

    private fun loadQuestion() {
        txtQuestion.text = questions[currentIndex].first
        Log.d("APP DEBUG", "loadQuestion called")
    }

    private fun checkAnswer(answer: Boolean) {
        timer.cancel()
        val correct = questions[currentIndex].second
        if (answer == correct) {
            score++
            txtFeedback.text = "Correct!"
            txtFeedback.setTextColor(resources.getColor(R.color.accent))
        } else {
            txtFeedback.text = "Incorrect!"
            txtFeedback.setTextColor(resources.getColor(R.color.error))
        }
        userAnswers.add(answer)
        if (currentIndex == questions.size - 1) {
            btnNext.text = "Finish"

        }
        btnTrue.isEnabled = false
        btnFalse.isEnabled = false
        //btnNext.isEnabled = true
        Log.d("APP DEBUG", "checkAnswer called")
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timelimit, 120000) {
            override fun onTick(ms: Long) {
                txtTimer.text = "Time: ${ms / 120000L}"
            }
            override fun onFinish() {
                txtFeedback.text = "Time's up!"
                userAnswers.add(false)
                nextQuestion()
                Log.d("APP DEBUG", "onFinish called")

            }

        }.start()
        //timer.start()
    }
    private fun nextQuestion() {
        timer.cancel()
        currentIndex++
        if (currentIndex<questions.size) {
            loadQuestion()
            startTimer()
            btnTrue.isEnabled = true
            btnFalse.isEnabled = true
            txtFeedback.text = ""
            Log.d("APP DEBUG", "nextQuestion called")
        }else{
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("score", score)
            Log.d("APP DEBUG", "Button clicked")
            intent.putExtra("total", questions.size)
            Log.d("APP DEBUG", "Button clicked")
            intent.putExtra("questions",questions.map { it.first }.toTypedArray())
            Log.d("APP DEBUG", "Button clicked")
            intent.putExtra("answers", questions.map { it.second }.toTypedArray())
            Log.d("APP DEBUG", "Button clicked")
            intent.putExtra("userAnswers", userAnswers.toBooleanArray())
            Log.d("APP DEBUG", "Button clicked")
            startActivity(intent)
            finish()
            Log.d("APP DEBUG", "nextQuestion called")
        }
    }

    }

