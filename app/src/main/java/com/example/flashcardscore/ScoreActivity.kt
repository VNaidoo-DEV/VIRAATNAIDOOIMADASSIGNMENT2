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
        Log.d("APP DEBUG", "onCreate called")
        btnReview = findViewById(R.id.btnReview)
        txtResult = findViewById(R.id.txtResult)
        container = findViewById(R.id.container)
        btnBack = findViewById(R.id.btnBack)
        btnClose = findViewById(R.id.btnClose)
        Log.d("APP DEBUG","INTENT PULLED FROM QUIZ")
        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        val questions = intent.getStringArrayExtra("questions") ?: arrayOf()
        val correct = intent.getBooleanArrayExtra("correctAnswers") ?: booleanArrayOf()
        val userAnswers = intent.getBooleanArrayExtra("userAnswers") ?: booleanArrayOf()
        val slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in)
        txtResult.startAnimation(slideIn)//ANIMATION
        btnReview.startAnimation(slideIn)
        btnBack.startAnimation(slideIn)
        btnClose.startAnimation(slideIn)
        Log.d("APP DEBUG","ANIMATION DONE")


        val percentage = ((score.toDouble() / total) * 100).toInt()
        //Calculating percentage of correct answers
        if (percentage >= 75) {
            txtResult.text = "Well done $score/$total ($percentage%)"
            txtResult.setTextColor(android.graphics.Color.GREEN)
        } else {
            txtResult.text = "Try again $score/$total ($percentage%)"
            txtResult.setTextColor(android.graphics.Color.RED)
        }
        Log.d("APP DEBUG","IF CONDITION EXECUTED")


        //DECLARED BTNREVIEW SETONCLICKLISTENER OBJECT
        btnReview.setOnClickListener{
            Log.d("APP DEBUG","REVIEW BUTTON CLICKED")
            btnReview.isEnabled = false// Disable the button to prevent multiple clicks
            for (i in questions.indices) {
                Log.d("APP DEBUG","FOR LOOP EXECUTED")
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

                // YOUR : line (COLORED)
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
                Log.d("APP DEBUG","SPANNABLE CONDITION EXECUTED")
                txtResult2.startAnimation(slideIn)
                txtResult2.text = spannable
                txtResult2.textSize = 18f
                txtResult2.setBackgroundColor(android.graphics.Color.WHITE)
                txtResult2.setPadding(0, 16, 0, 16)
                Log.d("APP DEBUG","TEXTVIEW CONDITION EXECUTED")
                container.addView(txtResult2)
            }


            }
        btnBack.setOnClickListener {
            Log.d("APP DEBUG","BACK BUTTON CLICKED")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnClose.setOnClickListener {
            Log.d("APP DEBUG","CLOSE BUTTON CLICKED")
            finishAffinity()
        }

            }
        }







