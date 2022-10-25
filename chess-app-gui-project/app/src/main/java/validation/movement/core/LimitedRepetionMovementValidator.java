package validation.movement.core;

import chess.Tile;
import chess.Turn;
import validation.movement.AbstractMovementValidator;
import validation.movement.MovementValidator;

import java.util.HashSet;
import java.util.Set;


public class LimitedRepetionMovementValidator extends AbstractMovementValidator {

    private final int limit;
    private int count = 0;

    public LimitedRepetionMovementValidator(int limit) {
        super(new HashSet<>());
        this.limit=limit;
    }


    @Override
    public boolean validate(Turn turn) {
        if (count == limit) {
            return false;
        } else {
            count++;
            return true;
        }
    }
}
