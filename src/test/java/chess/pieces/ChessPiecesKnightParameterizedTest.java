package chess.pieces;

import chess.parametizedUtil.ChessParameterized;
import org.junit.Test;
import org.junit.runner.*;
import org.junit.runners.Parameterized;

import java.util.*;

import static chess.parametizedUtil.ChessParameterized.bChessParameterized;
import static chess.pieces.ChessPieces.KNIGHT;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ChessPiecesKnightParameterizedTest {
    @Parameterized.Parameters
    public static Collection<ChessParameterized> data() {
        return Arrays.asList(
                bChessParameterized(
                        new Position(0, 1),
                        new Position(2, 2),
                        new int[][]{{26, 0}, {0}, {0, 0, 25}}, true,
                        "Knight goes to front/Left pos"),
                bChessParameterized(
                        new Position(0, 3),
                        new Position(2, 2),
                        new int[][]{{26, 0, 0, 0, 0}, {0}, {0, 0, 25}}, true,
                        "Knight goes to front/Right pos"),
                bChessParameterized(
                        new Position(4, 1),
                        new Position(2, 2),
                        new int[][]{{26, 0}, {0}, {0, 0, 25}}, true,
                        "Knight goes to backwards/Left pos"),
                bChessParameterized(
                        new Position(4, 3),
                        new Position(2, 2),
                        new int[][]{{26, 0}, {0}, {0, 0, 25}}, true,
                        "Knight goes to backwards/Right pos"),
                bChessParameterized(
                        new Position(0, 1),
                        new Position(1, 3),
                        new int[][]{{26, 0, 0, 0, 0}, {0}, {0, 0, 25}}, true,
                        "Knight goes to side/Left pos"),
                bChessParameterized(
                        new Position(0, 5),
                        new Position(1, 3),
                        new int[][]{{26, 0, 0, 0, 0}, {0}, {0, 0, 25}}, true,
                        "Knight goes to side/Left pos"),
                bChessParameterized(
                        new Position(0, 4),
                        new Position(1, 3),
                        new int[][]{{26, 0, 0, 0, 0}, {0}, {0, 0, 25}}, false,
                        "Knight not goes to side/Left pos"));
    }

    private ChessParameterized paramTest;

    public ChessPiecesKnightParameterizedTest(ChessParameterized paramTest) {
        this.paramTest = paramTest;
    }

    @Test
    public void shouldValidateIfPawnCanMoveOnTheBoard() {
        System.out.println(paramTest.testCase);
        assertEquals(paramTest.assertion,
                KNIGHT.getValidatePosition()
                        .test(paramTest.goTo, paramTest.current, paramTest.board));
    }
}
