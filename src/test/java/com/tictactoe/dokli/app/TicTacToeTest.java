package com.tictactoe.dokli.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {

    private TicTacToe ticTacToe;

    @BeforeEach
    void setUp() {
        ticTacToe = new TicTacToe();
    }

    @Test
    void testInitialCurrentPlayerIsPlayer1() throws Exception {
        Field currentPlayerField = TicTacToe.class.getDeclaredField("currentPlayer");
        currentPlayerField.setAccessible(true);

        Object currentPlayer = currentPlayerField.get(ticTacToe);
        assertNotNull(currentPlayer);

        Field markerField = currentPlayer.getClass().getDeclaredField("marker");
        markerField.setAccessible(true);
        char marker = (char) markerField.get(currentPlayer);

        assertEquals('X', marker, "Initial player should have marker 'X'");
    }

    @Test
    void testSwitchCurrentPlayer() throws Exception {
        Field currentPlayerField = TicTacToe.class.getDeclaredField("currentPlayer");
        currentPlayerField.setAccessible(true);

        // Initially player1
        Object initialPlayer = currentPlayerField.get(ticTacToe);

        // Call private switchCurrentPlayer()
        Method switchMethod = TicTacToe.class.getDeclaredMethod("switchCurrentPlayer");
        switchMethod.setAccessible(true);
        switchMethod.invoke(ticTacToe);

        Object switchedPlayer = currentPlayerField.get(ticTacToe);

        assertNotSame(initialPlayer, switchedPlayer, "Current player should switch after calling switchCurrentPlayer");
    }

    @Test
    void testHasWinnerRow() throws Exception {
        // Access board and currentPlayer
        Field boardField = TicTacToe.class.getDeclaredField("board");
        boardField.setAccessible(true);
        Object board = boardField.get(ticTacToe);

        Field currentPlayerField = TicTacToe.class.getDeclaredField("currentPlayer");
        currentPlayerField.setAccessible(true);
        Object currentPlayer = currentPlayerField.get(ticTacToe);

        // Place winning row for current player
        Method getMarkerMethod = currentPlayer.getClass().getDeclaredMethod("getMarker");
        char marker = (char) getMarkerMethod.invoke(currentPlayer);

        Method placeMethod = board.getClass().getDeclaredMethod("place", int.class, int.class, char.class);
        placeMethod.invoke(board, 0, 0, marker);
        placeMethod.invoke(board, 0, 1, marker);
        placeMethod.invoke(board, 0, 2, marker);

        // Call private hasWinner()
        Method hasWinnerMethod = TicTacToe.class.getDeclaredMethod("hasWinner");
        hasWinnerMethod.setAccessible(true);
        boolean result = (boolean) hasWinnerMethod.invoke(ticTacToe);

        assertTrue(result, "hasWinner should return true for a winning row");
    }

    @Test
    void testHasWinnerColumn() throws Exception {
        Field boardField = TicTacToe.class.getDeclaredField("board");
        boardField.setAccessible(true);
        Object board = boardField.get(ticTacToe);

        Field currentPlayerField = TicTacToe.class.getDeclaredField("currentPlayer");
        currentPlayerField.setAccessible(true);
        Object currentPlayer = currentPlayerField.get(ticTacToe);

        Method getMarkerMethod = currentPlayer.getClass().getDeclaredMethod("getMarker");
        char marker = (char) getMarkerMethod.invoke(currentPlayer);

        Method placeMethod = board.getClass().getDeclaredMethod("place", int.class, int.class, char.class);
        // Place markers in the first column
        placeMethod.invoke(board, 0, 0, marker);
        placeMethod.invoke(board, 1, 0, marker);
        placeMethod.invoke(board, 2, 0, marker);

        Method hasWinnerMethod = TicTacToe.class.getDeclaredMethod("hasWinner");
        hasWinnerMethod.setAccessible(true);
        boolean result = (boolean) hasWinnerMethod.invoke(ticTacToe);

        assertTrue(result, "hasWinner should return true for a winning column");
    }

    @Test
    void testHasWinnerDiagonal() throws Exception {
        Field boardField = TicTacToe.class.getDeclaredField("board");
        boardField.setAccessible(true);
        Object board = boardField.get(ticTacToe);

        Field currentPlayerField = TicTacToe.class.getDeclaredField("currentPlayer");
        currentPlayerField.setAccessible(true);
        Object currentPlayer = currentPlayerField.get(ticTacToe);

        Method getMarkerMethod = currentPlayer.getClass().getDeclaredMethod("getMarker");
        char marker = (char) getMarkerMethod.invoke(currentPlayer);

        Method placeMethod = board.getClass().getDeclaredMethod("place", int.class, int.class, char.class);
        placeMethod.invoke(board, 0, 0, marker);
        placeMethod.invoke(board, 1, 1, marker);
        placeMethod.invoke(board, 2, 2, marker);

        Method hasWinnerMethod = TicTacToe.class.getDeclaredMethod("hasWinner");
        hasWinnerMethod.setAccessible(true);
        boolean result = (boolean) hasWinnerMethod.invoke(ticTacToe);

        assertTrue(result, "hasWinner should return true for a winning diagonal");
    }

    @Test
    void testHasWinnerReturnsFalseWhenNoWin() throws Exception {
        Field boardField = TicTacToe.class.getDeclaredField("board");
        boardField.setAccessible(true);
        Object board = boardField.get(ticTacToe);

        Field currentPlayerField = TicTacToe.class.getDeclaredField("currentPlayer");
        currentPlayerField.setAccessible(true);
        Object currentPlayer = currentPlayerField.get(ticTacToe);

        Method getMarkerMethod = currentPlayer.getClass().getDeclaredMethod("getMarker");
        char marker = (char) getMarkerMethod.invoke(currentPlayer);

        Method placeMethod = board.getClass().getDeclaredMethod("place", int.class, int.class, char.class);
        placeMethod.invoke(board, 0, 0, marker);
        placeMethod.invoke(board, 1, 0, marker);

        Method hasWinnerMethod = TicTacToe.class.getDeclaredMethod("hasWinner");
        hasWinnerMethod.setAccessible(true);
        boolean result = (boolean) hasWinnerMethod.invoke(ticTacToe);

        assertFalse(result, "hasWinner should return false when there is no winner");
    }
}
