package chess.pieces;

import chess.parametizedUtil.ChessParameterized;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static chess.parametizedUtil.ChessParameterized.bChessParameterized;
import static chess.pieces.ChessPieces.PAWN;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ChessPiecesPawnParameterizedTest {

    @Parameters
    public static Collection<ChessParameterized> data() {
        return Arrays.asList(
                bChessParameterized(
                        new Position(1, 0),
                        new Position(0, 0),
                        new int[][]{{26}, {0}}, true,
                        "Pawn goes to front pos"),
                bChessParameterized(
                        new Position(2, 0),
                        new Position(0, 0),
                        new int[][]{{26}, {0}}, false,
                        "Pawn goes to front pos but its 2 rows"),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(0, 0),
                        new int[][]{{26}, {0}}, false,
                        "GoTo pos is the same as current"),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(1, 0),
                        new int[][]{{0}, {26}}, false,
                        "Going backwards"),
                bChessParameterized(
                        new Position(1, 1),
                        new Position(0, 0),
                        new int[][]{{26}, {0, 0}}, false,
                        "Diagonal(front/right) Pos is empty"),
                bChessParameterized(
                        new Position(1, 0),
                        new Position(0, 1),
                        new int[][]{{0, 26}, {0, 0}}, false,
                        "Diagonal(front/left) Pos is empty"),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(1, 1),
                        new int[][]{{0, 0}, {0, 26}}, false,
                        "Diagonal Backwards(front/right) Pos is empty"),
                bChessParameterized(
                        new Position(0, 1),
                        new Position(1, 0),
                        new int[][]{{0, 0}, {26, 0}}, false,
                        "Diagonal Backwards(front/left) Pos is empty"),
                bChessParameterized(
                        new Position(1, 1),
                        new Position(0, 0),
                        new int[][]{{26}, {0, 24}}, true,
                        "Diagonal(front/right) Pos is NOT empty"),
                bChessParameterized(
                        new Position(1, 0),
                        new Position(0, 1),
                        new int[][]{{0, 26}, {24, 0}}, true,
                        "Diagonal(front/left) Pos is NOT empty"),
                bChessParameterized(
                        new Position(8, 0),
                        new Position(1, 0),
                        new int[][]{{0}, {26}}, false,
                        "Out Of the board"),
                bChessParameterized(
                        new Position(0, 8),
                        new Position(1, 0),
                        new int[][]{{0}, {26}}, false,
                        "Out Of the board"),
                bChessParameterized(
                        new Position(-1, 0),
                        new Position(1, 0),
                        new int[][]{{0}, {26}}, false,
                        "Out of the board"),
                bChessParameterized(
                        new Position(0, -1),
                        new Position(1, 0),
                        new int[][]{{0}, {26}}, false,
                        "Out of the board"),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(1, 8),
                        new int[][]{{0}, {26}}, false,
                        "Current is Out of the board"),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(8, 0),
                        new int[][]{{0}, {26}}, false,
                        "Current is Out of the board"),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(-1, 0),
                        new int[][]{{0}, {26}}, false,
                        "Current is Out of the board"),
                bChessParameterized(
                        new Position(0, 0),
                        new Position(0, -1),
                        new int[][]{{0}, {26}}, false,
                        "Current is Out of the board"),
                bChessParameterized(
                        new Position(3, 0),
                        new Position(1, 0),
                        new int[][]{{0}, {26}, {0}, {0}}, true,
                        "Move 2 positions forward given its the first time"),
                bChessParameterized(
                        new Position(4, 0),
                        new Position(6, 0),
                        new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {16}, {0}}, true,
                        "Move 2 positions forward given its the first time"));
    }

    private ChessParameterized paramTest;

    public ChessPiecesPawnParameterizedTest(ChessParameterized paramTest) {
        this.paramTest = paramTest;
    }

    @Test
    public void shouldValidateIfPawnCanMoveOnTheBoard() {
        System.out.println(paramTest.testCase);
        assertEquals(PAWN.getValidatePosition()
                .test(paramTest.goTo, paramTest.current, paramTest.board), paramTest.assertion);
    }
}
