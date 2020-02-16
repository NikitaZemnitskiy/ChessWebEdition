package com.zemnitskiy.chess.domain.exceptions;

public class NotYourTurnException extends ChessException {
    public NotYourTurnException(String message) {
        super(message);
    }
}
