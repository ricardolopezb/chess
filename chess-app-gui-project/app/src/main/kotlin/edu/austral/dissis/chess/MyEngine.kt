package edu.austral.dissis.chess

import adapter.Adapter
import chess.Game
import chess.GameMode
import chess.Player
import edu.austral.dissis.chess.gui.*
import utils.Coordinates
import utils.exceptions.InvalidMoveException
import utils.exceptions.VictoryException

class MyEngine() : GameEngine {
    private val game: Game = Game(GameMode.CLASSIC)
    private val adapter: Adapter = Adapter()

    override fun applyMove(move: Move): MoveResult {
        val coords: List<Coordinates>
        coords = adapter.getCoordinatesFromMove(move)

        try{
            game.move(coords.get(0), coords.get(1), Player())
            val pieces = adapter.convertPieceToChessPiece(game.board.tiles)
            return NewGameState(pieces, if(game.isWhiteTurn) PlayerColor.WHITE else PlayerColor.BLACK)
        } catch (e: InvalidMoveException){
            return InvalidMove(e.message ?: fail("Name required"))
        } catch (e: VictoryException) {
            return GameOver(if( game.isWhiteTurn) PlayerColor.WHITE else PlayerColor.BLACK)

        }
    }
    fun fail(message: String): Nothing {
        throw IllegalArgumentException(message)
    }

    override fun init(): InitialState {
        val pieces = adapter.convertPieceToChessPiece(game.board.tiles); // gets?
        return InitialState(BoardSize(8,8),pieces, PlayerColor.WHITE);
    }


}