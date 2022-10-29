package validation.movement.special;

import chess.PieceMover;
import chess.PieceName;
import chess.Tile;
import chess.Turn;
import utils.CoordinatePair;
import utils.Coordinates;
import validation.movement.AbstractMovementValidator;
import validation.movement.MovementValidator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CastlingValidator extends AbstractMovementValidator implements MovementValidator {
    private final PieceName piece1;
    private final PieceName piece2;

    public CastlingValidator(Set<MovementValidator> allowedMoves, PieceName piece1, PieceName piece2) {
        super(allowedMoves);
        this.piece1 = piece1;
        this.piece2 = piece2;
    }


    // TODO implement generic with variable board length
    @Override
    public boolean validate(Turn turn) {
        boolean inSameRow = validateSameRow(turn);
        boolean firstOrLastRow = (turn.getFrom().getNumber() == 1 && turn.getTo().getNumber() == 1)
                || (turn.getFrom().getNumber() == 8 && turn.getTo().getNumber() == 8);

        boolean whiteCastling = turn.getFrom().getNumber() == 1 && turn.getTo().getNumber() == 1;

        if(turn.getFrom().getPiece().getName() != piece1) return false;
        if(!(inSameRow && firstOrLastRow)) return false;
        if(!super.validateRestrictions(turn)) return false;


        int diff = turn.getTo().getLetter() - turn.getFrom().getLetter();
        System.out.println("diff" + diff);

        List<CoordinatePair> moves = new ArrayList<>();

        // enroque largo
        if(diff == -2){
            Coordinates rookFrom = new Coordinates('a', whiteCastling ? 1 : 8);
            Coordinates rookTo = new Coordinates((char) ((turn.getFrom().getLetter()-1)), whiteCastling ? 1 : 8);

            Coordinates kingFrom = new Coordinates(turn.getFrom().getLetter(), whiteCastling ? 1 : 8);
            Coordinates kingTo = new Coordinates(turn.getTo().getLetter(), whiteCastling ? 1 : 8);

            CoordinatePair rookMove = new CoordinatePair(rookFrom, rookTo);
            CoordinatePair kingMove = new CoordinatePair(kingFrom, kingTo);

            moves.add(rookMove);
            moves.add(kingMove);

        }
        else if (diff == 2){

            Coordinates rookFrom = new Coordinates('h', whiteCastling ? 1 : 8);
            Coordinates rookTo = new Coordinates((char) (turn.getFrom().getLetter()+1), whiteCastling ? 1 : 8);

            Coordinates kingFrom = new Coordinates(turn.getFrom().getLetter(), whiteCastling ? 1 : 8);
            Coordinates kingTo = new Coordinates(turn.getTo().getLetter(), whiteCastling ? 1 : 8);

            CoordinatePair rookMove = new CoordinatePair(rookFrom, rookTo);
            CoordinatePair kingMove = new CoordinatePair(kingFrom, kingTo);

            moves.add(rookMove);
            moves.add(kingMove);

        }
        PieceMover.getInstance().moveCastling(moves);

        return true;
    }

    private boolean validateSameRow(Turn turn) {
        return turn.getFrom().getNumber() == turn.getTo().getNumber();
    }
}
