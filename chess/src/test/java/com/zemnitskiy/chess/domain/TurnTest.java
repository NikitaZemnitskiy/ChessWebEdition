package com.zemnitskiy.chess.domain;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TurnTest {

    @Test
    void isDiagonal() {
        Turn turn = new Turn(Position.fromString("a1"), Position.fromString("e5"));
        Turn turn2 = new Turn(Position.fromString("e5"), Position.fromString("a1"));
        assertTrue(turn.isDiagonal());
        assertTrue(turn2.isDiagonal());
    }
    @Test
    void notDiagonal() {
        Turn vert = new Turn(Position.fromString("a1"), Position.fromString("a2"));
        Turn hor = new Turn(Position.fromString("b1"), Position.fromString("d1"));
        Turn random = new Turn(Position.fromString("a1"), Position.fromString("f3"));
        assertFalse(vert.isDiagonal());
        assertFalse(hor.isDiagonal());
        assertFalse(random.isDiagonal());
    }

    @Test
    void isVertical() {
        Turn turn = new Turn(Position.fromString("d6"), Position.fromString("d2"));
        Turn turn2 = new Turn(Position.fromString("d2"), Position.fromString("d6"));
        assertTrue(turn.isVertical());
        assertTrue(turn2.isVertical());
    }

    @Test
    void notVertical() {
        Turn diagonal = new Turn(Position.fromString("a1"), Position.fromString("c3"));
        Turn hor = new Turn(Position.fromString("b1"), Position.fromString("d1"));
        Turn random = new Turn(Position.fromString("a1"), Position.fromString("f3"));
        assertFalse(diagonal.isVertical());
        assertFalse(hor.isVertical());
        assertFalse(random.isVertical());
    }

    @Test
    void isHorizontal() {
        Turn turn = new Turn(Position.fromString("b1"), Position.fromString("h1"));
        Turn turn2 = new Turn(Position.fromString("h1"), Position.fromString("b1"));
        assertTrue(turn.isHorizontal());
        assertTrue(turn2.isHorizontal());
    }

    @Test
    void notHorizontal() {
        Turn diagonal = new Turn(Position.fromString("a1"), Position.fromString("c3"));
        Turn vertical = new Turn(Position.fromString("b1"), Position.fromString("b5"));
        Turn random = new Turn(Position.fromString("a1"), Position.fromString("f3"));
        assertFalse(diagonal.isHorizontal());
        assertFalse(vertical.isHorizontal());
        assertFalse(random.isHorizontal());
    }


    @org.junit.jupiter.api.Test
    void testVerticalIteratorA1A8() {
        Position from = Position.fromString("a1");
        Position to = Position.fromString("a8");
        List<Position> expectedPositions = new ArrayList<>();
        expectedPositions.add(Position.fromString("a2"));
        expectedPositions.add(Position.fromString("a3"));
        expectedPositions.add(Position.fromString("a4"));
        expectedPositions.add(Position.fromString("a5"));
        expectedPositions.add(Position.fromString("a6"));
        expectedPositions.add(Position.fromString("a7"));
        expectedPositions.add(Position.fromString("a8"));
        List<Position> positions = new ArrayList<>();
        Turn turn = new Turn(from, to);
        for (Position p : turn) {
            positions.add(p);
        }
        assertEquals(expectedPositions,positions);
    }

    @org.junit.jupiter.api.Test
    void testHorizontalIteratorA1H1() {
        Position from = Position.fromString("a1");
        Position to = Position.fromString("h1");
        List<Position> expectedPositions = new ArrayList<>();
        expectedPositions.add(Position.fromString("b1"));
        expectedPositions.add(Position.fromString("c1"));
        expectedPositions.add(Position.fromString("d1"));
        expectedPositions.add(Position.fromString("e1"));
        expectedPositions.add(Position.fromString("f1"));
        expectedPositions.add(Position.fromString("g1"));
        expectedPositions.add(Position.fromString("h1"));
        List<Position> positions = new ArrayList<>();
        Turn turn = new Turn(from, to);
        for (Position p : turn) {
            positions.add(p);
        }
        assertEquals(expectedPositions,positions);
    }

    @org.junit.jupiter.api.Test
    void testDiagonalIteratorA1H8() {
        Position from = Position.fromString("a1");
        Position to = Position.fromString("h8");
        List<Position> expectedPositions = new ArrayList<>();
        expectedPositions.add(Position.fromString("b2"));
        expectedPositions.add(Position.fromString("c3"));
        expectedPositions.add(Position.fromString("d4"));
        expectedPositions.add(Position.fromString("e5"));
        expectedPositions.add(Position.fromString("f6"));
        expectedPositions.add(Position.fromString("g7"));
        expectedPositions.add(Position.fromString("h8"));
        List<Position> positions = new ArrayList<>();
        Turn turn = new Turn(from, to);
        for (Position p : turn) {
            positions.add(p);
        }
        assertEquals(expectedPositions,positions);
    }
}