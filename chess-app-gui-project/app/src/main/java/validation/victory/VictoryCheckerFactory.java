package validation.victory;

import chess.GameMode;
import validation.Validator;

public class VictoryCheckerFactory {
    private final Validator validator;

    public VictoryCheckerFactory(Validator validator) {
        this.validator = validator;
    }

    public VictoryChecker getVictoryCheckerForGameMode(GameMode gameMode){
        return switch (gameMode){
            case CLASSIC, CAPABLANCA -> new CheckMateChecker(validator);
            default -> null;
        };
    }

}
