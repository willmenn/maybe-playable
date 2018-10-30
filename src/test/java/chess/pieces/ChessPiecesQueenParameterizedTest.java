package chess.pieces;

import chess.parametizedUtil.ChessParameterized;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static chess.parametizedUtil.ChessParameterized.bChessParameterized;
import static chess.pieces.ChessPieces.QUEEN;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ChessPiecesQueenParameterizedTest {
    @Parameterized.Parameters
    public static Collection<ChessParameterized> data() {
        return Arrays.asList(
                bChessParameterized(
                        new Position(1, 0),
                        new Position(0, 0),
                        new int[][]{{22}, {0}}, true,
                        "Queen goes to front pos"),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(1, 0),
                        new int[][]{{0}, {22}}, true,
                        "Queen goes to backwards pos"),
                bChessParameterized(
                        new Position(1, 1),
                        new Position(1, 0),
                        new int[][]{{0}, {22}}, true,
                        "Queen goes to right pos"),
                bChessParameterized(
                        new Position(1, 0),
                        new Position(1, 1),
                        new int[][]{{0}, {0, 22}}, true,
                        "Queen goes to left pos"),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(1, 1),
                        new int[][]{{0}, {0, 22}}, true,
                        "Queen goes to diagonal backwards/left pos"),
                bChessParameterized(
                        new Position(0, 2),
                        new Position(1, 1),
                        new int[][]{{0, 0, 0}, {0, 22}}, true,
                        "Queen goes to diagonal backwards/right pos"),
                bChessParameterized(
                        new Position(1, 0),
                        new Position(0, 1),
                        new int[][]{{0, 22, 0}, {0, 0, 0}}, true,
                        "Queen goes to diagonal front/right pos"),
                bChessParameterized(
                        new Position(1, 1),
                        new Position(0, 0),
                        new int[][]{{0, 22, 0}, {0, 0, 0}}, true,
                        "Queen goes to diagonal front/left pos"),
                bChessParameterized(
                        new Position(5, 5),
                        new Position(0, 0),
                        new int[][]{{22}, {0, 0}, {0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 24}}, true,
                        "Queen goes to diagonal front/left pos"));
    }

    private ChessParameterized paramTest;

    public ChessPiecesQueenParameterizedTest(ChessParameterized paramTest) {
        this.paramTest = paramTest;
    }

    @Test
    public void shouldValidateIfPawnCanMoveOnTheBoard() {
        System.out.println(paramTest.testCase);
        assertEquals(paramTest.assertion,
                QUEEN.getValidatePosition()
                        .test(paramTest.goTo, paramTest.current, paramTest.board));
    }
}
