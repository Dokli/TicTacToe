package com.tictactoe.dokli.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void boardShouldBeEmptyAfterInitialization() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                assertTrue(board.isCellEmpty(x, y), "Cell (" + x + "," + y + ") should be empty");
            }
        }
    }

    @Test
    void placeShouldSetMarkerCorrectly() {
        board.place(0, 0, 'X');
        assertFalse(board.isCellEmpty(0, 0));
    }

    @Test
    void clearShouldEmptyBoard() {
        board.place(1, 1, 'O');
        board.clear();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                assertTrue(board.isCellEmpty(x, y), "Cell (" + x + "," + y + ") should be empty after clear");
            }
        }
    }

    @Test
    void isFullShouldReturnTrueWhenBoardIsFull() {
        char marker = 'X';
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                board.place(x, y, marker);
            }
        }
        assertTrue(board.isFull());
    }

    @Test
    void isFullShouldReturnFalseWhenBoardHasEmptyCells() {
        board.place(0, 0, 'X');
        assertFalse(board.isFull());
    }

    @Test
    void clearShouldEmptyAllCellsAfterSettingMarkers() {
        board.place(0, 0, 'X');
        board.place(1, 1, 'O');
        board.place(2, 2, 'X');

        board.clear();

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                assertTrue(board.isCellEmpty(x, y), "Cell (" + x + "," + y + ") should be empty after clear()");
            }
        }
    }

    @Test
    void clearShouldLeaveAnAlreadyEmptyBoardUnchanged() {
        board.clear(); // redundant, Board is already empty

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                assertTrue(board.isCellEmpty(x, y), "Cell (" + x + "," + y + ") should still be empty");
            }
        }
    }
}
