package validation;

import chess.board.Board;
import chess.Tile;
import chess.Turn;

public class PathValidator {

    public boolean validate(Turn turn, Board board){
        Tile from = turn.getFrom();
        Tile to = turn.getTo();

        int fromLetter = from.getLetter() - 96;
        int toLetter = to.getLetter() - 96;
        int vectorLetter = toLetter - fromLetter;

        int fromNumber = from.getNumber();
        int toNumber = to.getNumber();
        int vectorNumber = toNumber - fromNumber;

        // minimize
        int hcf = hcf(vectorLetter, vectorNumber);
        vectorLetter = vectorLetter / hcf;
        vectorNumber = vectorNumber / hcf;

        Tile indexTile = from;
        while(!indexTile.equals(to)){
            try{
                char charToSearch = (char) (((indexTile.getLetter()-96) + vectorLetter)+96);
                indexTile = board.getTile(charToSearch, indexTile.getNumber() + vectorNumber);
            } catch (Exception e){
                return false;
            }
            if(indexTile.hasPiece() && !indexTile.equals(to)) return false;
        }
        return true;

    }

    private int hcf(int vectorLetter, int vectorNumber) {
        if (vectorNumber == 0) {
            return vectorLetter;
        }
        return Math.abs(hcf(vectorNumber, vectorLetter % vectorNumber));
    }


}
