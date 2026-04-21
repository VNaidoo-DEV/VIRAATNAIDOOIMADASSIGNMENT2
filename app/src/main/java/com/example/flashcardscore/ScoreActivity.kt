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
    lateinit var btnBack: Button
    lateinit var btnClose: Button

    lateinit var container: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)
        btnReview = findViewById(R.id.btnReview)
        txtResult = findViewById(R.id.txtResult)
        //txtResult2 = findViewById(R.id.txtResult2)
        container = findViewById(R.id.container)
        btnBack = findViewById(R.id.btnBack)
        btnClose = findViewById(R.id.btnClose)
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

        //txtResult.textSize = 24f
        //container.addView(txtResult)

        btnReview.setOnClickListener {
            btnReview.isEnabled = false
            for (i in questions.indices) {

                val txtResult2 = TextView(this)

                val isCorrect = userAnswers[i] == correct[i]

                val userText = "Your: ${if (userAnswers[i]) "True" else "False"}\n"


                val spannable = android.text.SpannableStringBuilder()

                // Q line (BLACK)
                val qText = "Q${i + 1}: ${questions[i]}\n"
                val startQ = spannable.length
                spannable.append(qText)
                spannable.setSpan(
                    android.text.style.ForegroundColorSpan(android.graphics.Color.BLACK),
                    startQ,
                    startQ + qText.length,
                    android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                // YOUR line (COLORED)
                val startUser = spannable.length
                spannable.append(userText)

                val color = if (isCorrect) {
                    android.graphics.Color.GREEN
                } else {
                    android.graphics.Color.RED
                }

                spannable.setSpan(
                    android.text.style.ForegroundColorSpan(color),
                    startUser,
                    startUser + userText.length,
                    android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                // CORRECT line (BLACK)
                // 🔹 CORRECT LINE (BLACK)
                val startCorrect = spannable.length
                val correctText = "Correct: ${if (correct[i]) "True" else "False"}"
                spannable.append(correctText)
                //spannable.append(correctText)
                spannable.setSpan(
                    android.text.style.ForegroundColorSpan(android.graphics.Color.BLACK),
                    startCorrect,
                    startCorrect + correctText.length,
                    android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )


                txtResult2.text = spannable
                txtResult2.textSize = 18f
                txtResult2.setBackgroundColor(android.graphics.Color.WHITE)
                txtResult2.setPadding(0, 16, 0, 16)

                container.addView(txtResult2)
            }


            }
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnClose.setOnClickListener {
            finish()
        }

            }
        }







