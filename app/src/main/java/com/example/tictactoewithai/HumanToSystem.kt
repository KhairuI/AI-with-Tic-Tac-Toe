package com.example.tictactoewithai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_human_to_system.*

class HumanToSystem : AppCompatActivity() {

    private val  boardCells= Array(3){ arrayOfNulls<ImageView>(3)}
    var board= Board()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_human_to_system)

        loadBoard()
        restartButtonId.setOnClickListener{
            board= Board()
            resultTexTId.text= ""
            mapBoardToUI()
        }
    }

    private fun mapBoardToUI() {

        for(i in board.board.indices){
            for(j in board.board.indices){
                when(board.board[i][j]){
                    Board.player ->{
                        boardCells[i][j]?.setImageResource(R.drawable.zero_icon)
                        boardCells[i][j]?.isEnabled= false
                    }
                    Board.computer->{
                        boardCells[i][j]?.setImageResource(R.drawable.x_icon)
                        boardCells[i][j]?.isEnabled= false
                    }
                    else->{
                        boardCells[i][j]?.setImageResource(0)
                        boardCells[i][j]?.isEnabled= true

                    }
                }
            }
        }
    }

    private fun loadBoard() {

        for (i in boardCells.indices){
            for(j in boardCells.indices){

                boardCells[i][j] = ImageView(this);
                boardCells[i][j]?.layoutParams= GridLayout.LayoutParams().apply {
                    rowSpec= GridLayout.spec(i)
                    columnSpec= GridLayout.spec(j)
                    width= 220
                    height= 220
                    bottomMargin= 5
                    topMargin= 5
                    leftMargin= 5
                    rightMargin= 5
                }
                boardCells[i][j]?.setBackgroundColor(ContextCompat.getColor(this,R.color.colorBackground))
                boardCells[i][j]?.setOnClickListener(CellClickListener(i,j))
                gameBoardId.addView(boardCells[i][j])
            }


        }
    }

    inner class CellClickListener(
        private val i:Int, private val j:Int
    ): View.OnClickListener{
        override fun onClick(v: View?) {

            if(!board.isGameOver){
                val cell= Cell(i,j)
                board.placeMove(cell,Board.player)
                board.miniMax(0,Board.computer)

                board.computerMove?.let {
                    board.placeMove(it,Board.computer)
                }
                mapBoardToUI()
            }

            when{
                board.hasComputerWon() -> resultTexTId.text= "System Win"
                board.hasPlayerWon() -> resultTexTId.text="Human Win"
                board.isGameOver -> resultTexTId.text= "Game is Tied"
            }

        }

    }
}