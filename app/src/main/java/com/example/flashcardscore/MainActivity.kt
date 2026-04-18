package com.example.flashcardscore

import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var btnStart: Button // Declare the button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        btnStart = findViewById(R.id.btnStart) // Initialize the button
        Log.d("APP DEBUG", "onCreate called")
        btnStart.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            Log.d("APP DEBUG", "Button clicked")

        }


    }
}