package com.example.calculator

import kotlin.system.exitProcess

class Calculator {

    private fun isOperand(c: String): Boolean{
        if(c != "+" && c != "-" && c != "X" && c != "/" && c != "(" && c != ")"){
            return true
        }
        return false
    }

    private fun precedence(operation: String): Int{
        if(operation == "+" || operation == "-"){
            return 1
        }
        else if(operation == "X" || operation == "/"){
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
                "X" -> {
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
    
    fun performOperation(input: String) : String {
        val args: Array<String> = input.split(" ").toTypedArray()
        var result: Double
        val operands = ArrayDeque<Double>()
        val operations = ArrayDeque<String>()

        for(item in args){
            if(args[0] == "sqrt("){
                return sqrt(args[1].toDouble()).toString()
            }
            else if(item == "("){
                operations.addFirst(item)
            }
            else if(isOperand(item)){
                operands.addFirst(item.toDouble())
            }
            else if(item == ")"){
                while(!operations.isEmpty() && operations.first() != "("){
                    operands.addFirst(operate(operands, operations))
                }
                if(!operations.isEmpty())
                    operations.removeFirst()
            }
            else{
                while(!operations.isEmpty() && precedence(operations.first())>= precedence(item)){
                    operands.addFirst(operate(operands, operations))
                }
                operations.addFirst(item)
            }
        }
        while(!operations.isEmpty()){
            operands.addFirst(operate(operands, operations))
        }

        result = operands.first()

        return result.toString()
    }
}