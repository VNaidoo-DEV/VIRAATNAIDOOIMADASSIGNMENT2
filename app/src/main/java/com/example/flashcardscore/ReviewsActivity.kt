package com.example.flashcardscore

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.animation.AnimationUtils
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
        val slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in)
        val slideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out)
        binding.txtResult.startAnimation(slideIn)
        btnBack.startAnimation(slideIn)
        Log.d("APP DEBUG", "ANIMATION STARTED")
        binding.txtResult.startAnimation(slideOut)
        btnBack.startAnimation(slideOut)
        Log.d("APP DEBUG", "ANIMATION STOPPED")
        val questions = intent.getStringArrayExtra("questions") ?: arrayOf() //WHAT SCORE THE USER
        val answers = intent.getBooleanArrayExtra("answers") ?: booleanArrayOf()
        val userAnswers = intent.getBooleanArrayExtra("userAnswers") ?: booleanArrayOf()//WHAT TOTAL THE USER GOT OUT OF 4

        val spannable = SpannableStringBuilder()

        for (i in questions.indices) {

            val question = questions[i]

            val isCorrect = i < userAnswers.size && userAnswers[i]
            val color = if (isCorrect) Color.GREEN else Color.RED

            spannable.append("Q${i + 1}: $question\n")

            val start = spannable.length
            spannable.append("Your Answer: ${if (isCorrect) "Correct" else "Wrong"}\n")

            spannable.setSpan(
                ForegroundColorSpan(color),
                start,
                spannable.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            spannable.append("\n")
        }
        binding.txtResult.text = spannable
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) //Start the MainActivity
        }
    }


    }
