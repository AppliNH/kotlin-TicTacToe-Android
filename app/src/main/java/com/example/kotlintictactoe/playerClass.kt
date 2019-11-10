package com.example.kotlintictactoe

import java.util.ArrayList

class Player(val id: Int, var cells: ArrayList<Int> = arrayListOf<Int>(), var score: Int = 0){
    fun emptyCells()
    {
        cells = arrayListOf<Int>()
    }
    fun increaseScore()
    {
        score +=1
    }
}