package chess;

public class Turn {
    private Tile to;
    private Tile from;
    private Player player;

    public Turn(Tile to, Tile from, Player player) {
        this.to = to;
        this.from = from;
        this.player = player;
    }

    public Tile getTo() {
        return to;
    }

    public void setTo(Tile to) {
        this.to = to;
    }

    public Tile getFrom() {
        return from;
    }

    public void setFrom(Tile from) {
        this.from = from;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


}
