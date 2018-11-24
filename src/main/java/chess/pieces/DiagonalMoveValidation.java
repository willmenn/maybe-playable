package chess.pieces;

import static chess.pieces.GenericMoveValidation.isPosEmpty;
import static chess.pieces._90DegreeMoveValidation.isAhead;

public class DiagonalMoveValidation {

    static boolean isDiagonalAndNotEmptyPos(Position current, Position goTo,
                                            int[][] board,
                                            ChessTypeOfPieces type) {
        return isDiagonalForOneMove(current, goTo)
                && isAhead(goTo, current, 1, type)
                && !isPosEmpty(board, goTo.row, goTo.column);
    }

    static boolean isDiagonalForOneMove(Position current, Position position) {
        return (position.column - current.column == 1
                || position.column - current.column == -1)
                && position.row - current.row != 0;
    }

    static boolean isDiagonalMove(Position current, Position position) {
        int col = Math.abs(current.column - position.column);
        int row = Math.abs(current.row - position.row);
        return col == row;
    }

}
