package chess.pieces;

import chess.parametizedUtil.ChessParameterized;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static chess.parametizedUtil.ChessParameterized.bChessParameterized;
import static chess.pieces.ChessPieces.ROOK;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ChessPiecesRookParameterizedTest {

    @Parameterized.Parameters
    public static Collection<ChessParameterized> data() {
        return asList(
                bChessParameterized(
                        new Position(1, 0),
                        new Position(0, 0),
                        new int[][]{{23}, {0}}, true,
                        "Rook goes to front pos"),
                bChessParameterized(
                        new Position(1, -1),
                        new Position(0, 0),
                        new int[][]{{23}, {0}}, false,
                        "Rook outside the board"),
                bChessParameterized(
                        new Position(-1, 1),
                        new Position(0, 0),
                        new int[][]{{23}, {0}}, false,
                        "Rook outside the board"),
                bChessParameterized(
                        new Position(1, 1),
                        new Position(0, -1),
                        new int[][]{{23}, {0}}, false,
                        "Rook outside the board"),
                bChessParameterized(
                        new Position(1, 1),
                        new Position(-1, 0),
                        new int[][]{{23}, {0}}, false,
                        "Rook outside the board"),
                bChessParameterized(
                        new Position(1, 8),
                        new Position(0, 0),
                        new int[][]{{23}, {0}}, false,
                        "Rook outside the board"),
                bChessParameterized(
                        new Position(8, 0),
                        new Position(0, 0),
                        new int[][]{{23}, {0}}, false,
                        "Rook outside the board"),
                bChessParameterized(
                        new Position(1, 0),
                        new Position(0, 8),
                        new int[][]{{23}, {0}}, false,
                        "Rook outside the board"),
                bChessParameterized(
                        new Position(1, 0),
                        new Position(8, 0),
                        new int[][]{{23}, {0}}, false,
                        "Rook outside the board"),
                bChessParameterized(
                        new Position(1, 1),
                        new Position(0, 0),
                        new int[][]{{23}, {0}}, false,
                        "Rook Diagonal (Front/Right)"),
                bChessParameterized(
                        new Position(1, 0),
                        new Position(0, 1),
                        new int[][]{{23}, {0}}, false,
                        "Rook Diagonal (Front/Left)"),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(1, 1),
                        new int[][]{{23}, {0}}, false,
                        "Rook Diagonal (Back/Left)"),
                bChessParameterized(
                        new Position(0, 2),
                        new Position(1, 1),
                        new int[][]{{23}, {0}}, false,
                        "Rook outside (Back/Right)"),
                bChessParameterized(
                        new Position(2, 0),
                        new Position(0, 0),
                        new int[][]{{23}, {11}, {0}}, false,
                        "Rook cant go forward 2 pos, column is the same."),
                bChessParameterized(
                        new Position(2, 0),
                        new Position(0, 0),
                        new int[][]{{23}, {0}, {0}}, true,
                        "Rook can go forward 2 pos, column is the same."),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(2, 0),
                        new int[][]{{0}, {11}, {23}}, false,
                        "Rook cant go backward 2 pos, column is the same."),
                bChessParameterized(
                        new Position(0, 2),
                        new Position(0, 0),
                        new int[][]{{23, 0, 0}}, true,
                        "Rook can go right 2 pos, column is the same."),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(0, 2),
                        new int[][]{{0, 0, 23}}, true,
                        "Rook can go left 2 pos, column is the same."),
                bChessParameterized(
                        new Position(0, 2),
                        new Position(0, 0),
                        new int[][]{{23, 10, 0}}, false,
                        "Rook cant go right 2 pos, column is the same."),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(0, 2),
                        new int[][]{{0, 10, 23}}, false,
                        "Rook cant go left 2 pos, column is the same.")
        );
    }

    private ChessParameterized paramTest;

    public ChessPiecesRookParameterizedTest(ChessParameterized paramTest) {
        this.paramTest = paramTest;
    }

    @Test
    public void shouldValidateIfPawnCanMoveOnTheBoard() {
        System.out.println(paramTest.testCase);
        assertEquals(paramTest.assertion,
                ROOK.getValidatePosition()
                        .test(paramTest.goTo, paramTest.current, paramTest.board));
    }
}
