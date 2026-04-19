package com.example.flashcardscore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)
       val txtScore = findViewById<TextView>(R.id.txtScore)//Declaring the textview
        val btnReview = findViewById<Button>(R.id.btnReview)//Declaring the button
        Log.d("APP DEBUG", "VAR DECLARED")
        val score = intent.getIntExtra("score", 0)//WHAT SCORE THE USER
        val total = intent.getIntExtra("total", 0)//WHAT TOTAL THE USER GOT OUT OF 4
        val questions = intent.getStringArrayExtra("questions") ?: arrayOf()
        val answers = intent.getBooleanArrayExtra("answers") ?: booleanArrayOf()
        val userAnswers = intent.getBooleanArrayExtra("userAnswers") ?: booleanArrayOf()
        val percentage = (score.toDouble() / total) * 100

        val feedback = if (percentage >= 60) "MASTER HACKER" else "STAY SAFE ONLINE"
        Log.d("APP DEBUG", "OUTPUT MSSG")
        txtScore.text = "Score: $score/$total (${percentage.toInt()}%)\n$feedback"
        Log.d("APP DEBUG", "OUTPUT TOTAL")


        Log.d("APP DEBUG", "onCreate called")

        btnReview.setOnClickListener {
            //Start the ReviewsActivity
            val intent = Intent(this, ReviewsActivity::class.java)

            intent.putExtra("questions", questions)
            intent.putExtra("answers", answers)
            intent.putExtra("userAnswers", userAnswers)

            startActivity(intent)
        }

    }
}