package chess.pieces;

import chess.exception.EmptyPositionException;

import java.util.function.Predicate;

public enum ChessTypeOfPieces {
    WHITE(20, 0, (value) -> value > 0, 6),
    BLACK(10, 7, (value) -> value < 0, 1);

    private int numberRepresentation;
    private int startPoint;
    private Predicate<Integer> validMovement;
    private int pawnFirstRowPosition;

    ChessTypeOfPieces(int numberRepresentation, int startPoint, Predicate<Integer> validMovement,
                      int pawnFirstRowPosition) {
        this.numberRepresentation = numberRepresentation;
        this.startPoint = startPoint;
        this.validMovement = validMovement;
        this.pawnFirstRowPosition = pawnFirstRowPosition;
    }

    public int getNumberRepresentation() {
        return numberRepresentation;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public int getPawnFirstRowPosition() {
        return pawnFirstRowPosition;
    }

    public Predicate<Integer> getValidMovement() {
        return validMovement;
    }

    public static ChessTypeOfPieces valueOf(int value) {
        if (value == 0) {
            throw new EmptyPositionException();
        }

        if (value - WHITE.numberRepresentation < 10 && value - WHITE.numberRepresentation > 0) {
            return WHITE;
        } else if (value - BLACK.numberRepresentation < 10 && value - BLACK.numberRepresentation > 0) {
            return BLACK;
        }

        throw new IllegalArgumentException("Value: " + value);
    }

    public static ChessTypeOfPieces invertType(ChessTypeOfPieces type) {
        if (type.equals(BLACK)) {
            return WHITE;
        } else {
            return BLACK;
        }
    }
}
