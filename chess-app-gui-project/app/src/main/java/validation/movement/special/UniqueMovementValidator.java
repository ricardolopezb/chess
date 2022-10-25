package validation.movement.special;

import chess.Turn;
import validation.movement.AbstractMovementValidator;
import validation.movement.MovementValidator;

import java.util.HashSet;
import java.util.Set;

public class UniqueMovementValidator extends AbstractMovementValidator implements MovementValidator {
    private Set<String> movedPiecesIds;

    public UniqueMovementValidator() {
        super(new HashSet<>());
        movedPiecesIds = new HashSet<>();
    }


    @Override
    public boolean validate(Turn turn) {
        if(!movedPiecesIds.contains(turn.getFrom().getPiece().getId())){
            movedPiecesIds.add(turn.getFrom().getPiece().getId());
            return true;
        } else return false;
    }
}
