package chess;

class ChessPiecesFunction {

    static boolean isDiagonalAndNotEmptyPos(Position current, Position goTo,
                                            int[][] board,
                                            ChessTypeOfPieces type) {
        return isDiagonalMove(current, goTo)
                && isAhead(goTo, current, 1, type)
                && !isPosEmpty(board, goTo.row, goTo.column);
    }

    static boolean isDiagonalMove(Position current, Position position) {
        return (position.column - current.column == 1
                || position.column - current.column == -1)
                && position.row - current.row != 0;
    }

    static boolean isNextPosAhead(Position current,
                                  Position goTo, int[][] board,
                                  ChessTypeOfPieces type) {
        return goTo.column.equals(current.column)
                && isAhead(goTo, current, 1, type)
                && isPosEmpty(board, goTo.row, goTo.column);
    }

    static boolean isOutSideTheBoard(Position current, Position goTo) {
        return isOutsideTheBoard(current) || isOutsideTheBoard(goTo);
    }

    static boolean isPawnAbleToGo2Positions(Position current,
                                            Position goTo, int[][] board,
                                            ChessTypeOfPieces type) {
        return goTo.column.equals(current.column)
                && (current.row == 1 || current.row == 6)
                && isAhead(goTo, current, 2, type)
                && isPosEmpty(board, goTo.row, goTo.column);
    }

    private static boolean isAhead(Position position, Position current, int posAvailableToGoForward,
                                   ChessTypeOfPieces type) {
        return type.getValidMovement().test(position.row - current.row)
                && Math.abs(position.row - current.row) == posAvailableToGoForward;
    }

    private static boolean isOutsideTheBoard(Position position) {
        return position.column > 7 || position.row > 7
                || position.column < 0 || position.row < 0;
    }

    private static boolean isPosEmpty(int[][] board, int row, int column) {
        return board[row][column] == 0;
    }
}
