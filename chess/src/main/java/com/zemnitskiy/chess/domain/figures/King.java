package com.zemnitskiy.chess.domain.figures;

import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Position;
import com.zemnitskiy.chess.domain.Turn;
import com.zemnitskiy.chess.domain.exceptions.WrongTurnException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class King extends Figure {
    private final Logger log = LoggerFactory.getLogger(King.class);
    public King(Color color) {
        super(color);
    }
    private final Set<Turn> castlingTurns = Set.of(new Turn(Position.fromString("e1"), Position.fromString("c1"))
            , new Turn(Position.fromString("e1"), Position.fromString("g1"))
            , new Turn(Position.fromString("e8"), Position.fromString("g8"))
            , new Turn(Position.fromString("e8"), Position.fromString("c8"))
    );
    @Override
    public void isPossible(Turn turn, Board board) {
        checkFigureBetweenTurn(turn, board);
        if(castlingTurns.contains(turn)){
            makeCastling(turn,board);
            return;
        }
       if (maxOneCell(turn.dx) && maxOneCell(turn.dy)) return;
       throw new WrongTurnException("King can't make this " + turn);
    }

    private void checkFigureBetweenTurn(Turn t, Board board){
        Figure figureAtDestination = board.figures[t.to.x][t.to.y];
        Figure firstOnPath = t.firstOnPath(board);
        System.out.println("From " + t.from.toString() + " to " +t.to.toString());

        if(firstOnPath != figureAtDestination){
            throw new WrongTurnException("King can't jump over other figure");
        }

    }

    private boolean maxOneCell(int coord) {
        return coord <= 1 && coord >= -1;
    }
    private void makeCastling(Turn turn,Board board){
        if(isPossibleShortCastling(turn,board) || isPossibleLongCastling(turn,board)){
            return;
        }
        throw new WrongTurnException("You can't make castling now");
    }
    private boolean isPossibleLongCastling(Turn turn,Board board){

        if(color == Color.WHITE
                && turn.to.x == 2
                && board.figures[0][0] != null
                && board.figures[0][0].toString().equals("R")
                && board.figures[1][0] == null){
            board.figures[3][0] = board.figures[0][0];
            board.figures[0][0] = null;
            log.debug("Long castling made");
            return true;
        }
        if(color == Color.BLACK
                && turn.to.x == 2
                && board.figures[0][7] != null
                && board.figures[0][7].toString().equals("r")
                && board.figures[1][7] == null){
            board.figures[3][7] = board.figures[0][7];
            board.figures[0][7] = null;
            log.debug("Long castling made");
            return true;
        }

        return false;
    }
    private boolean isPossibleShortCastling(Turn turn,Board board){
        if(color == Color.WHITE
                && turn.to.x == 6
                && board.figures[7][0] != null
                && board.figures[7][0].toString().equals("R")){
            board.figures[5][0] = board.figures[7][0];
            board.figures[7][0] = null;
            log.debug("Short castling made");
            return true;
        }
        if(color == Color.BLACK
                && turn.to.x == 6
                && board.figures[7][7] != null
                && board.figures[7][7].toString().equals("r")){
            board.figures[5][7] = board.figures[7][7];
            board.figures[7][7] = null;
            log.debug("Short castling made");
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if(super.getColor() == Color.WHITE) {
            return "K";
        }
        return "k";
    }
}
