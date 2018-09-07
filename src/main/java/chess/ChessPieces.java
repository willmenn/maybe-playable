package chess;

import chess.util.TriPredicate;

import static chess.ChessPiecesFunction.isDiagonalAndNotEmptyPos;
import static chess.ChessPiecesFunction.isDiagonalMove;
import static chess.ChessPiecesFunction.isNextPosAhead;
import static chess.ChessPiecesFunction.isOutSideTheBoard;
import static chess.ChessPiecesFunction.isPawnAbleToGo2Postions;

public enum ChessPieces {
    KING(1, (position, current, board) -> true),
    QUEEN(2, (position, current, board) -> true),
    ROOK(3, getRookValidatePos()),
    BISHOP(4, (position, current, board) -> true),
    KNIGHT(5, (position, current, board) -> true),
    PAWN(6, pawnValidatePos());

    private int numberRepresentation;

    private TriPredicate<Position, Position, int[][]> validatePosition;

    ChessPieces(int numberRepresentation, TriPredicate<Position, Position, int[][]> validatePosition) {
        this.numberRepresentation = numberRepresentation;
        this.validatePosition = validatePosition;
    }

    public int getNumberRepresentation() {
        return numberRepresentation;
    }

    public TriPredicate<Position, Position, int[][]> getValidatePosition() {
        return validatePosition;
    }

    public static String valueOf(int numberRepresentation) {
        int number = removeTypeOfPieceFromNumberRepresentation(numberRepresentation);
        for (ChessPieces piece : ChessPieces.values()) {
            if (piece.getNumberRepresentation() == number) {
                return piece.name();
            }
        }
        return "EMPTY";
    }

    private static TriPredicate<Position, Position, int[][]> pawnValidatePos() {
        return (position, current, board) -> {
            if (isOutSideTheBoard(current, position)) {
                return false;
            }

            ChessTypeOfPieces type = ChessTypeOfPieces.valueOf(board[current.row][current.column]);
            if (isNextPosAhead(current, position, board, type)) {
                return true;
            } else if (isPawnAbleToGo2Postions(current, position, board, type)) {
                return true;
            } else {
                return isDiagonalAndNotEmptyPos(current, position, board, type);
            }
        };
    }

    private static TriPredicate<Position, Position, int[][]> getRookValidatePos() {
        return (position, current, board) -> {
            if (isOutSideTheBoard(current, position) || isDiagonalMove(current, position)) {
                return false;
            }
            //TODO: WIP
            return true;
        };
    }

    static ChessPieces valueOfToObject(int numberRepresentation) {
        int number = removeTypeOfPieceFromNumberRepresentation(numberRepresentation);
        for (ChessPieces piece : ChessPieces.values()) {
            if (piece.getNumberRepresentation() == number) {
                return piece;
            }
        }
        throw new RuntimeException();
    }

    private static int removeTypeOfPieceFromNumberRepresentation(int numberRepresentation) {
        if (numberRepresentation - 10 < 10) {
            return numberRepresentation - 10;
        } else {
            return numberRepresentation - 20;
        }
    }
}
