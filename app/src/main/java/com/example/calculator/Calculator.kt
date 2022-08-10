package com.example.calculator

import kotlin.system.exitProcess

class Calculator {

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

    private fun sqrt(input: Double) : Double {
        return kotlin.math.sqrt(input)
    }
    
    fun performOperation(input : String) : String {
        var result: Double
        val operands = ArrayDeque<Double>()
        val operations = ArrayDeque<String>()

        for (item in input) {

            if (item == '(') {
                operations.addFirst(item.toString())
            }

            else if (isOperand(item)) {
                operands.addFirst((item - '0').toDouble())
                println("this is a test -----> " + (item).code.toDouble())
            }

            else if (item == ')') {
                while (!operations.isEmpty() && operations.first() != "(") {
                    operands.addFirst(operate(operands, operations))
                }
                if (!operations.isEmpty())
                    operations.removeFirst()
            }
            else {
                while (!operations.isEmpty() && precedence(operations.first()) >= precedence(item.toString())) {
                    operands.addFirst(operate(operands, operations))
                }
                operations.addFirst(item.toString())
            }
        }
        while (!operations.isEmpty()) {
            operands.addFirst(operate(operands, operations))
        }
        result = operands.first()

        if(input.contains("sqrt")){
            result = sqrt(result)
        }
        return result.toString()
    }
}