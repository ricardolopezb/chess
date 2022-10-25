package chess;

import java.util.Objects;

public class Tile {
    private final char letter;
    private final int number;
    private Piece piece;
    private boolean hasChanged;

    public Tile(char letter, int number) {
        this.letter = letter;
        this.number = number;
        this.hasChanged = false;
    }

    public char getLetter() {
        return letter;
    }

    public int getNumber() {
        return number;
    }

    public Piece getPiece() {
        return piece;
    }

    public String toString(){
        return String.valueOf(this.letter) + this.number + (piece != null ? piece.toString() : "-");
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean hasPiece(){
        return this.piece!=null;
    }

    public boolean hasChanged() {
        return hasChanged;
    }

    public void setHasChanged(boolean hasChanged) {
        this.hasChanged = hasChanged;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && number == tile.number && Objects.equals(piece, tile.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, number, piece);
    }

    public void emptyTile() {
        setPiece(null);
    }

    public void insertPiece(Piece movedPiece) {
        setPiece(movedPiece);
    }
}
