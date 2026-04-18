package com.example.flashcardscore

import android.content.Intent
import android.os.Bundle
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reviews)
        //txtResult.text = "HELLO REVIEW SCREEN WORKS"


                binding = ActivityReviewsBinding.inflate(layoutInflater)
                setContentView(binding.root)

                val questions = intent.getStringArrayExtra("questions") ?: emptyArray()
                val answers = intent.getBooleanArrayExtra("answers") ?: booleanArrayOf()
                val userAnswers = intent.getBooleanArrayExtra("userAnswers") ?: booleanArrayOf()

                val result = StringBuilder()

                for (i in questions.indices) {
                    val user = if (i < userAnswers.size && userAnswers[i]) "Hack" else "Myth"
                    val correct = if (i < answers.size && answers[i]) "Hack" else "Myth"

                    result.append(
                        """
                Q${i + 1}: ${questions[i]}
                Your Answer: $user
                Correct Answer: $correct

            """.trimIndent()
                    )
                }

                binding.txtResult.text = result.toString()
            }
        }

