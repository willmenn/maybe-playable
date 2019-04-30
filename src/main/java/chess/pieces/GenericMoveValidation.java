package chess.pieces;

import static chess.pieces._90DegreeMoveValidation.isAhead;

class GenericMoveValidation {

    static boolean isPawnAbleToGo2Positions(Position current,
                                            Position goTo, int[][] board,
                                            ChessTypeOfPieces type) {
        return goTo.getColumn().equals(current.getColumn())
                && (current.getRow() == 1 || current.getRow() == 6)
                && isAhead(goTo, current, 2, type)
                && isPosEmpty(board, goTo.getRow(), goTo.getColumn());
    }

    static boolean isPosEmpty(int[][] board, int row, int column) {
        return board[row][column] == 0;
    }

    static boolean isOutSideTheBoard(Position current, Position goTo) {
        return isOutsideTheBoard(current) || isOutsideTheBoard(goTo);
    }

    private static boolean isOutsideTheBoard(Position position) {
        return position.getColumn() > 7 || position.getRow() > 7
                || position.getColumn() < 0 || position.getRow() < 0;
    }
}
