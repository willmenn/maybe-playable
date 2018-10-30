package chess.pieces;

import chess.parametizedUtil.ChessParameterized;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static chess.parametizedUtil.ChessParameterized.bChessParameterized;
import static chess.pieces.ChessPieces.KING;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ChessPiecesKingParameterizedTest {

    @Parameterized.Parameters
    public static Collection<ChessParameterized> data() {
        return Arrays.asList(
                bChessParameterized(
                        new Position(1, 0),
                        new Position(0, 0),
                        new int[][]{{21, 0}, {0}}, true,
                        "King goes to front pos"),
                bChessParameterized(
                        new Position(1, 1),
                        new Position(0, 0),
                        new int[][]{{21, 0}, {0}}, true,
                        "King goes to front/right pos"),
                bChessParameterized(
                        new Position(1, 0),
                        new Position(0, 1),
                        new int[][]{{0, 21}, {0}}, true,
                        "King goes to front/left pos"),
                bChessParameterized(
                        new Position(0, 1),
                        new Position(0, 0),
                        new int[][]{{0, 21}, {0}}, true,
                        "King goes to side/right pos"),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(0, 1),
                        new int[][]{{0, 21}, {0}}, true,
                        "King goes to side/left pos"),
                bChessParameterized(
                        new Position(0, 1),
                        new Position(1, 1),
                        new int[][]{{0, 0}, {0, 21}}, true,
                        "King goes to backwards pos"),
                bChessParameterized(
                        new Position(0, 2),
                        new Position(1, 1),
                        new int[][]{{0, 0}, {0, 21}}, true,
                        "King goes to backwards/right pos"),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(1, 1),
                        new int[][]{{0, 0}, {0, 21}}, true,
                        "King goes to backwards/right pos"),
                bChessParameterized(
                        new Position(0, 3),
                        new Position(1, 1),
                        new int[][]{{0, 0}, {0, 21}}, false,
                        "King goes to backwards/right pos"));
    }

    private ChessParameterized paramTest;

    public ChessPiecesKingParameterizedTest(ChessParameterized paramTest) {
        this.paramTest = paramTest;
    }

    @Test
    public void shouldValidateIfPawnCanMoveOnTheBoard() {
        System.out.println(paramTest.testCase);
        assertEquals(paramTest.assertion,
                KING.getValidatePosition()
                        .test(paramTest.goTo, paramTest.current, paramTest.board));
    }


}
