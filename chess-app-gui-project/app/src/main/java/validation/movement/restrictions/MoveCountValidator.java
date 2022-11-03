package validation.movement.restrictions;

import chess.Tile;
import chess.Turn;
import validation.movement.AbstractMovementValidator;
import validation.movement.MovementValidator;

import java.util.HashSet;
import java.util.Set;

public class MoveCountValidator extends AbstractMovementValidator implements MovementValidator {
    public MoveCountValidator() {
        super(new HashSet<>());
    }

    // planteado para una cant generica de movimientos hechos
//    private final int min;
//    private final int max;
//
//    public MoveCountValidator(int min, int max){
//        this.min = min;
//        this.max = max;
//    }

    @Override
    public boolean validate(Turn turn) {
        return !turn.getFrom().hasChanged() && !turn.getTo().hasChanged();
    }
}

