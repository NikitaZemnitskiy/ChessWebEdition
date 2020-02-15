package com.zemnitskiy.chess.domain;

public class Turn {
    private Position position1;
    private Position position2;

    public Turn(Position position1, Position position2) {
        this.position1 = position1;
        this.position2 = position2;
    }
}
