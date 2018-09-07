package chess;

import chess.util.TriPredicate;

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
            if (isOutsideTheBoard(position) || isOutsideTheBoard(current)) {
                return false;
            }

            ChessTypeOfPieces type = ChessTypeOfPieces.valueOf(board[current.row][current.column]);
            if (position.column.equals(current.column)
                    && isAhead(position, current, 1, type)
                    && isPosEmpty(board, position.row, position.column)) {
                return true;
            } else if (position.column.equals(current.column)
                    && (current.row == 1 || current.row == 6)
                    && isAhead(position, current, 2, type)
                    && isPosEmpty(board, position.row, position.column)) {
                return true;
            } else {
                return isDiagonalMove(position, current)
                        && isAhead(position, current, 1, type)
                        && !isPosEmpty(board, position.row, position.column);
            }
        };
    }

    private static TriPredicate<Position, Position, int[][]> getRookValidatePos() {
        return (position, current, board) -> {
            if (isOutsideTheBoard(position) || isDiagonalMove(position, current)) {
                return false;
            }
            //TODO: WIP
            return true;
        };
    }

    private static boolean isOutsideTheBoard(Position position) {
        return position.column > 7 || position.row > 7
                || position.column < 0 || position.row < 0;
    }

    private static boolean isPosEmpty(int[][] board, int row, int column) {
        return board[row][column] == 0;
    }

    private static boolean isAhead(Position position, Position current, int posAvailableToGoForward,
                                   ChessTypeOfPieces type) {
        return type.getValidMovement().test(position.row - current.row)
                && Math.abs(position.row - current.row) == posAvailableToGoForward;
    }

    private static boolean isDiagonalMove(Position position, Position current) {
        return (position.column - current.column == 1
                || position.column - current.column == -1)
                && position.row - current.row != 0;
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
