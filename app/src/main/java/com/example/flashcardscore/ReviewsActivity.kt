package com.example.flashcardscore

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.flashcardscore.databinding.ActivityReviewsBinding

class ReviewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReviewsBinding
    lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityReviewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnBack=findViewById(R.id.btnBack)
        val questions = intent.getStringArrayExtra("questions") ?: arrayOf()
        val answers = intent.getBooleanArrayExtra("answers") ?: booleanArrayOf()
        val userAnswers = intent.getBooleanArrayExtra("userAnswers") ?: booleanArrayOf()

        val spannable = SpannableStringBuilder()

        for (i in questions.indices) {

            val question = questions[i]
            val userAnswer = if (i < userAnswers.size && userAnswers[i]) "Hack" else "Myth"
            val correctAnswer = if (i < answers.size && answers[i]) "Hack" else "Myth"

            val isCorrect = userAnswer == correctAnswer
            val color = if (isCorrect) Color.GREEN else Color.RED

            spannable.append("Q${i + 1}: $question\n")

            val startUser = spannable.length
            spannable.append("Your Answer: $userAnswer\n")

            spannable.setSpan(
                ForegroundColorSpan(color),
                startUser,
                spannable.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            spannable.append("Correct Answer: $correctAnswer\n\n")
        }

        binding.txtResult.text = spannable
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    }
