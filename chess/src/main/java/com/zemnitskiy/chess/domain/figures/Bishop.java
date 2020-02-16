package com.zemnitskiy.chess.domain.figures;


import com.zemnitskiy.chess.domain.Color;
import com.zemnitskiy.chess.domain.Position;
import com.zemnitskiy.chess.domain.exceptions.WrongTurnException;

public class Bishop extends Figure{
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public void isPossible(Position position1, Position position2, Figure[][] figures) {
        int verticalAbs = Math.abs(position1.getVertical()-position2.getVertical());
        int horizontalAbs = Math.abs(position1.getIntHorizontal()-position2.getIntHorizontal());
        if(verticalAbs == horizontalAbs){
            //RightUp
           if(position1.getVertical() < position2.getVertical()
                   && position1.getIntHorizontal() < position2.getIntHorizontal()){
                for(int i = 1; i<verticalAbs; i++){
                    if(figures[position1.getVertical()+i][position1.getIntHorizontal()+i] != null){
                       throw new WrongTurnException("Bishop can't make this turn (from "+position1+" to "+position2);
                    }
                }
                return;
           }
           //RightDown
           else if(position1.getVertical() > position2.getVertical()
                   && position1.getIntHorizontal()< position2.getIntHorizontal()){
               for(int i = 1; i<verticalAbs; i++){
                   if(figures[position1.getVertical()-i][position1.getIntHorizontal()+i] != null){
                       throw new WrongTurnException("Bishop can't make this turn (from "+position1+" to "+position2);
                   }
               }
                return;
           }

           //LeftDown
           else if(position1.getVertical() > position2.getVertical()
                   && position1.getIntHorizontal() > position2.getIntHorizontal()){
               for(int i = 1; i<verticalAbs; i++){
                   if(figures[position1.getVertical()-i][position1.getIntHorizontal()-i] != null){
                       throw new WrongTurnException("Bishop can't make this turn (from "+position1+" to "+position2);
                   }
               }
                return;
           }

           //LeftUp
           else if(position1.getVertical() < position2.getVertical()
                   && position1.getIntHorizontal() > position2.getIntHorizontal()){
               for(int i = 1; i<verticalAbs; i++){
                   if(figures[position1.getVertical()+i][position1.getIntHorizontal()-i] != null){
                       throw new WrongTurnException("Bishop can't make this turn (from "+position1+" to "+position2);
                   }
               }
                return;
           }
       }
        throw new WrongTurnException("Bishop can't make this turn (from "+position1+" to "+position2);
    }
    @Override
    public String toString() {
        if(super.getColor() == Color.WHITE) {
            return "B";
        }
        return "b";
    }
}
