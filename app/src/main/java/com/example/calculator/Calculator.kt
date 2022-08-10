package com.example.calculator

import kotlin.system.exitProcess

class Calculator {
    fun isOperand(c: Char): Boolean{
        if(c != '+' && c != '-' && c != '*' && c != '/' && c != '(' && c != ')'){
            return true
        }
        return false
    }

    fun precedence(operation: String): Int{
        if(operation == "+" || operation == "-"){
            return 1
        }
        else if(operation == "*" || operation == "/"){
            return 2
        }
        return -1
    }

    fun operate (operands: ArrayDeque<Double>, operations: ArrayDeque<String>): Double{
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