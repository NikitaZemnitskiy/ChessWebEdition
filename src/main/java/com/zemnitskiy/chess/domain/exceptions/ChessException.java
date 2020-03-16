package com.zemnitskiy.chess.domain.exceptions;

public abstract class ChessException extends RuntimeException {

    public ChessException(String message) {
        super(message);
    }

}
