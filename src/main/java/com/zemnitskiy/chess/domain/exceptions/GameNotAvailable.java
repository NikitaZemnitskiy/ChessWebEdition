package com.zemnitskiy.chess.domain.exceptions;

public class GameNotAvailable extends ChessException {
    public GameNotAvailable(String message) {
        super(message);
    }
}
