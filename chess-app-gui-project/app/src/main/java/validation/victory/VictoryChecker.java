package validation.victory;

import chess.board.Board;
import utils.exceptions.InvalidMoveException;

public interface VictoryChecker {

    public boolean checkWin(Board board, boolean whiteTurn) throws InvalidMoveException;
}
