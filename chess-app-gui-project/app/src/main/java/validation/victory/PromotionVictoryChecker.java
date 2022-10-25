package validation.victory;

import chess.board.Board;

public class PromotionVictoryChecker implements VictoryChecker{


    @Override
    public boolean checkWin(Board board, boolean whiteTurn) {
        return false;
    }
}
