package chess;

import org.junit.Test;

import static chess.ChessPieces.PAWN;
import static org.junit.Assert.*;

public class ChessPiecesPawnTest {

    @Test
    public void shouldBeAbleToGoToNextPosGivenItEmptyAhead() {
        int[][] board = new int[8][8];
        board[0][0] = 26;
        board[1][0] = 0;
        Position goTo = new Position(1, 0);
        Position current = new Position(0, 0);
        boolean response = PAWN.getValidatePosition().test(goTo, current, board);
        assertTrue(response);
    }

    @Test
    public void shouldNotBeAbleToGoToNextPosGivenItsInDiagonalAndItsEmpty() {
        int[][] board = new int[8][8];
        board[0][0] = 26;
        board[1][1] = 0;
        Position goTo = new Position(1, 1);
        Position current = new Position(0, 0);
        boolean response = PAWN.getValidatePosition().test(goTo, current, board);
        assertFalse(response);
    }

    @Test
    public void shouldNotBeAbleToGoToNextPosGivenItsToGoBackwards() {
        int[][] board = new int[8][8];
        board[1][0] = 26;
        board[1][1] = 0;
        Position goTo = new Position(0, 0);
        Position current = new Position(1, 0);
        boolean response = PAWN.getValidatePosition().test(goTo, current, board);
        assertFalse(response);
    }

    @Test
    public void shouldNotBeAbleToGoToNextPosGivenItsOutOfTheBoard() {
        int[][] board = new int[8][8];
        board[1][0] = 26;
        board[1][1] = 0;
        Position goTo = new Position(8, 8);
        Position current = new Position(1, 0);
        boolean response = PAWN.getValidatePosition().test(goTo, current, board);
        assertFalse(response);
    }

    @Test
    public void shouldNotBeAbleToGoToNextPosGivenItsOutOfTheBoardWhenPosIsNegative() {
        int[][] board = new int[8][8];
        board[1][0] = 26;
        board[1][1] = 0;
        Position goTo = new Position(8, 8);
        Position current = new Position(-1, -1);
        boolean response = PAWN.getValidatePosition().test(goTo, current, board);
        assertFalse(response);
    }

    @Test
    public void shouldBeAbleToGoToNextPosGivenItsDiagonalAndItsNotEmpty() {
        int[][] board = new int[8][8];
        board[0][0] = 26;
        board[1][1] = 22;
        Position goTo = new Position(1, 1);
        Position current = new Position(0, 0);
        boolean response = PAWN.getValidatePosition().test(goTo, current, board);
        assertTrue(response);
    }
}
