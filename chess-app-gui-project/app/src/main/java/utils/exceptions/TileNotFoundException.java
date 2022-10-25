package utils.exceptions;

public class TileNotFoundException extends Exception{
    public TileNotFoundException() {
        super("You got out of the board dummy");
    }
}
