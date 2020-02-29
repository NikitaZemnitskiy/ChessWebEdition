package com.zemnitskiy.chess.domain;

import com.zemnitskiy.chess.domain.exceptions.ImpossiblePositionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {
    Logger log = LoggerFactory.getLogger(getClass());

    @org.junit.jupiter.api.Test
    void TestToStringFromCoordinates() {

        Position p = Position.fromCoordinates(0,1);
        log.debug(p.toString());
        assertEquals("a2", p.toString());

    }

    @org.junit.jupiter.api.Test
    void TestToStringFromString() {
        Position p = Position.fromString("a2");
        log.debug(p.toString());
        assertEquals("a2", p.toString());
    }


    @org.junit.jupiter.api.Test
    void validPositionCreator() {
        for(int i = 0; i<8; i++){
            for(int n = 0; n<8; n++){
                Position.fromCoordinates(i,n);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void invalidPositionCreator1() {
        Throwable thrown = assertThrows(ImpossiblePositionException.class, () -> {
           Position.fromCoordinates(8,2);
        });
        assertNotNull(thrown.getMessage());
    }

    @org.junit.jupiter.api.Test
    void invalidPositionCreator2() {
        Throwable thrown = assertThrows(ImpossiblePositionException.class, () -> {
            Position.fromCoordinates(-1,2);
        });
        assertNotNull(thrown.getMessage());
    }

    @org.junit.jupiter.api.Test
    void validPositionCreatorFromString() {
        for(int i = 0; i<8; i++){
            for(int n = 0; n<8; n++){
                char hor = (char) ('a' + n);
                int vert = i+1;
                Position.fromString(hor + "" + vert);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void inValidPositionCreatorFromString() {
        Throwable thrown = assertThrows(ImpossiblePositionException.class, () -> {
            Position.fromString("h9");
        });
        assertNotNull(thrown.getMessage());
    }

    @org.junit.jupiter.api.Test
    void inValidPositionCreatorFromString2() {
        Throwable thrown = assertThrows(ImpossiblePositionException.class, () -> {
            Position.fromString("k5");
        });
        assertNotNull(thrown.getMessage());
    }

    @org.junit.jupiter.api.Test
    void inValidPositionCreatorFromString3() {
        Throwable thrown = assertThrows(ImpossiblePositionException.class, () -> {
            Position.fromString("h0");
        });
        assertNotNull(thrown.getMessage());
    }

    @org.junit.jupiter.api.Test
    void inValidPositionCreatorFromString4() {
        Throwable thrown = assertThrows(ImpossiblePositionException.class, () -> {
            Position.fromString("nikita");
        });
        assertNotNull(thrown.getMessage());
    }

    @org.junit.jupiter.api.Test
    void inValidPositionCreatorFromString5() {
        Throwable thrown = assertThrows(ImpossiblePositionException.class, () -> {
            Position.fromString("a");
        });
        assertNotNull(thrown.getMessage());
    }

    @org.junit.jupiter.api.Test
    void inValidPositionCreatorFromString6() {
        Throwable thrown = assertThrows(ImpossiblePositionException.class, () -> {
            Position.fromString("100");
        });
        assertNotNull(thrown.getMessage());
    }

    @org.junit.jupiter.api.Test
    void inValidPositionCreatorFromString7() {
        Throwable thrown = assertThrows(ImpossiblePositionException.class, () -> {
            Position.fromString("");
        });
        assertNotNull(thrown.getMessage());
    }

    @org.junit.jupiter.api.Test
    void MoveBy() {
        Throwable thrown = assertThrows(ImpossiblePositionException.class, () -> {
            Position.fromString("");
        });
        assertNotNull(thrown.getMessage());
    }





    @org.junit.jupiter.api.Test
    void turnTo() {
    }

    @org.junit.jupiter.api.Test
    void moveBy() {
    }

    @org.junit.jupiter.api.Test
    void testToString() {
    }
}