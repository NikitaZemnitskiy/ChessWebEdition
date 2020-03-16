package com.zemnitskiy.chess.domain.exceptions;

public class WrongTurnException extends ChessException {
    public WrongTurnException(String message) {
        super(message);
    }
}
