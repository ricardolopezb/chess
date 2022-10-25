package chess.board;

import chess.Tile;

import java.util.ArrayList;
import java.util.List;

public interface BoardCreator {
    Board create();
    default List<Tile> generateTiles(int x, int y){
        List<Tile> tiles = new ArrayList<>(x*y);
        // [a1, b1, c1]
        for (int number = 1; number <= x ; number++) {
            for (int letter = 97; letter < 97+y; letter++) {
                Tile tile = new Tile((char)letter, number);
                tiles.add(tile);
            }
        }
        return tiles;
    }

}
