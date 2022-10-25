package chess;

import chess.board.Board;
import utils.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Game> games;

    public Player(){
        this.games = new ArrayList<>();
    }

    public Board seeBoard(int index){
        return games.get(index).getBoard();
    }

    public void move(int index, Coordinates from, Coordinates to) throws Exception {
        //games.get(index).move(new Turn(to, from, this));
        games.get(index).move(from, to, this);
    }

    public void joinGame(Game game){
        games.add(game);
    }



}
