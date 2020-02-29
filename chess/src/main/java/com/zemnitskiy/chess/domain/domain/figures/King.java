package com.zemnitskiy.chess.domain.domain.figures;

import com.zemnitskiy.chess.domain.domain.Board;
import com.zemnitskiy.chess.domain.domain.Turn;
import com.zemnitskiy.chess.domain.domain.exceptions.WrongTurnException;

public class King extends Figure {

    public King(Color color) {
        super(color);
    }

    @Override
    public void isPossible(Turn turn, Board board) {
        checkFigureBetweenTurn(turn, board);
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
    @Override
    public String toString() {
        if(super.getColor() == Color.WHITE) {
            return "K";
        }
        return "k";
    }
}
