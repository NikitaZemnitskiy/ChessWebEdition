package com.zemnitskiy.chess.domain.figures;

import com.zemnitskiy.chess.domain.Color;
import com.zemnitskiy.chess.domain.Position;
import com.zemnitskiy.chess.domain.exceptions.WrongTurnException;

public class Pawn extends Figure {
    boolean isTurnFirst = true;
    public Pawn(Color color) {
        super(color);
    }

    @Override
    public void isPossible(Position position1, Position position2, Figure[][] figures) {
        if(super.getColor() == Color.WHITE) {
          isWhitePossible(position1, position2, figures);
        }
        else {
           isBlackPossible(position1, position2, figures);
        }
    }

   private void isWhitePossible(Position position1, Position position2, Figure[][] figures){

       if (figures[position2.getVertical()][position2.getIntHorizontal()] == null) {
           if (position1.getVertical() + 1 == position2.getVertical()
                   && position1.getHorizontal() == position2.getHorizontal()) {
               isTurnFirst = false;
               return;
           }
           if (position1.getVertical() + 2 == position2.getVertical()
                   && position1.getHorizontal() == position2.getHorizontal()
                   && isTurnFirst
                   && figures[position1.getVertical() + 1][position1.getIntHorizontal()] == null) {
               isTurnFirst = false;
              return;
           }
           throw new WrongTurnException("Pawn can't make this turn (from "+position1+" to "+position2);
       } else if (position1.getVertical() + 1 == position2.getVertical()
               && position1.getHorizontal() == position2.getHorizontal() + 1) {
           isTurnFirst = false;
           return;
       } else if (position1.getVertical() + 1 == position2.getVertical()
               && position1.getHorizontal() == position2.getHorizontal() - 1) {
           isTurnFirst = false;
           return;
       }
       throw new WrongTurnException("Pawn can't make this turn (from "+position1+" to "+position2);
 }

 private void isBlackPossible(Position position1, Position position2, Figure[][] figures){

     if (figures[position2.getVertical()][position2.getIntHorizontal()] == null) {
         if (position1.getVertical() - 1 == position2.getVertical()
                 && position1.getHorizontal() == position2.getHorizontal()) {
             isTurnFirst = false;
             return;
         }

         if (position1.getVertical() - 2 == position2.getVertical()
                 && position1.getHorizontal() == position2.getHorizontal()
                 && isTurnFirst
                 && figures[position1.getVertical() - 1][position1.getIntHorizontal()] == null) {
             isTurnFirst = false;
             return;
         }
         throw new WrongTurnException("Pawn can't make this turn (from "+position1+" to "+position2);
     } else if (position1.getVertical() - 1 == position2.getVertical()
             && position1.getHorizontal() == position2.getHorizontal() + 1) {
         isTurnFirst = false;
         return;
     } else if (position1.getVertical() - 1 == position2.getVertical()
             && position1.getHorizontal() == position2.getHorizontal() - 1) {
         isTurnFirst = false;
         return;
     }
     throw new WrongTurnException("Pawn can't make this turn (from "+position1+" to "+position2);
 }

    @Override
    public String toString() {
        if(super.getColor() == Color.WHITE) {
            return "P";
        }
        return "p";
    }
}
