package chess.pieces;

import org.junit.Test;

import static chess.pieces.ChessPieces.PAWN;
import static org.junit.Assert.assertFalse;

public class ChessPiecesRookTest {

    @Test
    public void shouldNotBeAbleToGoToNextPosGivenItsDiagonal() {
        int[][] board = new int[8][8];
        board[0][0] = 26;
        board[1][0] = 0;
        Position goTo = new Position(1, 1);
        Position current = new Position(0, 0);
        boolean response = PAWN.getValidatePosition().test(goTo, current, board);
        assertFalse(response);
    }

    @Test
    public void shouldNotBeAbleToGoToNextPosGivenItsOutsideOfTheBoard() {
        int[][] board = new int[8][8];
        board[0][0] = 26;
        board[1][0] = 0;
        Position goTo = new Position(-1, -1);
        Position current = new Position(0, 0);
        boolean response = PAWN.getValidatePosition().test(goTo, current, board);
        assertFalse(response);
        Position goTo2 = new Position(8, 8);
        Position current2 = new Position(0, 0);
        boolean response2 = PAWN.getValidatePosition().test(goTo2, current2, board);
        assertFalse(response2);
    }
}
