package com.zemnitskiy.chess.domain.exceptions;

public class ImpossiblePositionException extends ChessException {
    public ImpossiblePositionException(String message) {
        super(message);
    }
}
