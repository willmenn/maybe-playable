package chess;

import chess.pieces.*;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static chess.pieces.ChessPieces.*;
import static chess.pieces.ChessTypeOfPieces.*;

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

    public boolean movePeiceTo(String piecePos, String moveTo) {
        Position piecePosition = getPosition(piecePos);
        Position moveToPosition = getPosition(moveTo);
        ChessPieces pieceToBeMoved = evaluatePositionToPiece(piecePosition);

        if (pieceToBeMoved.getValidatePosition().test(moveToPosition, piecePosition, board)) {
            ifPieceIsPresentAddOnRemovedArray(moveToPosition, pieceToBeMoved);

            movePiece(piecePosition, moveToPosition, pieceToBeMoved);
        }

        return false;
    }

    private ChessPieces evaluatePositionToPiece(Position piecePosition) {
        return ChessPieces.valueOfToObject(
                this.board[piecePosition.getRow()][piecePosition.getColumn()]);
    }

    //TODO: Missing the unit tests
    public CheckMateStatus isCheckMate(ChessTypeOfPieces type) {
        int kingNumber = type.getNumberRepresentation() + KING.getNumberRepresentation();
        Position kingPosition = walkOnBoard(findPiecePos(kingNumber));
        List<Position> kingPossiblePos = getAllPossiblePositionForAKing(kingPosition);

        List<Position> allPiecesPos = getAllOponentPiecesPositions(type);

        int sumOfPiecesThatAreCheckTheKing = getSumOfAllPiecesThatAreCheckTheKing(kingPossiblePos, allPiecesPos);

        return CheckMateStatus.isCheckMate(sumOfPiecesThatAreCheckTheKing, kingPossiblePos.size());
    }

    private int getSumOfAllPiecesThatAreCheckTheKing(List<Position> kingPossiblePositions, List<Position> allPiecesPositions) {
        return allPiecesPositions.stream().mapToInt(piece ->
                kingPossiblePositions.stream().mapToInt(pos -> evaluatePositionToPiece(piece)
                        .getValidatePosition().test(piece, pos, this.board) ? 1 : 0).sum()).sum();
    }

    private List<Position> getAllPossiblePositionForAKing(Position king) {
        List<Position> possiblePositions = new ArrayList<>();
        validatePositionAndAdd(possiblePositions, king, new Position(king.getRow() - 1, king.getColumn() - 1));
        validatePositionAndAdd(possiblePositions, king, new Position(king.getRow() - 1, king.getColumn()));
        validatePositionAndAdd(possiblePositions, king, new Position(king.getRow() - 1, king.getColumn() + 1));
        validatePositionAndAdd(possiblePositions, king, new Position(king.getRow() + 1, king.getColumn() - 1));
        validatePositionAndAdd(possiblePositions, king, new Position(king.getRow() + 1, king.getColumn()));
        validatePositionAndAdd(possiblePositions, king, new Position(king.getRow(), king.getColumn() - 1));
        validatePositionAndAdd(possiblePositions, king, new Position(king.getRow(), king.getColumn() + 1));

        return possiblePositions;
    }

    private void validatePositionAndAdd(List<Position> possiblePositions, Position king, Position future) {
        if (isInsideTheBoard(future.getRow()) && isInsideTheBoard(future.getColumn())) {
            possiblePositions.add(future);
        }
    }

    private boolean isInsideTheBoard(Integer future) {
        return future < 0 || future > 7;
    }

    private List<Position> getAllOponentPiecesPositions(ChessTypeOfPieces type) {
        ChessTypeOfPieces otherType = ChessTypeOfPieces.invertType(type);
        return Arrays.asList(ChessPieces.values()).stream()
                .map(piece ->
                        walkOnBoard(findPiecePos(otherType.getNumberRepresentation()
                                + piece.getNumberRepresentation())))
                .collect(Collectors.toList());
    }

    private BiFunction<Integer, Integer, Position> findPiecePos(int pieceNumber) {
        return (i, j) -> {
            if (this.board[i][j] == pieceNumber) {
                return new Position(i, j);
            } else {
                return null;
            }
        };
    }


    private Position walkOnBoard(BiFunction<Integer, Integer, Position> func) {
        Position pos = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pos = func.apply(i, j);
            }
        }
        return pos;
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
