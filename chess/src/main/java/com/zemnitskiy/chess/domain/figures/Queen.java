package com.zemnitskiy.chess.domain.figures;


import com.zemnitskiy.chess.domain.Color;
import com.zemnitskiy.chess.domain.Position;
import com.zemnitskiy.chess.domain.exceptions.ChessException;
import com.zemnitskiy.chess.domain.exceptions.WrongTurnException;

public class Queen extends Figure{
    public Queen(Color color) {
        super(color);
    }

    @Override
    public void isPossible(Position position1, Position position2, Figure[][] figures) {
        int exceptionCount = 0;
        Rook rook = new Rook(Color.WHITE);
        Bishop bishop = new Bishop(Color.WHITE);
        try{
            bishop.isPossible(position1,position2,figures);
        }
        catch (ChessException e){
            exceptionCount++;
        }
        try{
            rook.isPossible(position1,position2,figures);
        }
        catch (ChessException e){
            exceptionCount++;
        }
        if (exceptionCount>=2){
            throw new WrongTurnException("Queen can't make this turn (from "+position1+" to "+position2);
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
