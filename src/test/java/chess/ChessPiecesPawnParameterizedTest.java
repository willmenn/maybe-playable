package chess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static chess.ChessPieces.PAWN;
import static chess.ChessPiecesPawnParameterizedTest.ChessParameterized.bChessParameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ChessPiecesPawnParameterizedTest {

    private ChessParameterized paramTest;

    @Parameterized.Parameters
    public static Collection<ChessParameterized> data() {
        return Arrays.asList(
                bChessParameterized(
                        new Position(1, 0), // Pawn goes to front pos
                        new Position(0, 0),
                        new int[][]{{26}, {0}}, true),
                bChessParameterized(
                        new Position(2, 0), // Pawn goes to front pos but its 2 rows
                        new Position(0, 0),
                        new int[][]{{26}, {0}}, false),
                bChessParameterized(
                        new Position(0, 0), // GoTo pos is the same as current
                        new Position(0, 0),
                        new int[][]{{26}, {0}}, false),
                bChessParameterized(
                        new Position(0, 0), // Going backwards
                        new Position(1, 0),
                        new int[][]{{0}, {26}}, false),
                bChessParameterized(
                        new Position(1, 1), // Diagonal(front/right) Pos is empty
                        new Position(0, 0),
                        new int[][]{{26}, {0, 0}}, false),
                bChessParameterized(
                        new Position(1, 0), // Diagonal(front/left) Pos is empty
                        new Position(0, 1),
                        new int[][]{{0, 26}, {0, 0}}, false),
                bChessParameterized(
                        new Position(0, 0), // Diagonal Backwards(front/right) Pos is empty
                        new Position(1, 1),
                        new int[][]{{0, 0}, {0, 26}}, false),
                bChessParameterized(
                        new Position(0, 1), // Diagonal Backwards(front/left) Pos is empty
                        new Position(1, 0),
                        new int[][]{{0, 0}, {1, 0}}, false),
                bChessParameterized(
                        new Position(1, 1), // Diagonal(front/right) Pos is NOT empty
                        new Position(0, 0),
                        new int[][]{{26}, {0, 24}}, true),
                bChessParameterized(
                        new Position(1, 0), // Diagonal(front/left) Pos is NOT empty
                        new Position(0, 1),
                        new int[][]{{0, 26}, {24, 0}}, true),
                bChessParameterized(
                        new Position(8, 0), // Out Of the board
                        new Position(1, 0),
                        new int[][]{{0}, {26}}, false),
                bChessParameterized(
                        new Position(0, 8), // Out Of the board
                        new Position(1, 0),
                        new int[][]{{0}, {26}}, false),
                bChessParameterized(
                        new Position(-1, 0), // Out of the board
                        new Position(1, 0),
                        new int[][]{{0}, {26}}, false),
                bChessParameterized(
                        new Position(0, -1), // Out of the board
                        new Position(1, 0),
                        new int[][]{{0}, {26}}, false),
                bChessParameterized(
                        new Position(0, 0), //Current is Out of the board
                        new Position(1, 8),
                        new int[][]{{0}, {26}}, false),
                bChessParameterized(
                        new Position(0, 0), // Current is Out of the board
                        new Position(8, 0),
                        new int[][]{{0}, {26}}, false),
                bChessParameterized(
                        new Position(0, 0), // Current is Out of the board
                        new Position(-1, 0),
                        new int[][]{{0}, {26}}, false),
                bChessParameterized(
                        new Position(0, 0), // Current is Out of the board
                        new Position(0, -1),
                        new int[][]{{0}, {26}}, false));
    }

    public ChessPiecesPawnParameterizedTest(ChessParameterized paramTest) {
        this.paramTest = paramTest;
    }

    @Test
    public void shouldValidateIfPawnCanMoveOnTheBoard() {
        assertEquals(PAWN.getValidatePosition()
                .test(paramTest.goTo, paramTest.current, paramTest.board), paramTest.assertion);
    }

    public static class ChessParameterized {
        private Position goTo;

        private Position current;

        private int[][] board;

        private boolean assertion;

        public static ChessParameterized bChessParameterized(Position goTo, Position current, int[][] board, boolean assertion) {
            return new ChessParameterized(goTo, current, board, assertion);
        }

        public ChessParameterized(Position goTo, Position current, int[][] board, boolean assertion) {
            this.goTo = goTo;
            this.current = current;
            this.board = board;
            this.assertion = assertion;
        }
    }
}
