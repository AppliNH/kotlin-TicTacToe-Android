package com.example.kotlintictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appTitle.text = "Tic Tac Toe"
    }

    var activePlayer = 1

    var player1 = Player(1)
    var player2 = Player(2)

    var hasWinner: Boolean = false

    fun buClick(view: View) {
        val selectedButton = view as Button
        var buttonId = 0

        when (selectedButton.id) {
            R.id.bu1 -> buttonId = 1
            R.id.bu2 -> buttonId = 2
            R.id.bu3 -> buttonId = 3
            R.id.bu4 -> buttonId = 4
            R.id.bu5 -> buttonId = 5
            R.id.bu6 -> buttonId = 6
            R.id.bu7 -> buttonId = 7
            R.id.bu8 -> buttonId = 8
            R.id.bu9 -> buttonId = 9
        }

        playGame(buttonId, selectedButton)
    }

    fun initGame() {

        activePlayer = 1
        hasWinner = false
        player1.emptyCells()
        player2.emptyCells()
    }

    fun playGame(buttonId: Int, selectedButton: Button) {
        var pastPlayer: Player? = null

        if (activePlayer == 1) {
            selectedButton.text = "X"
            selectedButton.setBackgroundResource(R.color.red)
            player1.cells.add(buttonId)

            pastPlayer = player1
            activePlayer = 2
        } else {
            selectedButton.text = "O"
            selectedButton.setBackgroundResource(R.color.green)
            player2.cells.add(buttonId)
            pastPlayer = player2
            activePlayer = 1
        }
        selectedButton.isEnabled = false

        checkWinner(pastPlayer)
        resetTheGame(pastPlayer)

        if(!hasWinner && activePlayer == 2 && switchToBot.isChecked)
        {
            autoPlay()
        }

    }

    fun checkWinner(player: Player) {

        var winner: Player? = null

        val row1 = listOf(1, 2, 3)
        val row2 = listOf(4, 5, 6)
        val row3 = listOf(7, 8, 9)

        val col1 = listOf(1, 4, 7)
        val col2 = listOf(2, 5, 8)
        val col3 = listOf(3, 6, 9)

        val diag1 = listOf(1, 5, 9)
        val diag2 = listOf(3, 5, 7)
        Log.d("yeh",""+player.id +player.cells)

        when {
            player.cells.containsAll(row1) -> {
                winner = player
                Log.d("Victory","row1")}

            player.cells.containsAll(row2) -> {
                winner = player
                Log.d("Victory","row2")}

            player.cells.containsAll(row3) -> {
                winner = player
                Log.d("Victory","row3")}

            player.cells.containsAll(col1) -> {
                winner = player
                Log.d("Victory","col1")}

            player.cells.containsAll(col2) -> {
                winner = player
                Log.d("Victory","col2")}

            player.cells.containsAll(col3) -> {
                winner = player
                Log.d("Victory","col3")}

            player.cells.containsAll(diag1) -> {
                winner = player
                Log.d("Victory","diag1")}

            player.cells.containsAll(diag2) -> {
                winner = player
                Log.d("Victory","diag2")}
            else -> winner = null
        }


        if (winner != null) {
            hasWinner = true
            winner.increaseScore()

            val player1Score = findViewById<TextView>(R.id.player1Score)
            val player2Score = findViewById<TextView>(R.id.player2Score)

            player1Score.text = player1.score.toString()
            player2Score.text = player2.score.toString()

            when (winner?.id) {
                1 -> {Toast.makeText(this, "Winner is player 1 !", Toast.LENGTH_SHORT).show()}
                2 -> Toast.makeText(this, "Winner is player 2 !", Toast.LENGTH_SHORT).show()
                else -> Log.d("winner", "uh something wrong lmao")
            }
        }

    }

    fun resetTheGame(player: Player) {
        if (player.cells.size == 5 ) {
            hasWinner = true
            Toast.makeText(this, "Draft !", Toast.LENGTH_SHORT).show()
        }
        if(hasWinner)
        {
            bu1.isEnabled = true
            bu1.text = ""
            bu1.setBackgroundResource(R.color.myWhite)

            bu2.isEnabled = true
            bu2.text = ""
            bu2.setBackgroundResource(R.color.myWhite)

            bu3.isEnabled = true
            bu3.text = ""
            bu3.setBackgroundResource(R.color.myWhite)

            bu4.isEnabled = true
            bu4.text = ""
            bu4.setBackgroundResource(R.color.myWhite)

            bu5.isEnabled = true
            bu5.text = ""
            bu5.setBackgroundResource(R.color.myWhite)

            bu6.isEnabled = true
            bu6.text = ""
            bu6.setBackgroundResource(R.color.myWhite)

            bu7.isEnabled = true
            bu7.text = ""
            bu7.setBackgroundResource(R.color.myWhite)

            bu8.isEnabled = true
            bu8.text = ""
            bu8.setBackgroundResource(R.color.myWhite)

            bu9.isEnabled = true
            bu9.text = ""
            bu9.setBackgroundResource(R.color.myWhite)

            initGame()
        }
    }

    fun autoPlay(){
        var availableCells = ArrayList<Int>()

        for(cellId in 1..9){

            if( !(player1.cells.contains(cellId) || player2.cells.contains(cellId) ) ){
                availableCells.add(cellId)
            }
        }

        val r = Random()
        val rIndex = r.nextInt(availableCells.size)
        val cellId = availableCells[rIndex]

        var selectedCell : Button?
        selectedCell = when(cellId){
            1 -> bu1
            2 -> bu2
            3 -> bu3
            4 -> bu4
            5 -> bu5
            6 -> bu6
            7 -> bu7
            8 -> bu8
            9 -> bu9
            else -> { bu1}

        }

        playGame(cellId,selectedCell)

    }
}
