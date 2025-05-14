package com.tictactoe.dokli.app;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TicTacToe {
    private HumanPlayerImpl player1;
    private HumanPlayerImpl player2;
    private HumanPlayerImpl currentPlayer;
    private Board board;
    public void start(){

    }
    private void switchCurrentPlayer(){

    }
    private boolean hasWinner(){
        return true;
    }
}
