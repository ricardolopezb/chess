package validation.movement.core;


import chess.Tile;
import chess.Turn;
import validation.movement.AbstractMovementValidator;
import validation.movement.MovementValidator;

import java.util.HashSet;
import java.util.Set;

public class VariableMovementValidator extends AbstractMovementValidator implements MovementValidator {
    private final int x;
    private final int y;
    private final int extension;


    public VariableMovementValidator(int x, int y, int extension){
        super(new HashSet<>());
        this.x = x;
        this.y = y;
        this.extension = extension;
    }

    @Override
    public boolean validate(Turn turn){

        Tile to = turn.getTo();
        Tile from = turn.getFrom();

        int toLetter = to.getLetter() - 96;
        int toNumber = to.getNumber();

        int fromLetter = from.getLetter() - 96;
        int fromNumber = from.getNumber();

        int xSubt = Math.abs(toLetter-fromLetter);
        boolean divByX = xSubt%x == 0;
        boolean lessThanLimitX = xSubt/x <= extension;

        int ySubt = Math.abs(toNumber-fromNumber);
        boolean divByY = ySubt%y == 0;
        boolean lessThanLimitY = ySubt/y <= extension;

        return super.validateRestrictions(turn) && divByX && divByY && lessThanLimitX && lessThanLimitY && ((ySubt/y)==(xSubt/x));
    }


}
