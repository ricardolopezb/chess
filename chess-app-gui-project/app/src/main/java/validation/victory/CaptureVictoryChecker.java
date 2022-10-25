package validation.victory;

import chess.board.Board;

public class CaptureVictoryChecker implements VictoryChecker {

    @Override
    public boolean checkWin(Board board, boolean whiteTurn) {
        return false;
    }
}
