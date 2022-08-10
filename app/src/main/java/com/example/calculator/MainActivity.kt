package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.output)

        val calculator = Calculator()

        val button1 = findViewById<Button>(R.id.btn_1)
        button1.setOnClickListener { textView.text = "${textView.text}1"}

        val button2 = findViewById<Button>(R.id.btn_2)
        button2.setOnClickListener { textView.text = "${textView.text}2"}

        val button3 = findViewById<Button>(R.id.btn_3)
        button3.setOnClickListener { textView.text = "${textView.text}3"}

        val button4 = findViewById<Button>(R.id.btn_4)
        button4.setOnClickListener { textView.text = "${textView.text}4"}

        val button5 = findViewById<Button>(R.id.btn_5)
        button5.setOnClickListener { textView.text = "${textView.text}5"}

        val button6 = findViewById<Button>(R.id.btn_6)
        button6.setOnClickListener { textView.text = "${textView.text}6"}

        val button7 = findViewById<Button>(R.id.btn_7)
        button7.setOnClickListener { textView.text = "${textView.text}7"}

        val button8 = findViewById<Button>(R.id.btn_8)
        button8.setOnClickListener { textView.text = "${textView.text}8"}

        val button9 = findViewById<Button>(R.id.btn_9)
        button9.setOnClickListener { textView.text = "${textView.text}9"}

        val button0 = findViewById<Button>(R.id.btn_0)
        button0.setOnClickListener { textView.text = "${textView.text}0"}

        val btn_plus = findViewById<Button>(R.id.btn_plus)
        btn_plus.setOnClickListener { textView.text = "${textView.text}+"}

        val btn_minus = findViewById<Button>(R.id.btn_minus)
        btn_minus.setOnClickListener { textView.text = "${textView.text}-"}

        val btn_multiply = findViewById<Button>(R.id.btn_multiply)
        btn_multiply.setOnClickListener { textView.text = "${textView.text}X"}

        val btn_divide = findViewById<Button>(R.id.btn_divide)
        btn_divide.setOnClickListener { textView.text = "${textView.text}/"}

        val open_bracket = findViewById<Button>(R.id.open_bracket)
        open_bracket.setOnClickListener { textView.text = "${textView.text}("}

        val  closed_bracket = findViewById<Button>(R.id.closed_bracket)
        closed_bracket.setOnClickListener { textView.text = "${textView.text})"}

        val dot = findViewById<Button>(R.id.dot)
        dot.setOnClickListener { textView.text = "${textView.text}."}

        val clear = findViewById<Button>(R.id.btn_clear)
        clear.setOnClickListener { textView.text = ""}

        val del = findViewById<Button>(R.id.del)
        del.setOnClickListener {
            if(textView.text.toString() !="") {
                textView.text = textView.text.subSequence(0, textView.text.length - 1)
            }
        }

        val space = findViewById<Button>(R.id.space)
        space.setOnClickListener { textView.text ="${textView.text} "}

        val sqrt = findViewById<Button>(R.id.square_root)
        sqrt.setOnClickListener {textView.text = "sqrt(${textView.text})"}

        val ans = findViewById<Button>(R.id.btn_ans)
        ans.setOnClickListener {
            textView.text = calculator.performOperation(textView.text.toString())
        }
    }
}