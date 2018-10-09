package chess.pieces;

import chess.util.TriPredicate;

import static chess.pieces.ChessPiecesFunction.isDiagonalAndNotEmptyPos;
import static chess.pieces.ChessPiecesFunction.isDiagonalMove;
import static chess.pieces.ChessPiecesFunction.isNextPosAhead;
import static chess.pieces.ChessPiecesFunction.isNextPosAheadEnabledManyPositions;
import static chess.pieces.ChessPiecesFunction.isOutSideTheBoard;
import static chess.pieces.ChessPiecesFunction.isPawnAbleToGo2Positions;
import static chess.pieces.ChessPiecesFunction.isValidGoingBackwardsOrForwards;
import static chess.pieces.ChessPiecesFunction.isValidGoingRightOrLeft;

public enum ChessPieces {
    KING(1, (position, current, board) -> true),
    QUEEN(2, (position, current, board) -> true),
    ROOK(3, rookValidatePos()),
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
            } else if (isPawnAbleToGo2Positions(current, position, board, type)) {
                return true;
            } else {
                return isDiagonalAndNotEmptyPos(current, position, board, type);
            }
        };
    }

    public static ChessPieces valueOfToObject(int numberRepresentation) {
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

    private static TriPredicate<Position, Position, int[][]> rookValidatePos() {
        return (position, current, board) -> {
            if (isOutSideTheBoard(current, position) || isDiagonalMove(current, position)) {
                return false;
            }

            if (position.getColumn().equals(current.getColumn())) {
                return isValidGoingBackwardsOrForwards(position, current, board);
            } else {
                return isValidGoingRightOrLeft(position, current, board);
            }
        };
    }

}
