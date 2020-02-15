package com.zemnitskiy.chess.domain.figures;

import com.zemnitskiy.chess.domain.Color;
import com.zemnitskiy.chess.domain.Position;

public class Pawn extends Figure {
    boolean isTurnFirst = true;
    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isPossible(Position position1, Position position2, Figure[][] figures) {
        if(super.getColor() == Color.WHITE) {
          return isWhitePossible(position1, position2, figures);
        }
        else {
            return isBlackPossible(position1, position2, figures);
        }
    }

   boolean isWhitePossible(Position position1, Position position2, Figure[][] figures){

       if (figures[position2.getVertical()][position2.getIntHorizontal()] == null) {
           if (position1.getVertical() + 1 == position2.getVertical()
                   && position1.getHorizontal() == position2.getHorizontal()) {
               isTurnFirst = false;
               return true;
           }
           if (position1.getVertical() + 2 == position2.getVertical()
                   && position1.getHorizontal() == position2.getHorizontal()
                   && isTurnFirst
                   && figures[position1.getVertical() + 1][position1.getIntHorizontal()] == null) {
               isTurnFirst = false;
               return true;
           }
           return false;
       } else if (position1.getVertical() + 1 == position2.getVertical()
               && position1.getHorizontal() == position2.getHorizontal() + 1) {
           isTurnFirst = false;
           return true;
       } else if (position1.getVertical() + 1 == position2.getVertical()
               && position1.getHorizontal() == position2.getHorizontal() - 1) {
           isTurnFirst = false;
           return true;
       }
       return false;
 }

 private boolean isBlackPossible(Position position1, Position position2, Figure[][] figures){

     if (figures[position2.getVertical()][position2.getIntHorizontal()] == null) {
         if (position1.getVertical() - 1 == position2.getVertical()
                 && position1.getHorizontal() == position2.getHorizontal()) {
             isTurnFirst = false;
             return true;
         }

         if (position1.getVertical() - 2 == position2.getVertical()
                 && position1.getHorizontal() == position2.getHorizontal()
                 && isTurnFirst
                 && figures[position1.getVertical() - 1][position1.getIntHorizontal()] == null) {
             isTurnFirst = false;
             return true;
         }
         return false;
     } else if (position1.getVertical() - 1 == position2.getVertical()
             && position1.getHorizontal() == position2.getHorizontal() + 1) {
         isTurnFirst = false;
         return true;
     } else if (position1.getVertical() - 1 == position2.getVertical()
             && position1.getHorizontal() == position2.getHorizontal() - 1) {
         isTurnFirst = false;
         return true;
     }
     return false;
 }

    @Override
    public String toString() {
        if(super.getColor() == Color.WHITE) {
            return "P";
        }
        return "p";
    }
}
