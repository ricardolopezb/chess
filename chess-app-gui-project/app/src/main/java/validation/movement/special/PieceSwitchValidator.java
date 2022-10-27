package validation.movement.special;

import chess.PieceMover;
import chess.PieceName;
import chess.Tile;
import chess.Turn;
import validation.movement.AbstractMovementValidator;
import validation.movement.MovementValidator;

import java.util.Set;

public class PieceSwitchValidator extends AbstractMovementValidator implements MovementValidator {

    private final PieceName piece1;
    private final PieceName piece2;

    public PieceSwitchValidator(Set<MovementValidator> allowedMoves, PieceName piece1, PieceName piece2){
        super(allowedMoves);
        this.piece1 = piece1;
        this.piece2 = piece2;
    }

    @Override
    public boolean validate(Turn turn) {
        Tile from = turn.getFrom();
        Tile to = turn.getTo();
        if(!from.hasPiece() || !to.hasPiece()) return false;

        if(from.getPiece().getColor() == to.getPiece().getColor()
                && (from.getPiece().getName() == piece1 && to.getPiece().getName() == piece2
                || from.getPiece().getName() == piece2 && to.getPiece().getName() == piece1)
                && super.validateRestrictions(turn)){
            PieceMover.getInstance().setSwitchTurn(true);
            return true;
        }
        return false;
    }
}

