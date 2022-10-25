package validation.movement;

import chess.Movement;
import chess.Turn;
import validation.movement.MovementValidator;

import java.util.Set;

/**
 * Representation of set of movements for classic or specific pieces.
 * The set passed in the constructor will define a piece's allowed movements.
 * Can be made of core movements or other piece's movement groups
 * */

public class PieceMovementValidator extends AbstractMovementValidator implements MovementValidator {

    public PieceMovementValidator(Set<MovementValidator> allowedMoves){
        super(allowedMoves);
    }


}
