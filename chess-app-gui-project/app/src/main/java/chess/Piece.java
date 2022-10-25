package chess;

import java.util.Objects;

public class Piece {
    private static int idCounter = 0;
    private final String id;
    private PieceName name;
    private final Color color;

    public Piece(PieceName name, Color color) {
        this.id  = String.valueOf(idCounter++);
        this.name = name;
        this.color = color;
    }

    public PieceName getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public String toString(){
        return this.name + " - " + this.color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece piece)) return false;
        return getName() == piece.getName() && getColor() == piece.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getColor());
    }

    public String getId(){
        return this.id;
    }

    public void setName(PieceName promoteTo) {
        this.name = promoteTo;
    }
}
