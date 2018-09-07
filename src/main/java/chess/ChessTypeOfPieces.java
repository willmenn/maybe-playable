package chess;

import chess.exception.EmptyPositionException;

import java.util.function.Predicate;

public enum ChessTypeOfPieces {
    WHITE(20, 0, (value) -> value > 0),
    BLACK(10, 7, (value) -> value < 0);

    private int numberRepresentation;
    private int startPoint;
    private Predicate<Integer> validMovement;

    ChessTypeOfPieces(int numberRepresentation, int startPoint, Predicate<Integer> validMovement) {
        this.numberRepresentation = numberRepresentation;
        this.startPoint = startPoint;
        this.validMovement = validMovement;
    }

    public int getNumberRepresentation() {
        return numberRepresentation;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public Predicate<Integer> getValidMovement() {
        return validMovement;
    }

    static ChessTypeOfPieces valueOf(int value) {
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
}
