package com.zemnitskiy.chess.domain.figures;


import com.zemnitskiy.chess.domain.Color;
import com.zemnitskiy.chess.domain.Position;
import com.zemnitskiy.chess.domain.exceptions.WrongTurnException;

public class Knight extends Figure{

    public Knight(Color color) {
        super(color);
    }

    @Override
    public void isPossible(Position position1, Position position2, Figure[][] figures) {
        if (position1.getVertical() == position2.getVertical() + 1 && position1.getHorizontal() == position2.getHorizontal() + 2) {
            return;
        }
        if (position1.getVertical() == position2.getVertical() + 2 && position1.getHorizontal() == position2.getHorizontal() + 1) {
            return;
        }
        if (position1.getVertical() == position2.getVertical() + 2 && position1.getHorizontal() == position2.getHorizontal() - 1) {
            return;
        }
        if (position1.getVertical() == position2.getVertical() + 1 && position1.getHorizontal() == position2.getHorizontal() - 2) {
            return;
        }
        if (position1.getVertical() == position2.getVertical() - 1 && position1.getHorizontal() == position2.getHorizontal() - 2) {
            return;
        }
        if (position1.getVertical() == position2.getVertical() - 2 && position1.getHorizontal() == position2.getHorizontal() - 1) {
            return;
        }
        if (position1.getVertical() == position2.getVertical() - 2 && position1.getHorizontal() == position2.getHorizontal() + 1) {
            return;
        }
        if (position1.getVertical() == position2.getVertical() - 1 && position1.getHorizontal() == position2.getHorizontal() + 2) {
            return;
        }
        throw new WrongTurnException("Knight can't make this turn (from "+position1+" to "+position2);
    }
    @Override
    public String toString() {
        if(super.getColor() == Color.WHITE) {
            return "N";
        }
        return "n";
    }
}
