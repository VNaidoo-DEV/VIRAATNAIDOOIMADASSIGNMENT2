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
       val txtScore = findViewById<TextView>(R.id.txtScore)
        val btnReview = findViewById<Button>(R.id.btnReview)

        val score = intent.getIntExtra("score", 0)//WHAT SCORE THE USER
        val total = intent.getIntExtra("total", 0)//WHAT TOTAL THE USER GOT OUT OF 4

        txtScore.text="Score : $score/$total"
        Log.d("APP DEBUG", "onCreate called")

        btnReview.setOnClickListener {

            Toast.makeText(this, "CLICKED", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ReviewsActivity::class.java)
            intent.putExtra("questions",intent.getStringArrayExtra("questions"))
            Log.d("APP DEBUG", "Button clicked")
            intent.putExtra("answers",intent.getBooleanArrayExtra("answers"))
            Log.d("APP DEBUG", "Button clicked")
            intent.putExtra("userAnswers",intent.getBooleanArrayExtra("userAnswers"))
            Log.d("APP DEBUG", "Button clicked")
            Toast.makeText(this, "Before Intent", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            Log.d("APP DEBUG", "ACT STARTED")
            Toast.makeText(this, "After Intent", Toast.LENGTH_SHORT).show()

        }

    }
}