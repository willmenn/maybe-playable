package chess.pieces;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static chess.pieces.ChessPieces.BISHOP;
import static chess.pieces.ChessPieces.PAWN;
import static chess.pieces.ChessTypeOfPieces.BLACK;
import static chess.pieces.ChessTypeOfPieces.WHITE;
import static org.junit.Assert.*;

public class PawnEnPassantTest {

    private PawnEnPassant pawnEnPassant;

    @Before
    public void setUp() {
        pawnEnPassant = new PawnEnPassant();
    }

    @Test
    public void shouldBeAbleToMoveGivenIsEnPassantForBlackPawn() {
        int[][] board = new int[][]{{0, 0, 0}, {0, blackPawn(), 0}, {0, 0, 0}, {0, 0, 0}};

        boolean enPassant = pawnEnPassant.isEnPassant(new Position(1, 1), new Position(3, 1),
                board, BLACK);
        assertTrue(enPassant);
    }

    @Test
    public void shouldNotBeAbleToMoveGivenIsNotFirstMoveForBlackPawn() {
        int[][] board = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, blackPawn(), 0}, {0, 0, 0}, {0, 0, 0}};

        boolean enPassant = pawnEnPassant.isEnPassant(new Position(3, 1), new Position(5, 1),
                board, BLACK);
        assertFalse(enPassant);
    }

    @Test
    public void shouldNotBeAbleToMoveGivenIsOnlyOneMoveForBlackPawn() {
        int[][] board = new int[][]{{0, 0, 0}, {0, blackPawn(), 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

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

        boolean enPassant = pawnEnPassant.isEnPassant(new Position(6, 1), new Position(5, 1),
                board, WHITE);
        assertFalse(enPassant);
    }

    @Test
    public void shouldNotBeEnPassantGivenIsNotADiagonalMove() {
        int[][] board = new int[][]{{0, 0, 0}, {0, blackPawn(), 0}, {0, 0, 0}, {0, 0, 0}};
        boolean enPassant = pawnEnPassant.isPawnAbleToCaptureEnPassant(new Position(1, 1),
                new Position(2, 1),
                board, BLACK, new ArrayList<>());
        assertFalse(enPassant);
    }

    @Test
    public void shouldNotBeEnPassantGivenIsNotAPawnToBeCaptured() {
        int[][] board = new int[][]{{0, 0, 0}, {0, blackPawn(), whiteBishop()}, {0, 0, 0}, {0, 0, 0}};
        boolean enPassant = pawnEnPassant.isPawnAbleToCaptureEnPassant(new Position(1, 1),
                new Position(2, 2),
                board, BLACK, new ArrayList<>());
        assertFalse(enPassant);
    }

    @Test
    public void shouldNotBeEnPassantGivenIsNotAPawnThatWillCapture() {
        int[][] board = new int[][]{{0, 0, 0}, {0, blackBishop(), whitePawn()}, {0, 0, 0}, {0, 0, 0}};
        boolean enPassant = pawnEnPassant.isPawnAbleToCaptureEnPassant(new Position(1, 1),
                new Position(2, 2),
                board, BLACK, new ArrayList<>());
        assertFalse(enPassant);
    }

    @Test
    public void shouldNotBeEnPassantGivenIs2PawnsFromTheSameColor() {
        int[][] board = new int[][]{{0, 0, 0}, {0, blackPawn(), blackPawn()}, {0, 0, 0}, {0, 0, 0}};
        boolean enPassant = pawnEnPassant.isPawnAbleToCaptureEnPassant(new Position(1, 1),
                new Position(2, 2),
                board, BLACK, new ArrayList<>());
        assertFalse(enPassant);
    }

    @Test
    public void shouldNotBeEnPassantGivenPawnIsNotEnPassant() {
        int[][] board = new int[][]{{0, 0, 0}, {0, blackPawn(), whitePawn()}, {0, 0, 0}, {0, 0, 0}};

        ArrayList<Position> pawsEnPassant = new ArrayList<>();
        pawsEnPassant.add(new Position(2, 2));
        boolean enPassant = pawnEnPassant.isPawnAbleToCaptureEnPassant(new Position(1, 1),
                new Position(2, 2),
                board, BLACK, pawsEnPassant);
        assertFalse(enPassant);
    }

    @Test
    public void shouldBeEnPassant() {
        int[][] board = new int[][]{{0, 0, 0}, {0, blackPawn(), whitePawn()}, {0, 0, 0}, {0, 0, 0}};

        ArrayList<Position> pawsEnPassant = new ArrayList<>();
        pawsEnPassant.add(new Position(1, 2));
        boolean enPassant = pawnEnPassant.isPawnAbleToCaptureEnPassant(new Position(1, 1),
                new Position(2, 2),
                board, BLACK, pawsEnPassant);
        assertTrue(enPassant);
    }

    private int blackPawn() {
        return BLACK.getNumberRepresentation() + PAWN.getNumberRepresentation();
    }

    private int blackBishop() {
        return BLACK.getNumberRepresentation() + BISHOP.getNumberRepresentation();
    }

    private int whitePawn() {
        return WHITE.getNumberRepresentation() + PAWN.getNumberRepresentation();
    }

    private int whiteBishop() {
        return WHITE.getNumberRepresentation() + BISHOP.getNumberRepresentation();
    }
}