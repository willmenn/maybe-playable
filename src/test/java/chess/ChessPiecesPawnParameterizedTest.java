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
                        new Position(1, 0),
                        new Position(0, 0),
                        new int[][]{{26}, {0}},
                        true));
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
