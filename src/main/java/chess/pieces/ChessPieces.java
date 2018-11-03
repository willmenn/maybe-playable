package chess.pieces;

import chess.util.TriPredicate;

import static chess.pieces.ChessPiecesFunction.*;
import static java.lang.Math.abs;

public enum ChessPieces {
    KING(1, kingValidatePos()),
    QUEEN(2, queenValidatePos()),
    ROOK(3, rookValidatePos()),
    BISHOP(4, bishopValidatePos()),
    KNIGHT(5, knightValidatePos()),
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

    public static ChessPieces valueOfToObject(int numberRepresentation) {
        int number = removeTypeOfPieceFromNumberRepresentation(numberRepresentation);
        for (ChessPieces piece : ChessPieces.values()) {
            if (piece.getNumberRepresentation() == number) {
                return piece;
            }
        }
        throw new RuntimeException();
    }

    private static int addColumnGivenRightOrLeft(Position position, Position current, int col) {
        if (position.getColumn() - current.getColumn() > 0) {
            col++;
        } else { // going left
            col--;
        }
        return col;
    }

    private static TriPredicate<Position, Position, int[][]> bishopValidatePos() {
        return (position, current, board) -> {
            if (isOutSideTheBoard(current, position) || !isDiagonalMove(current, position)) {
                return false;
            }

            return validateDiagonalMovement(position, current, board);
        };
    }

    private static int getColumnStartPosition(Position position, Position current) {
        return position.getColumn() - current.getColumn() > 0 ? current.getColumn() + 1
                : current.getColumn() - 1;
    }

    private static TriPredicate<Position, Position, int[][]> kingValidatePos() {
        return (position, current, board) -> {
            if (isOutSideTheBoard(current, position)) {
                return false;
            }

            return validatePieceOnlyMovesOneBlockForward(position, current);
        };
    }

    private static boolean validatePieceOnlyMovesOneBlockForward(Position position, Position current) {
        return Math.abs(current.row - position.row) <= 1 && Math.abs(current.column - position.column) <= 1;
    }

    private static TriPredicate<Position, Position, int[][]> knightValidatePos() {
        return (position, current, board) -> {
            if (isOutSideTheBoard(current, position)) {
                return false;
            }

            if (validateKnightPos(position, current, 2, 1)) {
                return true;
            } else {
                return validateKnightPos(position, current, 1, 2);
            }
        };
    }

    private static TriPredicate<Position, Position, int[][]> queenValidatePos() {
        return (position, current, board) -> {
            if (isOutSideTheBoard(current, position)) {
                return false;
            }

            if (isDiagonalMove(current, position)) {
                return validateDiagonalMovement(position, current, board);
            } else {
                return validate90DegreesMovement(position, current, board);
            }

        };
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

    private static int removeTypeOfPieceFromNumberRepresentation(int numberRepresentation) {
        if (numberRepresentation - 10 < 10) {
            return numberRepresentation - 10;
        } else {
            return numberRepresentation - 20;
        }
    }

    private static TriPredicate<Position, Position, int[][]> rookValidatePos() {
        return (position, current, board) -> {
            if (isOutSideTheBoard(current, position) || isDiagonalForOneMove(current, position)) {
                return false;
            }

            return validate90DegreesMovement(position, current, board);
        };
    }

    private static boolean validate90DegreesMovement(Position position, Position current, int[][] board) {
        if (position.getColumn().equals(current.getColumn())) {
            return isValidGoingBackwardsOrForwards(position, current, board);
        } else if (position.getRow().equals(current.getRow())) {
            return isValidGoingRightOrLeft(position, current, board);
        } else {
            return false;
        }
    }

    private static boolean validateDiagonalMoveGoingPositiveRows(Position position, Position current, int[][] board) {
        int col = getColumnStartPosition(position, current);
        for (int i = current.getRow() + 1; i < Math.abs(current.getRow() - 8); i++) {
            if (board[i][col] != 0 && !(i == position.getRow() && col == position.getColumn())) {
                return false;
            }
            if (col == position.getColumn()) {
                return true;
            }
            col = addColumnGivenRightOrLeft(position, current, col);
        }
        return false;
    }

    private static boolean validateDiagonalMovement(Position position, Position current, int[][] board) {
        if (position.row - current.row >= 0) {
            return validateDiagonalMoveGoingPositiveRows(position, current, board);
        } else {
            return validateDiagonalMoveGoingNegativeRows(position, current, board);
        }
    }

    private static boolean validateDiagonalMoveGoingNegativeRows(Position position, Position current, int[][] board) {
        int col = getColumnStartPosition(position, current);
        for (int i = current.getRow() - 1; i >= position.row; i--) {
            if (board[i][col] != 0 && !(i == position.getRow() && col == position.getColumn())) {
                return false;
            }
            if (col == position.getColumn()) {
                return true;
            }
            col = addColumnGivenRightOrLeft(position, current, col);
        }
        return false;
    }

    private static boolean validateKnightPos(Position position, Position current, int row, int column) {
        return abs(current.getRow() - position.getRow()) == row
                && abs(current.getColumn() - position.getColumn()) == column;
    }
}
