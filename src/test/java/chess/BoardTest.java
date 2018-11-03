package chess;

import chess.pieces.ChessPieces;
import org.junit.Test;

import static chess.BoardUnauthorizedOps.setBoardField;
import static chess.CheckMateStatus.CHECK;
import static chess.pieces.ChessPieces.*;
import static chess.pieces.ChessTypeOfPieces.BLACK;
import static chess.pieces.ChessTypeOfPieces.WHITE;
import static org.junit.Assert.assertEquals;

public class BoardTest {

    @Test
    public void shouldBeAbleToHaveOnly4RooksOnTheBoard() {
        Board boardGame = new Board();
        int[][] board = boardGame.getBoard();
        int sumOfRooks = getSumOfPieces(board, ROOK);
        assertEquals(4, sumOfRooks);
    }

    @Test
    public void shouldBeAbleToHave4Knights() {
        Board boardGame = new Board();
        int[][] board = boardGame.getBoard();
        int sumOfRooks = getSumOfPieces(board, KNIGHT);
        assertEquals(4, sumOfRooks);
    }

    @Test
    public void shouldBeAbleToHaveOnly4BishopOnTheBoard() {
        Board boardGame = new Board();
        int[][] board = boardGame.getBoard();
        int sumOfRooks = getSumOfPieces(board, BISHOP);
        assertEquals(4, sumOfRooks);
    }

    @Test
    public void shouldBeAbleToHave2Queens() {
        Board boardGame = new Board();
        int[][] board = boardGame.getBoard();
        int sumOfRooks = getSumOfPieces(board, QUEEN);
        assertEquals(2, sumOfRooks);
    }

    @Test
    public void shouldBeAbleToHave2Kings() {
        Board boardGame = new Board();
        int[][] board = boardGame.getBoard();
        int sumOfRooks = getSumOfPieces(board, KING);
        assertEquals(2, sumOfRooks);
    }

    @Test
    public void shouldBeAbleToHave16Kings() {
        Board boardGame = new Board();
        int[][] board = boardGame.getBoard();
        int sumOfRooks = getSumOfPieces(board, PAWN);
        assertEquals(16, sumOfRooks);
    }

    @Test
    public void theRookShouldBeOnTheFirstAndLastPosition() {
        Board boardGame = new Board();
        int[][] board = boardGame.getBoard();
        int firstPosWhite = board[0][0];
        int lastPosWhite = board[0][7];
        int firstPosBlack = board[7][0];
        int lastPosBlack = board[7][7];
        assertEquals(ROOK.name(), valueOf(firstPosWhite));
        assertEquals(ROOK.name(), valueOf(lastPosWhite));
        assertEquals(ROOK.name(), valueOf(firstPosBlack));
        assertEquals(ROOK.name(), valueOf(lastPosBlack));
    }

    @Test
    public void theKnightShouldBeOnTheSecondPos() {
        Board boardGame = new Board();
        int[][] board = boardGame.getBoard();
        int firstPosWhite = board[0][1];
        int lastPosWhite = board[0][6];
        int firstPosBlack = board[7][1];
        int lastPosBlack = board[7][6];
        assertEquals(KNIGHT.name(), valueOf(firstPosWhite));
        assertEquals(KNIGHT.name(), valueOf(lastPosWhite));
        assertEquals(KNIGHT.name(), valueOf(firstPosBlack));
        assertEquals(KNIGHT.name(), valueOf(lastPosBlack));
    }

    @Test
    public void theBishopShouldBeOnTheThirdPos() {
        Board boardGame = new Board();
        int[][] board = boardGame.getBoard();
        int firstPosWhite = board[0][2];
        int lastPosWhite = board[0][5];
        int firstPosBlack = board[7][2];
        int lastPosBlack = board[7][5];
        assertEquals(BISHOP.name(), valueOf(firstPosWhite));
        assertEquals(BISHOP.name(), valueOf(lastPosWhite));
        assertEquals(BISHOP.name(), valueOf(firstPosBlack));
        assertEquals(BISHOP.name(), valueOf(lastPosBlack));
    }

    @Test
    public void theQueenShouldBeOnTheFourthAndFifthPos() {
        Board boardGame = new Board();
        int[][] board = boardGame.getBoard();
        int queenPostWhite = board[0][3];
        int queenPostBlack = board[7][4];
        assertEquals(QUEEN.name(), valueOf(queenPostWhite));
        assertEquals(QUEEN.name(), valueOf(queenPostBlack));
    }

    @Test
    public void theKingShouldBeOnTheFourthAndFifthPos() {
        Board boardGame = new Board();
        int[][] board = boardGame.getBoard();
        int queenPostWhite = board[0][4];
        int queenPostBlack = board[7][3];
        assertEquals(KING.name(), valueOf(queenPostWhite));
        assertEquals(KING.name(), valueOf(queenPostBlack));
    }

    @Test
    public void thePawnShouldAlwaysBeginAheadOfOtherPieces() {
        Board boardGame = new Board();
        int[][] board = boardGame.getBoard();
        for (int i = 0; i < board[1].length; i++) {
            assertEquals(PAWN.name(), valueOf(board[1][i]));
            assertEquals(PAWN.name(), valueOf(board[6][i]));
        }
    }

    @Test
    public void shouldReturnCheck() throws NoSuchFieldException, IllegalAccessException {
        Board board = new Board();
        int[][] boardMatrix = new int[8][8];
        boardMatrix[0][2] = WHITE.getNumberRepresentation() + KING.getNumberRepresentation();
        boardMatrix[0][0] = BLACK.getNumberRepresentation() + ROOK.getNumberRepresentation();
        setBoardField(board, boardMatrix);
        CheckMateStatus checkMate = board.isCheckMate(WHITE);
        assertEquals(CHECK, checkMate);
    }

    private int getSumOfPieces(int[][] board, ChessPieces piece) {
        int sumOfRooks = 0;
        for (int[] aBoard : board) {
            for (int j = 0; j < board[0].length; j++) {
                if (ChessPieces.valueOf(aBoard[j]).equals(piece.name())) {
                    sumOfRooks++;
                }
            }
        }
        return sumOfRooks;
    }
}