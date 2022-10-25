package chess.turn.manager;

import chess.Color;

public interface TurnManager {
    void handleMove();
    Color getCurrentTurnColor();
}
