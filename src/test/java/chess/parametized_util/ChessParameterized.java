package chess.parametized_util;

import chess.pieces.Position;

public class ChessParameterized {
    public Position goTo;

    public Position current;

    public int[][] board;

    public boolean assertion;

    public String testCase;

    public static ChessParameterized bChessParameterized(Position goTo, Position current, int[][] board,
                                                  boolean assertion,
                                                  String testCase) {
        return new ChessParameterized(goTo, current, board, assertion, testCase);
    }

    ChessParameterized(Position goTo, Position current, int[][] board, boolean assertion, String testCase) {
        this.goTo = goTo;
        this.current = current;
        this.board = board;
        this.assertion = assertion;
        this.testCase = testCase;
    }
}
