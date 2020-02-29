package com.zemnitskiy.chess.domain.domain.figures;

import com.zemnitskiy.chess.domain.domain.Board;
import com.zemnitskiy.chess.domain.domain.Turn;
import com.zemnitskiy.chess.domain.domain.exceptions.WrongTurnException;

public class Castle extends Figure {

    public Castle(Color color) {
        super(color);
    }

    @Override
    public void isPossible(Turn turn, Board board) {
        checkFigureBetweenTurn(turn, board);
        if(turn.isHorizontal() || turn.isVertical()){
            return;
        }
        throw new WrongTurnException("Castle can't make this " + turn);
    }

    private void checkFigureBetweenTurn(Turn t, Board board){
        Figure figureAtDestination = board.figures[t.to.x][t.to.y];
        Figure firstOnPath = t.firstOnPath(board);
        if(firstOnPath != figureAtDestination){
            throw new WrongTurnException("Castle can't jump over other figure");
        }

    }

    @Override
    public String toString() {
        if(super.getColor() == Color.WHITE) {
            return "R";
        }
        return "r";
    }
}
