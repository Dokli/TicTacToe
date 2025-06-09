package com.tictactoe.dokli.app;

import lombok.AllArgsConstructor;

import java.util.InputMismatchException;
import java.util.Scanner;

@AllArgsConstructor
public class TicTacToe {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;

    public TicTacToe() {
        this.player1 = new PlayerImpl('X');
        this.player2 = new PlayerImpl('O');
        this.currentPlayer = player1;
        this.board = new Board();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);


            while (true) {
                System.out.println("Current Player: " + currentPlayer.getMarker());
                int x, y;

                while(true){
                    try{
                        System.out.print("line (0–2): ");
                        x = scanner.nextInt();
                        System.out.print("column (0–2): ");
                        y = scanner.nextInt();
                        if(x >= 0 && x <= 2 && y >= 0 && y <= 2){
                            break;
                        }
                        if(board.isCellEmpty(x, y)){
                            System.out.println("Please choose an empty square.");
                        }
                    }catch (InputMismatchException e) {
                        System.out.println("Only numbers bro...");
                        scanner.nextLine();
                    }
                }

                board.place(x, y, currentPlayer.getMarker());

                switchCurrentPlayer();
            }
    }

    private void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private boolean hasWinner() {
        return true;
    }
}
