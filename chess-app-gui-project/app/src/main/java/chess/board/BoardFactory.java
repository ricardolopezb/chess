package chess.board;

import chess.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardFactory {
    private BoardCreator boardCreator;

    public BoardFactory(GameMode mode) {
        switch (mode){
            case CLASSIC -> {
                boardCreator = new ClassicBoardCreator();
            }
            case RETIRED -> {
                boardCreator = new ClassicBoardCreator();
            }
            case BERLIN -> {
                boardCreator = new ClassicBoardCreator();
            }
        }
    }

    public Board createBoard(){
        return boardCreator.create();
    }


}
