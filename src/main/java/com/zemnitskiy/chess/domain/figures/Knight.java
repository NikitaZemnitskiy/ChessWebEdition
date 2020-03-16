package com.zemnitskiy.chess.domain.figures;

import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Turn;
import com.zemnitskiy.chess.domain.exceptions.WrongTurnException;

public class Knight extends Figure {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public void isPossible(Turn turn, Board board) {
        int dx = Math.abs(turn.dx);
        int dy = Math.abs(turn.dy);
        if((dx == 2 && dy == 1) || ((dx == 1 && dy == 2))){
            return;
        }
        throw new WrongTurnException("Knight can't make this " + turn);
    }

    @Override
    public String toString() {
        if(super.getColor() == Color.WHITE) {
            return "N";
        }
        return "n";
    }

}
