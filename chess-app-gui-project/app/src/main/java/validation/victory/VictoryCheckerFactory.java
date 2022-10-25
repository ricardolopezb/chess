package validation.victory;

import chess.GameMode;
import validation.Validator;

public class VictoryCheckerFactory {
    private final Validator validator;

    public VictoryCheckerFactory(Validator validator) {
        this.validator = validator;
    }

    public VictoryChecker getVictoryCheckerForGameMode(GameMode gameMode){
        switch (gameMode){
            case CLASSIC -> {return new CheckMateChecker(validator);}
//            case BERLIN -> {return new CheckMateChecker();}
//            case CLASSIC -> {return new CheckMateChecker();}
            default -> {
                return null;
            }
        }


    }

}
