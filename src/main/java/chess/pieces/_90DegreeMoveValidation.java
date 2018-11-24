package chess.pieces;

import static chess.pieces.GenericMoveValidation.isPosEmpty;

class _90DegreeMoveValidation {

    static boolean isAhead(Position position, Position current, int posAvailableToGoForward,
                           ChessTypeOfPieces type) {
        return type.getValidMovement().test(position.row - current.row)
                && Math.abs(position.row - current.row) == posAvailableToGoForward;
    }

    static boolean isNextPosAhead(Position current,
                                  Position goTo, int[][] board,
                                  ChessTypeOfPieces type) {
        return goTo.column.equals(current.column)
                && isAhead(goTo, current, 1, type)
                && isPosEmpty(board, goTo.row, goTo.column);
    }

    static boolean isValidGoingBackwardsOrForwards(Position position, Position current, int[][] board) {
        ChessTypeOfPieces type = ChessTypeOfPieces.valueOf(board[current.row][current.column]);
        if (isNextPosAheadEnabledManyPositions(current, position, board, type)) {
            for (int i = current.getRow() + 1; i < position.getRow(); i++) {
                if (board[i][current.getColumn()] != 0) {
                    return false;
                }
            }
        } else {
            for (int i = current.getRow() - 1; i > position.getRow(); i--) {
                if (board[i][current.getColumn()] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isAhead(Position position, Position current,
                                   ChessTypeOfPieces type) {
        return type.getValidMovement().test(position.row - current.row);
    }

    private static boolean isNextPosAheadEnabledManyPositions(Position current,
                                                              Position goTo, int[][] board,
                                                              ChessTypeOfPieces type) {
        return goTo.column.equals(current.column)
                && isAhead(goTo, current, type)
                && isPosEmpty(board, goTo.row, goTo.column);
    }

    static boolean isValidGoingRightOrLeft(Position position, Position current, int[][] board) {
        if (position.getColumn() - current.getColumn() > 0) {
            for (int i = current.getColumn() + 1; i < position.getColumn(); i++) {
                if (board[current.getRow()][i] != 0) {
                    return false;
                }
            }
        } else {
            for (int i = current.getColumn() - 1; i > position.getColumn(); i--) {
                if (board[current.getRow()][i] != 0) {
                    return false;
                }
            }
        }

        return true;
    }
}
