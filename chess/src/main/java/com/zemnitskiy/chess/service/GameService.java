package com.zemnitskiy.chess.service;

import com.zemnitskiy.chess.GameType;
import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Game;
import com.zemnitskiy.chess.domain.boardRep.StandartBoard;
import com.zemnitskiy.chess.entity.GameEntity;
import com.zemnitskiy.chess.entity.User;

public class GameService {

    GameEntity gameEntity;
    User whitePlayer;
    User blackPlayer;
    GameType gameType;

    public GameService(User whitePlayer, User blackPlayer, GameType gameType) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.gameType = gameType;
        this.gameEntity = createGameEntity();
    }

    public GameService(User whitePlayer, GameType gameType) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = whitePlayer;
        this.gameType = gameType;
        this.gameEntity = createGameEntity();
    }

    private GameEntity createGameEntity(){
        GameEntity gameEntity = new GameEntity
                (new Game(StandartBoard.getStandartBoard()), whitePlayer, blackPlayer);
        return gameEntity;
    }
}
