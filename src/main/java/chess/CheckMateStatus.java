package chess;

public enum CheckMateStatus {
    CHECK, CHECK_MATE, NONE;

    public static CheckMateStatus isCheckMate(int sum, int sumPosOfKing) {
        if (sum == sumPosOfKing) {
            return CHECK_MATE;
        } else if (sum > 0) {
            return CHECK;
        } else {
            return NONE;
        }
    }
}
