package chess.pieces;

import java.util.List;
import java.util.Optional;

import static chess.pieces.ChessPieces.PAWN;
import static chess.pieces.ChessTypeOfPieces.BLACK;
import static chess.pieces.DiagonalMoveValidation.isDiagonalMove;

public class PawnEnPassant {

    /**
     * This function has the intent to validate if the next capture
     * will be an en passant capture returning a true if it is.
     * Maybe Creating another function to execute the En Passant Capture.
     */
    public boolean isPawnAbleToCaptureEnPassant(Position from, Position goTo,
                                                int[][] board, ChessTypeOfPieces type,
                                                List<Position> pawsEnPassant) {
        int possiblePawnToBeCaptured = board[goTo.getRow() - 1][goTo.getColumn()];
        int possiblePawn = board[from.getRow()][from.getColumn()];

        if (isDiagonalMove(from, goTo)
                && ChessPieces.valueOf(possiblePawnToBeCaptured).equals(PAWN.name())
                && ChessPieces.valueOf(possiblePawn).equals(PAWN.name())
                && !ChessTypeOfPieces.valueOf(possiblePawnToBeCaptured).equals(type)) {

            Position posPawnToBeCaptured = new Position(goTo.getRow() - 1, goTo.getColumn());

            Optional<Position> isAvailableToBeCaptured = pawsEnPassant.stream()
                    .filter(p -> p.equals(posPawnToBeCaptured))
                    .findFirst();

            return isAvailableToBeCaptured.isPresent();
        }

        return false;
    }

    /**
     * The idea is to validate if the pawn that moved is EnPassant, ou seja ele fez
     * o seu primeiro movimento sendo o movimento de duas casa, assim sendo possível
     * o pawn ser capturado EnPassant. Para isto a classe board deverá então verificar se o
     * Pawn é EnPassant adicionar então ele em um array e validar se o outro pawn então pode fazer
     * a captura.
     * E o Board também deverá guardar os Pawn que nao  foram executados na sua primeira vez
     * com dois movimentos pois este não poderao ser executados o  EnPassant.
     */
    public boolean isEnPassant(Position from, Position goTo,
                               int[][] board, ChessTypeOfPieces type) {
        int howManyPositionWillMove = 0;
        if (type.equals(BLACK)) {
            howManyPositionWillMove = goTo.getRow() - from.getRow();
        } else {
            howManyPositionWillMove = -1 * (goTo.getRow() - from.getRow());
        }
        int pawnPosition = board[from.getRow()][from.getColumn()];

        return !isDiagonalMove(from, goTo)
                && howManyPositionWillMove == 2
                && ChessPieces.valueOf(pawnPosition).equals(PAWN.name())
                && type.getPawnFirstRowPosition() == from.getRow();
    }
}
