package chess;

public enum ChessTypeOfPieces {
    WHITE(20, 0),
    BLACK(10, 7);

    private int numberRepresentation;
    private int startPoint;

    ChessTypeOfPieces(int numberRepresentation, int startPoint) {
        this.numberRepresentation = numberRepresentation;
        this.startPoint = startPoint;
    }

    public int getNumberRepresentation() {
        return numberRepresentation;
    }

    public int getStartPoint() {
        return startPoint;
    }
}
