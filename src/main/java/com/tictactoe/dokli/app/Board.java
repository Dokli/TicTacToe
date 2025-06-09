package com.tictactoe.dokli.app;

import lombok.Getter;

@Getter
public class Board {
    private final char[][] cells;

    public Board() {
        cells = new char[3][3];
        this.clear();
    }

    public boolean isCellEmpty(int x, int y) {
        return cells[x][y] == ' ';
    }

    public void place(int x, int y, char marker) {
        cells[x][y] = marker;
    }

    public boolean isFull() {
        return true;
    }

    public void clear() {

    }

    public void print() {

    }

}
