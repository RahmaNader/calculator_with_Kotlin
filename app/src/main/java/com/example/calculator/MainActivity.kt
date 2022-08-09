package com.example.calculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.output)
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
        ////////////////////////////////////////////////////////////////////
        val ans = findViewById<Button>(R.id.btn_ans)
        ans.setOnClickListener {
            val operation = textView.text.toString()
            val result: Double
            val operands = ArrayDeque<Double>()
            val operations = ArrayDeque<String>()

            for(item in operation){
                if(item == '('){
                    operations.addFirst(item.toString())
                }
                else if(isOperand(item)){
                    operands.addFirst((item - '0').toDouble())
                }
                else if(item == ')'){
                    while(!operations.isEmpty() && operations.first() != "("){
                        operands.addFirst(operate(operands, operations))
                    }
                    if(!operations.isEmpty())
                        operations.removeFirst()
                }
                else{
                    while(!operations.isEmpty() && precedence(operations.first())>= precedence(item.toString())){
                        operands.addFirst(operate(operands, operations))
                    }
                    operations.addFirst(item.toString())
                }
            }
            while(!operations.isEmpty()){
                operands.addFirst(operate(operands, operations))
            }
            result = operands.first()


            textView.text = result.toString()
        }


    }

    private fun isOperand(c: Char): Boolean{
        if(c != '+' && c != '-' && c != '*' && c != '/' && c != '(' && c != ')'){
            return true
        }
        return false
    }

    private fun precedence(operation: String): Int{
        if(operation == "+" || operation == "-"){
            return 1
        }
        else if(operation == "*" || operation == "/"){
            return 2
        }
        return -1
    }

    private fun operate (operands: ArrayDeque<Double>, operations: ArrayDeque<String>): Double{
        val operand2 = operands.first()
        operands.removeFirst()
        val operand1 = operands.first()
        operands.removeFirst()
        val operation = operations.first()
        operations.removeFirst()
        try{
            when (operation) {
                "+" -> {
                    return (operand1 + operand2)
                }
                "-" -> {
                    return (operand1 - operand2)
                }
                "*" -> {
                    return (operand1 * operand2)
                }
                "/" -> {
                    if(operand2 == 0.0){
                        throw Exception()
                    }
                    return (operand1 / operand2)
                }
            }
        }
        catch(e: Exception){
            println("can't divide by zero")
            exitProcess(1)
        }
        return -1.0
    }
}