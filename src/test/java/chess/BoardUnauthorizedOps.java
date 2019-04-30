package chess;

import java.lang.reflect.Field;

class BoardUnauthorizedOps {

    static void setBoardField(Board board, int[][] boardMatrix) throws NoSuchFieldException, IllegalAccessException {
        Field boardField = Board.class.getDeclaredField("board");
        boardField.setAccessible(true);
        boardField.set(board, boardMatrix);
    }
}
