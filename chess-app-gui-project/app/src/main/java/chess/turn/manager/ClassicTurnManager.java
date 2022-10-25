package chess.turn.manager;

import chess.Color;

public class ClassicTurnManager implements TurnManager{

    private boolean whiteTurn;

    public ClassicTurnManager() {
        this.whiteTurn = true;
    }

    @Override
    public void handleMove() {
        this.whiteTurn = !this.whiteTurn;
    }

    @Override
    public Color getCurrentTurnColor() {
        return whiteTurn ? Color.WHITE : Color.BLACK;
    }
}
