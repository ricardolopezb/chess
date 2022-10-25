package validation.movement;

import chess.Turn;

import java.util.Set;

public abstract class AbstractMovementValidator implements MovementValidator{
    Set<MovementValidator> allowedMoves;

    public AbstractMovementValidator(Set<MovementValidator> allowedMoves){
        this.allowedMoves = allowedMoves;
    }

    public boolean validate(Turn turn){
        if(allowedMoves.isEmpty()) return true;
        for (MovementValidator allowedMove : allowedMoves) {
            if(allowedMove.validate(turn)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateRestrictions(Turn turn){
        if(allowedMoves.isEmpty()) return true;
        boolean result = true;
        for (MovementValidator allowedMove : allowedMoves) {
            result = result && allowedMove.validate(turn);
        }
        return result;
    }

}
