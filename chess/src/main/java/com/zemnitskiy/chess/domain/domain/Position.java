package com.zemnitskiy.chess.domain.domain;

import com.zemnitskiy.chess.domain.domain.exceptions.ImpossiblePositionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;


public class Position {
    Logger log = LoggerFactory.getLogger(Position.class);
    public final int x, y;

  // We must put here coordinates in range 0..7
    public static Position fromCoordinates(int x, int y) {
        return new Position(x,y);
    }

    public static Position fromString(String coordinatesAsString) {
        if (coordinatesAsString.length() < 2){
            throw new ImpossiblePositionException(coordinatesAsString + " not a move");
        }

        int x = coordinatesAsString.charAt(0) - 'a';
        int y = coordinatesAsString.charAt(1) - '1';

        return new Position(x,y);
    }

    Position (int x, int y){
        validate(x);
        validate(y);
        this.x = x;
        this.y = y;
        log.debug("Position created x - {}, y - {}", x, y);
    }

    void validate(int pos){
        if(pos < 0 || pos >= 8)
        throw new ImpossiblePositionException("Position " + pos + " must me in range 0..7");
    }

    public Turn turnTo(Position p) {
        return new Turn(this, p);
    }

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
