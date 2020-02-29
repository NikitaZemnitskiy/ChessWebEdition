package com.zemnitskiy.chess.domain.domain.exceptions;

public class ImpossiblePositionException extends ChessException {
    public ImpossiblePositionException(String message) {
        super(message);
    }
}
