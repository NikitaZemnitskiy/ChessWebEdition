package com.zemnitskiy.chess.domain.figures;


import com.zemnitskiy.chess.domain.Color;
import com.zemnitskiy.chess.domain.Position;

public class Bishop extends Figure{
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean isPossible(Position position1, Position position2, Figure[][] figures) {
        int verticalAbs = Math.abs(position1.getVertical()-position2.getVertical());
        int horizontalAbs = Math.abs(position1.getIntHorizontal()-position2.getIntHorizontal());
        if(verticalAbs == horizontalAbs){
            //RightUp
           if(position1.getVertical() < position2.getVertical()
                   && position1.getIntHorizontal() < position2.getIntHorizontal()){
                for(int i = 1; i<verticalAbs; i++){
                    if(figures[position1.getVertical()+i][position1.getIntHorizontal()+i] != null){
                        return false;
                    }
                }
                return true;
           }
           //RightDown
           else if(position1.getVertical() > position2.getVertical()
                   && position1.getIntHorizontal()< position2.getIntHorizontal()){
               for(int i = 1; i<verticalAbs; i++){
                   if(figures[position1.getVertical()-i][position1.getIntHorizontal()+i] != null){
                       return false;
                   }
               }
               return true;
           }

           //LeftDown
           else if(position1.getVertical() > position2.getVertical()
                   && position1.getIntHorizontal() > position2.getIntHorizontal()){
               for(int i = 1; i<verticalAbs; i++){
                   if(figures[position1.getVertical()-i][position1.getIntHorizontal()-i] != null){
                       return false;
                   }
               }
               return true;
           }

           //LeftUp
           else if(position1.getVertical() < position2.getVertical()
                   && position1.getIntHorizontal() > position2.getIntHorizontal()){
               for(int i = 1; i<verticalAbs; i++){
                   if(figures[position1.getVertical()+i][position1.getIntHorizontal()-i] != null){
                       return false;
                   }
               }
               return true;
           }
       }
        return false;
    }
    @Override
    public String toString() {
        if(super.getColor() == Color.WHITE) {
            return "B";
        }
        return "b";
    }
}
