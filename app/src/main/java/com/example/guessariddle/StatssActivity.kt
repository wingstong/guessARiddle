package com.example.guessariddle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class StatssActivity : AppCompatActivity() {

    lateinit var riddleCount : TextView
    lateinit var correctCount : TextView
    lateinit var incorrectCount : TextView

    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        val CurrentRiddle = intent.getIntExtra("CurrentRiddle", 0)
        val CorrectAnswerCount = intent.getIntExtra("CorrectAnswerCount", 0)

        btn = findViewById(R.id.buttonBack)
        btn.setOnClickListener {
            onBtnClick()
        }

        riddleCount = findViewById(R.id.textViewSolvedCount)
        correctCount = findViewById(R.id.textViewSolvedCorrect)
        incorrectCount = findViewById(R.id.textViewSolvedIncorrect)

        riddleCount.setText("Total Riddles Solved: ${CurrentRiddle}")
        correctCount.setText("Solved correctly: ${CorrectAnswerCount}")
        incorrectCount.setText("Solved incorrectly: ${CurrentRiddle - CorrectAnswerCount}")
    }

    private fun onBtnClick()
    {
        super.finish()
    }

}