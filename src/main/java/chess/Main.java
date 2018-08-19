package chess;

import static chess.util.TranslatorToConsole.translateForHumans;

public class Main {

    public static void main(String[] args) {
        Board chessBoard = new Board();
        System.out.println(translateForHumans(chessBoard.getBoard()));
    }
}
