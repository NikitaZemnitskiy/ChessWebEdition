package com.zemnitskiy.chess.domain.figures;

import com.zemnitskiy.chess.domain.Color;
import com.zemnitskiy.chess.domain.Position;
import com.zemnitskiy.chess.domain.exceptions.WrongTurnException;


public class King extends Figure {

    public King(Color color) {
        super(color);
    }

    @Override
    public void isPossible(Position position1, Position position2, Figure[][] figures) {

        if (position1.getVertical() == position2.getVertical()+1 && position1.getHorizontal()==position2.getHorizontal()){
            return;
        }
        if (position1.getVertical() == position2.getVertical()-1 && position1.getHorizontal()==position2.getHorizontal()){
            return;
        }
        if (position1.getVertical() == position2.getVertical() && position1.getHorizontal()==position2.getHorizontal()+1){
            return;
        }
        if (position1.getVertical() == position2.getVertical() && position1.getHorizontal()==position2.getHorizontal()-1){
            return;
        }
        if (position1.getVertical() == position2.getVertical()+1 && position1.getHorizontal()==position2.getHorizontal()+1){
            return;
        }
        if (position1.getVertical() == position2.getVertical()+1 && position1.getHorizontal()==position2.getHorizontal()-1){
            return;
        }
        if (position1.getVertical() == position2.getVertical()-1 && position1.getHorizontal()==position2.getHorizontal()+1){
            return;
        }
        if (position1.getVertical() == position2.getVertical()-1 && position1.getHorizontal()==position2.getHorizontal()-1){
            return;
        }

        throw new WrongTurnException("King can't make this turn (from "+position1+" to "+position2);
    }

    @Override
    public String toString() {
        if(super.getColor() == Color.WHITE) {
            return "K";
        }
        return "k";
    }
}
