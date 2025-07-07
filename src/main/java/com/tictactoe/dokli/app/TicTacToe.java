package com.tictactoe.dokli.app;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Scanner;

@AllArgsConstructor
@Getter
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
        boolean repeat;
        do {
            repeat = false;
            board.clear();

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
                                if(!board.isCellEmpty(x, y)){
                                    System.out.println("Please choose an empty square.");
                                } else{
                                    break;
                                }
                            }

                        }catch (InputMismatchException e) {
                            System.out.println("Only numbers bro...");
                            scanner.nextLine();
                        }
                    }

                    board.place(x, y, currentPlayer.getMarker());

                    if (hasWinner()) {
                        board.print();
                        System.out.println("Player " + currentPlayer.getMarker() + " won!");
                        break;
                    }

                    if (board.isFull()) {
                        board.print();
                        System.out.println("Draw!");
                        break;
                    }

                    board.print();
                    switchCurrentPlayer();

                }
            System.out.print("Ready for another round? (y/n): ");
            String answer = scanner.next();
            if (answer.equalsIgnoreCase("y")) {
                repeat = true;
            }
        }while (repeat);
    }

    private void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private boolean hasWinner() {
        char marker = currentPlayer.getMarker();
        char[][] cells = board.getCells();

        for (int i = 0; i < 3; i++) {
            if (cells[i][0] == marker && cells[i][1] == marker && cells[i][2] == marker) {
                return true;
            }
            if (cells[0][i] == marker && cells[1][i] == marker && cells[2][i] == marker){
                return true;
            }
        }
        //diagonal
        return (cells[0][0] == marker && cells[1][1] == marker && cells[2][2] == marker) ||
                (cells[0][2] == marker && cells[1][1] == marker && cells[2][0] == marker);
    }
}
