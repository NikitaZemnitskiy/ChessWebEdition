package com.zemnitskiy.chess.domain.figures;


import com.zemnitskiy.chess.domain.Color;
import com.zemnitskiy.chess.domain.Position;
import com.zemnitskiy.chess.domain.exceptions.WrongTurnException;

public class Rook extends Figure {
    public Rook(Color color) {
        super(color);
    }

    @Override
    public void isPossible(Position position1, Position position2, Figure[][] figures) {
        if (position1.getVertical() == position2.getVertical()) {
            if (position1.getIntHorizontal() > position2.getIntHorizontal()) {
                for (int i = position2.getIntHorizontal() + 1; i < position1.getIntHorizontal(); i++) {
                    if (figures[position1.getVertical()][i] != null) {
                        throw new WrongTurnException("Rook can't make this turn (from "+position1+" to "+position2);
                    }
                }
                return;
            } else {
                for (int i = position1.getIntHorizontal() + 1; i < position2.getIntHorizontal(); i++) {
                    if (figures[position1.getVertical()][i] != null) {
                        throw new WrongTurnException("Rook can't make this turn (from "+position1+" to "+position2);

                    }
                }
                return;
            }
        }


        if (position1.getHorizontal() == position2.getHorizontal()) {
            if (position1.getVertical() > position2.getVertical()) {
                for (int i = position2.getVertical()+1; i < position1.getVertical(); i++) {
                    if (figures[i][position1.getIntHorizontal()] != null) {
                        throw new WrongTurnException("Rook can't make this turn (from "+position1+" to "+position2);
                    }
                }
                return;
            } else {
                for (int i = position1.getVertical()+1; i < position2.getVertical(); i++) {
                    if (figures[i][position1.getIntHorizontal()] != null) {
                        throw new WrongTurnException("Rook can't make this turn (from "+position1+" to "+position2);
                    }
                }
                return;
            }
        }
        throw new WrongTurnException("Rook can't make this turn (from "+position1+" to "+position2);
    }
    @Override
    public String toString() {
        if(super.getColor() == Color.WHITE) {
            return "R";
        }
        return "r";
    }
}

