package chess.util;

import chess.ChessPieces;

import static java.lang.System.lineSeparator;

public class TranslatorToConsole {

    private static final String SEPARATED_BY_COMMA = ",";
    private static final String SPACE_ALIGN = " ";

    public static String translateForHumans(int[][] board) {
        String[][] translatedToString = translateToString(board);
        StringBuilder boardForHumans = new StringBuilder();
        for (String[] aBoard : translatedToString) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < board[0].length; j++) {
                if (j != 0) {
                    line.append(SEPARATED_BY_COMMA);
                }

                line.append(SPACE_ALIGN);
                line.append(aBoard[j]);

            }
            boardForHumans.append(line).append(lineSeparator());
        }
        return boardForHumans.toString();
    }

    private static String[][] translateToString(int[][] board) {
        String[][] boardTranslated = new String[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                String translated = ChessPieces.valueOf(board[i][j]);
                boardTranslated[i][j] = translated;
            }
        }
        return boardTranslated;
    }
}

