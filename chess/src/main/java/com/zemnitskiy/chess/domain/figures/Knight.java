package com.zemnitskiy.chess.domain.figures;


import com.zemnitskiy.chess.domain.Color;
import com.zemnitskiy.chess.domain.Position;

public class Knight extends Figure{

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean isPossible(Position position1, Position position2, Figure[][] figures) {
        if (position1.getVertical() == position2.getVertical() + 1 && position1.getHorizontal() == position2.getHorizontal() + 2) {
            return true;
        }
        if (position1.getVertical() == position2.getVertical() + 2 && position1.getHorizontal() == position2.getHorizontal() + 1) {
            return true;
        }
        if (position1.getVertical() == position2.getVertical() + 2 && position1.getHorizontal() == position2.getHorizontal() - 1) {
            return true;
        }
        if (position1.getVertical() == position2.getVertical() + 1 && position1.getHorizontal() == position2.getHorizontal() - 2) {
            return true;
        }
        if (position1.getVertical() == position2.getVertical() - 1 && position1.getHorizontal() == position2.getHorizontal() - 2) {
            return true;
        }
        if (position1.getVertical() == position2.getVertical() - 2 && position1.getHorizontal() == position2.getHorizontal() - 1) {
            return true;
        }
        if (position1.getVertical() == position2.getVertical() - 2 && position1.getHorizontal() == position2.getHorizontal() + 1) {
            return true;
        }
        if (position1.getVertical() == position2.getVertical() - 1 && position1.getHorizontal() == position2.getHorizontal() + 2) {
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        if(super.getColor() == Color.WHITE) {
            return "N";
        }
        return "n";
    }
}
