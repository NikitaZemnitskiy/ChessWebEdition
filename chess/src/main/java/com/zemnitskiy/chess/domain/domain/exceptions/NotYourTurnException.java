package com.zemnitskiy.chess.domain.domain.exceptions;

public class NotYourTurnException extends ChessException {
    public NotYourTurnException(String message) {
        super(message);
    }
}
