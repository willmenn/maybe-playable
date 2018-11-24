package chess.pieces;

import static chess.pieces._90DegreeMoveValidation.isAhead;

class GenericMoveValidation {

    static boolean isPawnAbleToGo2Positions(Position current,
                                            Position goTo, int[][] board,
                                            ChessTypeOfPieces type) {
        return goTo.column.equals(current.column)
                && (current.row == 1 || current.row == 6)
                && isAhead(goTo, current, 2, type)
                && isPosEmpty(board, goTo.row, goTo.column);
    }

    static boolean isPosEmpty(int[][] board, int row, int column) {
        return board[row][column] == 0;
    }

    static boolean isOutSideTheBoard(Position current, Position goTo) {
        return isOutsideTheBoard(current) || isOutsideTheBoard(goTo);
    }

    private static boolean isOutsideTheBoard(Position position) {
        return position.column > 7 || position.row > 7
                || position.column < 0 || position.row < 0;
    }
}
