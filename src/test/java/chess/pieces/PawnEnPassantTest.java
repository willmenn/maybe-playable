package chess.pieces;

import org.junit.Test;

import static chess.pieces.ChessPieces.PAWN;
import static chess.pieces.ChessTypeOfPieces.BLACK;
import static chess.pieces.ChessTypeOfPieces.WHITE;
import static org.junit.Assert.*;

public class PawnEnPassantTest {

    @Test
    public void shouldBeAbleToMoveGivenIsEnPassantForBlackPawn() {
        int[][] board = new int[][]{{0, 0, 0}, {0, blackPawn(), 0}, {0, 0, 0}, {0, 0, 0}};

        PawnEnPassant pawnEnPassant = new PawnEnPassant();
        boolean enPassant = pawnEnPassant.isEnPassant(new Position(1, 1), new Position(3, 1),
                board, BLACK);
        assertTrue(enPassant);
    }

    @Test
    public void shouldNotBeAbleToMoveGivenIsNotFirstMoveForBlackPawn() {
        int[][] board = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, blackPawn(), 0}, {0, 0, 0}, {0, 0, 0}};

        PawnEnPassant pawnEnPassant = new PawnEnPassant();
        boolean enPassant = pawnEnPassant.isEnPassant(new Position(3, 1), new Position(5, 1),
                board, BLACK);
        assertFalse(enPassant);
    }

    @Test
    public void shouldNotBeAbleToMoveGivenIsOnlyOneMoveForBlackPawn() {
        int[][] board = new int[][]{{0, 0, 0}, {0, blackPawn(), 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        PawnEnPassant pawnEnPassant = new PawnEnPassant();
        boolean enPassant = pawnEnPassant.isEnPassant(new Position(2, 1), new Position(3, 1),
                board, BLACK);
        assertFalse(enPassant);
    }

    @Test
    public void shouldBeAbleToMoveGivenIsEnPassantForWhitePawn() {
        int[][] board = new int[][]{{0, 0, 0}, {0, 0, 0},
                {0, 0, 0}, {0, 0, 0},
                {0, 0, 0}, {0, 0, 0},
                {0, whitePawn(), 0}, {0, 0, 0}};

        PawnEnPassant pawnEnPassant = new PawnEnPassant();
        boolean enPassant = pawnEnPassant.isEnPassant(new Position(6, 1), new Position(4, 1),
                board, WHITE);
        assertTrue(enPassant);
    }

    @Test
    public void shouldNotBeAbleToMoveGivenIsNotFirstMoveForWhitePawn() {
        int[][] board = new int[][]{{0, 0, 0}, {0, 0, 0},
                {0, 0, 0}, {0, 0, 0},
                {0, 0, 0}, {0, whitePawn(), 0},
                {0, 0, 0}, {0, 0, 0}};

        PawnEnPassant pawnEnPassant = new PawnEnPassant();
        boolean enPassant = pawnEnPassant.isEnPassant(new Position(5, 1), new Position(3, 1),
                board, WHITE);
        assertFalse(enPassant);
    }

    @Test
    public void shouldNotBeAbleToMoveGivenIsOnlyOneMoveForWhitePawn() {
        int[][] board = new int[][]{{0, 0, 0}, {0, 0, 0},
                {0, 0, 0}, {0, 0, 0},
                {0, 0, 0}, {0, 0, 0},
                {0, whitePawn(), 0}, {0, 0, 0}};

        PawnEnPassant pawnEnPassant = new PawnEnPassant();
        boolean enPassant = pawnEnPassant.isEnPassant(new Position(6, 1), new Position(5, 1),
                board, WHITE);
        assertFalse(enPassant);
    }

    private int blackPawn() {
        return BLACK.getNumberRepresentation() + PAWN.getNumberRepresentation();
    }

    private int whitePawn() {
        return WHITE.getNumberRepresentation() + PAWN.getNumberRepresentation();
    }
}