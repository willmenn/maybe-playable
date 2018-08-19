package chess;

import java.util.HashMap;
import java.util.Map;

import static chess.ChessPieces.*;

class Board {
    private static final int WHITE_PAWN_INITIAL_POSITION = 1;
    private static final int BLACK_PAWN_INITIAL_POSITION = 6;
    private int[][] board;
    private Map<Character, Integer> rowNames;

    Board() {
        this.board = initBoard(new int[8][8]);
        this.rowNames = initRowName(new HashMap<>());
    }

    int[][] getBoard() {
        return board;
    }

    public boolean movePeiceTo(String piecePos, String moveTo) {
        Position piecePosition = getPosition(piecePos);
        Position moveToPosition = getPosition(moveTo);
        ChessPieces pieceToBeMoved = ChessPieces.valueOfToObject(this.board[piecePosition.row][piecePosition.column]);

        return false;
    }

    private void addFirstLinePieces(int startPosition, int[][] board, int type) {
        board[startPosition][0] = type + ROOK.getNumberRepresentation();
        board[startPosition][1] = type + KNIGHT.getNumberRepresentation();
        board[startPosition][2] = type + BISHOP.getNumberRepresentation();
        board[startPosition][3] = type + getQueenKingPos(startPosition, QUEEN, KING);
        board[startPosition][4] = type + getQueenKingPos(startPosition, KING, QUEEN);
        board[startPosition][5] = type + BISHOP.getNumberRepresentation();
        board[startPosition][6] = type + KNIGHT.getNumberRepresentation();
        board[startPosition][7] = type + ROOK.getNumberRepresentation();
    }

    private void fillWithPawns(int startPosition, int[][] board, int type) {
        int pawnLine = startPosition == 0 ? WHITE_PAWN_INITIAL_POSITION
                : BLACK_PAWN_INITIAL_POSITION;

        for (int i = 0; i < board[0].length; i++) {
            board[pawnLine][i] = type + PAWN.getNumberRepresentation();
        }
    }

    private int getQueenKingPos(int startPosition, ChessPieces king, ChessPieces queen) {
        int queenKingPos;
        if (startPosition == 0) {
            queenKingPos = king.getNumberRepresentation();
        } else {
            queenKingPos = queen.getNumberRepresentation();
        }
        return queenKingPos;
    }

    private Position getPosition(String pos) {
        char[] chars = pos.toCharArray();
        Integer row = rowNames.get(chars[0]);
        Integer column = Integer.parseInt(String.valueOf(chars[1]));
        return new Position(row, column);
    }

    private int[][] initBoard(int[][] board) {
        ChessTypeOfPieces[] types = ChessTypeOfPieces.values();
        for (ChessTypeOfPieces type : types) {
            int numberRepresentation = type.getNumberRepresentation();
            int startPoint = type.getStartPoint();
            addFirstLinePieces(startPoint, board, numberRepresentation);
            fillWithPawns(startPoint, board, numberRepresentation);
        }

        return board;
    }

    private Map<Character, Integer> initRowName(HashMap<Character, Integer> rowNames) {
        int a = 'a';
        for (int i = 0; i < 8; i++) {
            char[] letter = Character.toChars(a + i);
            rowNames.put(letter[0], i);
        }
        return rowNames;
    }


}
