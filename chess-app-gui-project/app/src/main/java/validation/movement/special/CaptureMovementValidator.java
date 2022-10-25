package validation.movement.special;

import chess.Color;
import chess.PieceName;
import chess.Tile;
import chess.Turn;
import validation.movement.MovementValidator;

public class CaptureMovementValidator implements MovementValidator {


    @Override
    public boolean validate(Turn turn) {
        Tile to = turn.getTo();
        Tile from = turn.getFrom();
        Color myColor = from.getPiece().getColor();

        int toLetter = to.getLetter() - 96;
        int toNumber = to.getNumber();

        int fromLetter = from.getLetter() - 96;
        int fromNumber = from.getNumber();

        boolean captureDirection = myColor == Color.WHITE ? to.getNumber() >= from.getNumber() : to.getNumber() <= from.getNumber();

        // could be more generic
        if(from.getPiece().getName() == PieceName.PAWN){
            return to.hasPiece()
                    && to.getPiece().getColor() != myColor
                    && Math.abs(toLetter-fromLetter) == 1
                    && Math.abs(toNumber-fromNumber) == 1
                    && captureDirection;
        }
        return false;
    }
}
