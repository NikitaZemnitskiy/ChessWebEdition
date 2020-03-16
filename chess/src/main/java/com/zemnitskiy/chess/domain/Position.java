package com.zemnitskiy.chess.domain;

import com.zemnitskiy.chess.domain.exceptions.ImpossiblePositionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/*
This class contains some position on the bord. It can be 2 number in range 0..7(x and y)
coordinates or it can be character in range a..h and number in range 1..8(like a chess
bord coordinates)
*/

public class Position {
    Logger log = LoggerFactory.getLogger(Position.class);
    public final int x, y;

    //This method takes coordinates in range 0..7

    public static Position fromCoordinates(int x, int y) {
        return new Position(x,y);
    }

    // This method takes coordinates in format "e2" and transform them in Position(x,y)
    public static Position fromString(String coordinatesAsString) {
        if (coordinatesAsString.length() < 2){
            throw new ImpossiblePositionException(coordinatesAsString + " not a move");
        }

        int x = coordinatesAsString.charAt(0) - 'a';
        int y = coordinatesAsString.charAt(1) - '1';

        return new Position(x,y);
    }

    public Position (int x, int y){
        validate(x);
        validate(y);
        this.x = x;
        this.y = y;
    }
//throw exception if this position is impossible
    void validate(int pos){
        if(pos < 0 || pos >= 8)
        throw new ImpossiblePositionException("Position " + pos + " must me in range 0..7");
    }

    public Turn turnTo(Position p) {
        return new Turn(this, p);
    }

    //This method takes delta of x and y coordinates and return New position (current position + this delta)
    public Position moveBy(int dx, int dy) {
        return new Position(this.x + dx, this.y + dy);
    }

    @Override
    public String toString() {
        char x = (char)((int)'a' + this.x);
        int y = this.y+1;
        return "" + x + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
