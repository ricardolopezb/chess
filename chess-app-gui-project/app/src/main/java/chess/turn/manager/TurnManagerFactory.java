package chess.turn.manager;

import chess.GameMode;

public class TurnManagerFactory {

    public static TurnManager forGameMode(GameMode mode){
        switch (mode){
            case CLASSIC -> {
                return new ClassicTurnManager();
            }
        }
        return null;
    }

}
