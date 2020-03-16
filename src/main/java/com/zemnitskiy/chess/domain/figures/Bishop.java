package com.zemnitskiy.chess.domain.figures;

import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Turn;
import com.zemnitskiy.chess.domain.exceptions.WrongTurnException;

public class Bishop  extends Figure {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public void isPossible(Turn turn, Board board) {
        checkFigureBetweenTurn(board, turn);
        if(!turn.isDiagonal()){
            throw new WrongTurnException("Bishop can't make this " + turn);
        }
    }

    private void checkFigureBetweenTurn(Board board, Turn t){
        Figure figureAtDestination = board.figures[t.to.x][t.to.y];
        Figure firstOnPath = t.firstOnPath(board);
        System.out.println("From " + t.from.toString() + " to " +t.to.toString());

        if(firstOnPath == figureAtDestination){
            return;
        }
        throw new WrongTurnException("Bishop can't jump over other figure");
    }

    @Override
    public String toString() {
        if(super.getColor() == Color.WHITE) {
            return "B";
        }
        return "b";
    }
}
