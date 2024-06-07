package com.example.guessariddle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Random


class MainActivity : AppCompatActivity() {
    var riddles = listOf(
        Pair("Мчится печка впереди, тащит избы позади", "поезд"),
        Pair("Только на небо приплыли, тут же солнце собой скрыли", "облака"),
        Pair("На верхушке стебелька солнышко и облака", "ромашка"),
        Pair("Колются иголки у зеленой...", "ёлки"),
        Pair("Солнце с брызгами играет, семь цветных полос включает", "радуга"),
        Pair("Кто укажет парой рук, путь на Север и на Юг?", "компас"),
        Pair("Её узнаешь по простой примете: нет деревца белей на свете", "берёза"),
        Pair("Растянулась, как гармошка, Чудо-печка под окошком", "батарея"),
        Pair("На зеленой горочке – фиолетовые ёлочки.", "сирень"),
        Pair("Ест снегирь и свиристель красну ягодку на ней", "рябина"),
        Pair("Серые комочки на красненьком пруточке","верба"),
        Pair("Лапы есть, а не ходит, иголки есть, а не шьёт","ёлка"),
        Pair("В круглый коробок спрятался дубок", "жёлудь"),
        Pair("... рубль бережет", "копейка"),
        Pair("На дворе растёт трава, золотая голова", "Одуванчик"),
        Pair("Восемь рук иль восемь ног? Восьмирук? Нет! ...", "осьминог"),
        Pair("За сметану, хлеб и сыр, в кассе чек пробьёт...", "кассир"),
        Pair("Он разрежет всё подряд! Булку, фрукты и салат!", "нож")
    )

    lateinit var name : TextView
    lateinit var riddleText : TextView
    lateinit var correctOrNo : TextView

    lateinit var riddle : Button
    lateinit var answer : Button
    lateinit var statistic : Button
    lateinit var newGame : Button
    lateinit var statText : TextView

    var correctAnswerCount = 0
    var currentRiddle = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        name = findViewById(R.id.textViewHello)
        riddleText = findViewById(R.id.textViewRiddle)
        correctOrNo = findViewById(R.id.textViewIsCorrect)
        statText = findViewById(R.id.textViewGuessed)

        riddle = findViewById(R.id.buttonRiddle)
        riddle.setOnClickListener {
            onRiddleClick()
        }

        answer = findViewById(R.id.button1)
        answer.setOnClickListener {
            onAnswerClick()
        }
        answer.isEnabled = false

        statistic = findViewById(R.id.buttonStats)
        statistic.setOnClickListener {
            onStatisticClick()
        }
        statistic.isEnabled = false

        newGame = findViewById(R.id.buttonNewGame)
        newGame.setOnClickListener {
            onNewGameClick()
        }

        riddles = riddles.shuffled(Random())
    }

    private fun onRiddleClick() {
        answer.isEnabled = true
        riddle.isEnabled = false
        correctOrNo.text = ""

        riddleText.text = riddles[currentRiddle].first
    }
    private fun onAnswerClick()
    {
        val builder = AlertDialog.Builder(this)
        val dialogLayout = layoutInflater.inflate(R.layout.activity_answer, null)

        val ans = dialogLayout.findViewById<TextInputLayout>(R.id.editTextAnswer)
        val editText = ans.findViewById<TextInputEditText>(R.id.inputAnswer)
        editText.requestFocus()
        editText.setShowSoftInputOnFocus(true)

        builder.setView(dialogLayout)
            .setPositiveButton("Ответить") { dialog, which ->
                val ans = dialogLayout.findViewById<TextInputLayout>(R.id.editTextAnswer)

                if (ans.editText?.text.toString() == riddles[currentRiddle].second)
                {
                    correctOrNo.text = "Правильно!"
                    correctAnswerCount++
                }
                else
                {
                    correctOrNo.text = "Неправильно!"
                }
                answer.isEnabled = false
                riddle.isEnabled = true
                currentRiddle++
                statText.text = "Решено: ${currentRiddle}/10"

                if (currentRiddle == 10)
                {
                    statistic.isEnabled = true
                    answer.isEnabled = false
                    riddle.isEnabled = false
                }
            }

        builder.create().show()
    }

    private fun onStatisticClick()
    {
        val intent = Intent(this, StatssActivity::class.java)
        intent.putExtra("CorrectAnswerCount", correctAnswerCount)
        intent.putExtra("CurrentRiddle", currentRiddle)

        startActivity(intent)
    }

    private fun onNewGameClick()
    {
        riddles = riddles.shuffled(Random())
        currentRiddle = 0
        riddleText.text = ""

        riddle.isEnabled = true
        answer.isEnabled = false
        statistic.isEnabled = false

        correctOrNo.text = ""
        statText.text = "Решено: ${currentRiddle}/10"
    }
}
