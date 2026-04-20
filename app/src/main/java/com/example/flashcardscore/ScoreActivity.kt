package com.example.flashcardscore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreActivity : AppCompatActivity() {
    lateinit var btnReview: Button
    lateinit var txtResult: TextView
    lateinit var txtResult2: TextView
    lateinit var container: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)
        btnReview = findViewById(R.id.btnReview)
        txtResult = findViewById(R.id.txtResult)
        txtResult2 = findViewById(R.id.txtResult2)
        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        val questions = intent.getStringArrayExtra("questions") ?: arrayOf()
        val correct = intent.getBooleanArrayExtra("correctAnswers") ?: booleanArrayOf()
        val userAnswers = intent.getBooleanArrayExtra("userAnswers") ?: booleanArrayOf()



        if (score == total) {
            txtResult.text = "Well done $score/$total"
        }else{
            txtResult.text = "Try Again $score/$total"
        }

        txtResult.textSize = 24f
        container.addView(txtResult)
        btnReview.text = "Review Answers"
        container.addView(btnReview)

        btnReview.setOnClickListener {
            btnReview.isEnabled = false
            for (i in questions.indices) {
                txtResult2.text = """
                Q${i + 1}: ${questions[i]}
                Your : ${if (userAnswers[i]) "True" else "False"}
                Correct : ${if (correct[i]) "True" else "False"}
                """.trimIndent()


                container.addView(txtResult2)



            }
        }




        }

    }
