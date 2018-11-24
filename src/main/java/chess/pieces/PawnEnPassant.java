package chess.pieces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.pieces.ChessPieces.PAWN;
import static chess.pieces.DiagonalMoveValidation.isDiagonalMove;
import static chess.pieces.GenericMoveValidation.isPawnAbleToGo2Positions;

public class PawnEnPassant {

    public List<Position> isPawn2MoveAhead(Position from, Position goTo,
                                           int[][] board, ChessTypeOfPieces type, List<Position> paws) {
        if (isPawnAbleToGo2Positions(from, goTo, board, type)) {
            List<Position> pawsEnabledEnPassant = new ArrayList<>();
            Collections.copy(paws, pawsEnabledEnPassant);
            pawsEnabledEnPassant.add(goTo);
            return pawsEnabledEnPassant;
        }
        return paws;
    }

    public boolean isEnPassant(Position from, Position goTo,
                               int[][] board, ChessTypeOfPieces type) {
        if (!type.equals(PAWN)) {
            return false;
        }
        int possiblePawn = board[from.getRow()][goTo.getColumn()];

        return isDiagonalMove(from, goTo) || ChessPieces.valueOf(possiblePawn).equals(PAWN.name());
    }

    public Position getPawnThatMustBeRemovedByEnPassant(Position from, Position goTo) {
        return new Position(from.getRow(), goTo.getColumn());
    }
}
