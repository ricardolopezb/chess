package validation.movement.core;

import chess.Color;
import chess.Tile;
import chess.Turn;
import validation.movement.AbstractMovementValidator;
import validation.movement.MovementValidator;

import java.util.HashSet;
import java.util.Set;

public class UnidirectionalMovementValidator  extends AbstractMovementValidator {
    public UnidirectionalMovementValidator() {
        super(new HashSet<>());
    }

    @Override
    public boolean validate(Turn turn) {
        Color playerColor = turn.getFrom().getPiece().getColor();
        Tile from = turn.getFrom();
        Tile to = turn.getTo();
        if(playerColor == Color.WHITE){
            // fue cambiado
            return super.validateRestrictions(turn) && to.getNumber() >= from.getNumber();
        } else {
            return super.validateRestrictions(turn) && to.getNumber() <= from.getNumber();
        }
    }
}
