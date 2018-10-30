package chess.pieces;

import chess.parametizedUtil.ChessParameterized;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static chess.parametizedUtil.ChessParameterized.bChessParameterized;
import static chess.pieces.ChessPieces.BISHOP;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ChessPiecesBishopParameterizedTest {

    @Parameterized.Parameters
    public static Collection<ChessParameterized> data() {
        return Arrays.asList(
                bChessParameterized(
                        new Position(2, 2),
                        new Position(0, 0),
                        new int[][]{{24, 0}, {0, 0, 0}, {0, 0, 0}}, true,
                        "Bishop goes to front/Right pos"),
                bChessParameterized(
                        new Position(2, 0),
                        new Position(0, 2),
                        new int[][]{{24, 0}, {0, 0, 0}, {0, 0, 0}}, true,
                        "Bishop goes to front/Left pos"),
                bChessParameterized(
                        new Position(0, 2),
                        new Position(2, 0),
                        new int[][]{{0, 0, 0}, {0, 0, 0}, {24, 0, 0}}, true,
                        "Bishop goes to Backwards/Right pos"),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(2, 2),
                        new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 24}}, true,
                        "Bishop goes to Backwards/Left pos"),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(2, 2),
                        new int[][]{{21, 0, 0}, {0, 0, 0}, {0, 0, 24}}, true,
                        "Bishop goes to Backwards/Left pos, when there is a piece in the final place"),
                bChessParameterized(
                        new Position(2, 0),
                        new Position(0, 2),
                        new int[][]{{24, 0}, {0, 23, 0}, {0, 0, 0}}, false,
                        "Bishop not goes to front/Left pos, because there is a piece in the middle"),
                bChessParameterized(
                        new Position(2, 0),
                        new Position(0, 3),
                        new int[][]{{24, 0}, {0, 0, 0}, {0, 0, 0}}, false,
                        "Bishop not goes to front/Left pos, because is not diagonal"));
    }

    private ChessParameterized paramTest;

    public ChessPiecesBishopParameterizedTest(ChessParameterized paramTest) {
        this.paramTest = paramTest;
    }

    @Test
    public void shouldValidateIfPawnCanMoveOnTheBoard() {
        System.out.println(paramTest.testCase);
        assertEquals(paramTest.assertion,
                BISHOP.getValidatePosition()
                        .test(paramTest.goTo, paramTest.current, paramTest.board));
    }

}
