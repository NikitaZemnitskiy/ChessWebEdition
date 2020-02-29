package com.zemnitskiy.chess.domain.domain.figures;

import com.zemnitskiy.chess.domain.domain.Board;
import com.zemnitskiy.chess.domain.domain.Turn;
import com.zemnitskiy.chess.domain.domain.exceptions.ChessException;
import com.zemnitskiy.chess.domain.domain.exceptions.WrongTurnException;

public class Queen extends Figure {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public void isPossible(Turn turn, Board board) {
        int exceptionCount = 0;
        Castle castle = new Castle(color);
        Bishop bishop = new Bishop(color);
        try{
            bishop.isPossible(turn, board);
        }
        catch (ChessException e){
            exceptionCount++;
        }
        try{
            castle.isPossible(turn, board);
        }
        catch (ChessException e){
            exceptionCount++;
        }
        if (exceptionCount>=2){
            throw new WrongTurnException("Queen can't make this " + turn);
        }
    }
    @Override
    public String toString() {
        if(super.getColor() == Color.WHITE) {
            return "Q";
        }
        return "q";
    }
}
