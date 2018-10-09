package chess;

import chess.pieces.ChessPieces;
import chess.pieces.ChessTypeOfPieces;
import chess.pieces.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.pieces.ChessPieces.*;
import static chess.pieces.ChessTypeOfPieces.BLACK;
import static chess.pieces.ChessTypeOfPieces.WHITE;

class Board {
    private static final int WHITE_PAWN_INITIAL_POSITION = 1;
    private static final int BLACK_PAWN_INITIAL_POSITION = 6;
    private static final int EMPTY_BLOCK = 0;
    private int[][] board;
    private Map<Character, Integer> rowNames;
    private List<Integer> whitePiecesRemoved;
    private List<Integer> blackPiecesRemoved;

    Board() {
        this.board = initBoard(new int[8][8]);
        this.rowNames = initRowName(new HashMap<>());
        this.whitePiecesRemoved = new ArrayList<>();
        this.blackPiecesRemoved = new ArrayList<>();
    }

    int[][] getBoard() {
        return board;
    }

    //TODO: Check for checkmate
    public boolean movePeiceTo(String piecePos, String moveTo) {
        Position piecePosition = getPosition(piecePos);
        Position moveToPosition = getPosition(moveTo);
        ChessPieces pieceToBeMoved = ChessPieces.valueOfToObject(
                this.board[piecePosition.getRow()][piecePosition.getColumn()]);

        if (pieceToBeMoved.getValidatePosition().test(moveToPosition, piecePosition, board)) {
            ifPieceIsPresentAddOnRemovedArray(moveToPosition, pieceToBeMoved);

            movePiece(piecePosition, moveToPosition, pieceToBeMoved);
        }

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

    private void ifPieceIsPresentAddOnRemovedArray(Position moveToPosition, ChessPieces pieceToBeMoved) {
        ChessTypeOfPieces type = ChessTypeOfPieces.valueOf(pieceToBeMoved.getNumberRepresentation());
        int elementToBeRemoved = board[moveToPosition.getRow()][moveToPosition.getColumn()];

        if (elementToBeRemoved != EMPTY_BLOCK && type.equals(WHITE)) {
            whitePiecesRemoved.add(elementToBeRemoved);
        } else if (type.equals(BLACK)) {
            blackPiecesRemoved.add(elementToBeRemoved);
        }
    }

    private void movePiece(Position piecePosition, Position moveToPosition, ChessPieces pieceToBeMoved) {
        board[piecePosition.getRow()][piecePosition.getColumn()] = EMPTY_BLOCK;
        board[moveToPosition.getRow()][moveToPosition.getColumn()] = pieceToBeMoved.getNumberRepresentation();
    }
}
