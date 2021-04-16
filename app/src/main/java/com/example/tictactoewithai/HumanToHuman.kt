package com.example.tictactoewithai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import kotlinx.android.synthetic.main.activity_human_to_human.*

class HumanToHuman : AppCompatActivity(), View.OnClickListener {
    private val  buttons= Array(3){ arrayOfNulls<Button>(3)}
    private var player1Turn = true
    private var roundCount = 0
    private var player1Points = 0
    private var player2Points = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_human_to_human)

        findSection()
    }

    private fun findSection() {

        for(i in 0..2){
            for(j in 0..2){
                val buttonId = "button_$i$j"
                val resID = resources.getIdentifier(buttonId, "id", packageName)
                buttons[i][j] = findViewById(resID)
                buttons[i][j]?.setOnClickListener(this)
            }
        }
        humanRestartButtonId.setOnClickListener {
            resetGame()
        }
    }

    override fun onClick(v: View?) {
        if ((v as Button).text.toString() != "") {
            return
        }

        if (player1Turn) {
            v.text = "X"
        } else {
            v.text = "O"
        }
        roundCount++

        if (checkWin()) {
            if (player1Turn) {
                player1Wins()
            } else {
                player2Wins()
            }
        } else if (roundCount == 9) {
            draw()
        } else {
            player1Turn = !player1Turn
        }

    }

    private fun checkWin(): Boolean {

        val field = Array(3) { arrayOfNulls<String>(3) }
        for (i in 0..2) {
            for (j in 0..2) {
                field[i][j] = buttons[i][j]!!.text.toString()
            }
        }
        for (i in 0..2) {
            if (field[i][0] == field[i][1] && field[i][0] == field[i][2] && field[i][0] != "") {
                return true
            }
        }
        for (i in 0..2) {
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] != "") {
                return true
            }
        }
        if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] != "") {
            return true
        }
        return if (field[0][2] == field[1][1] && field[0][2] == field[2][0] && field[0][2] != "") {
            true
        } else false

    }

    private fun player1Wins() {
        player1Points++
        humanResultId.text="Player 1 Win!"
        updatePointText()
        resetBoard()
    }

    private fun player2Wins() {
        player2Points++
        humanResultId.text="Player 2 Win!"
        updatePointText()
        resetBoard()
    }

    private fun draw() {
        humanResultId.text="Match Tied"
        resetBoard()
    }

    private fun resetGame() {
        player1Points = 0
        player2Points = 0
        updatePointText()
        resetBoard()
        humanResultId.text=""

    }

    private fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j]!!.text = ""
            }
        }
        roundCount = 0
        player1Turn = true
    }

    private fun updatePointText() {
        player1Result.text= "Player 1: $player1Points"
        player2Result.text= "Player 2: $player2Points"
    }
}