package com.zemnitskiy.chess.domain.figures;

import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Turn;

public abstract class Figure {
    public final Color color;


    public Figure(Color color) {
        this.color = color;
    }

    public abstract void isPossible(Turn turn, Board board);

    public Color getColor() {
        return color;
    }
}
