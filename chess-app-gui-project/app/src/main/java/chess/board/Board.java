package chess.board;


import chess.Color;
import chess.Piece;
import chess.PieceName;
import chess.Tile;
import utils.Coordinates;
import utils.exceptions.TileNotFoundException;
import utils.exceptions.TileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {
    private List<Tile> tiles;

    public Board(List<Tile> tiles){
        this.tiles = tiles;
    }

    public void printBoard(){
        System.out.println("**************************** BOARD **************************\n");
        for (Tile tile : tiles) {
            System.out.println(tile.toString());
        }
        System.out.println("***************************************************************\n");

    }

    public Optional<List<Tile>> getTilesWithPiece(Piece target){
        List<Tile> result = new ArrayList<>();
        for (Tile tile : tiles) {
            if(tile.hasPiece() && target.equals(tile.getPiece())){
                result.add(tile);
            }
        }
        if(result.isEmpty()) return Optional.empty();
        else return Optional.of(result);
    }

    public boolean tileExists(Tile targetTile){
        for (Tile tile : tiles) {
            if(tile.equals(targetTile)) return true;
        }
        return false;
    }

    public Tile getTile(Coordinates coord) throws TileNotFoundException {
        return getTile(coord.getLetter(), coord.getNumber());
    }

    public Tile getTile(char letter, int number) throws TileNotFoundException {
        for (Tile tile : tiles) {
            if(tile.getLetter() == letter && tile.getNumber() == number) return tile;
        }
        throw new TileNotFoundException();
    }

    public List<Tile> getTiles() {
        return this.tiles;
    }


    public void movePiece(Tile from, Tile to) {
        Piece movedPiece = from.getPiece();
        from.emptyTile();
        to.insertPiece(movedPiece);
    }

    public Tile getKingTile(Color enemyColor) {
        for (Tile tile : tiles) {
            if(tile.hasPiece() && tile.getPiece().getColor() == enemyColor && tile.getPiece().getName() == PieceName.KING){
                return tile;
            }
        }
        return null;

    }
}
