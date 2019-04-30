package chess.pieces;

import static chess.pieces.GenericMoveValidation.isPosEmpty;
import static chess.pieces._90DegreeMoveValidation.isAhead;

public class DiagonalMoveValidation {

    static boolean isDiagonalAndNotEmptyPos(Position current, Position goTo,
                                            int[][] board,
                                            ChessTypeOfPieces type) {
        return isDiagonalForOneMove(current, goTo)
                && isAhead(goTo, current, 1, type)
                && !isPosEmpty(board, goTo.getRow(), goTo.getColumn());
    }

    static boolean isDiagonalForOneMove(Position current, Position position) {
        return (position.getColumn() - current.getColumn() == 1
                || position.getColumn() - current.getColumn() == -1)
                && position.getRow() - current.getRow() != 0;
    }

    static boolean isDiagonalMove(Position current, Position position) {
        int col = Math.abs(current.getColumn() - position.getColumn());
        int row = Math.abs(current.getRow() - position.getRow());
        return col == row;
    }

}
