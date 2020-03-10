package com.zemnitskiy.chess.service;

import com.zemnitskiy.chess.GameStatus;
import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Game;
import com.zemnitskiy.chess.domain.Turn;
import com.zemnitskiy.chess.domain.exceptions.GameNotAvailable;
import com.zemnitskiy.chess.domain.exceptions.NotYourTurnException;
import com.zemnitskiy.chess.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GameService {
    Logger log = LoggerFactory.getLogger(GameService.class);

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TurnRepository turnRepository;

    Map<Integer, GameEntity> gameEntityByGameId = new ConcurrentHashMap<>();
    Map<Integer, GameEntity> gameEntityByUserId = new ConcurrentHashMap<>();
    Set<GameEntity> availableGameEntity = new CopyOnWriteArraySet<>();

    public GameEntity createGameEntityToOneComputer(MyUserPrincipal whitePlayer, MyUserPrincipal blackPlayer) {
        Game game = new Game(Board.getStandartBoard());
        GameEntity gameEntity = new GameEntity(whitePlayer.getUser().getId(), blackPlayer.getUser().getId(), game, GameStatus.GAME);
        gameEntity = gameRepository.save(gameEntity);
        log.info("Game entity saved - {}", gameEntity);
        return gameEntity;
    }

    public synchronized GameEntity connectToGameOrCreateNew (MyUserPrincipal player) {
        GameEntity currentGameEntity = gameEntityByUserId.get(player.getUser().getId());
        if(currentGameEntity != null) {
            log.debug(player.getUser().getUsername() + " is trying to create game, when he already has one");
            return currentGameEntity;
        }

        if (availableGameEntity.isEmpty()) {
            return createNewGameEntity(player);
        }

        GameEntity gameEntity = availableGameEntity.iterator().next();
        gameEntity.setBlackPlayer(player.getUser().getId());
        gameEntity.setGameStatus(GameStatus.GAME);
        GameEntity gameEntitySaved = gameRepository.save(gameEntity);

        availableGameEntity.remove(gameEntity);
        gameEntityByUserId.put(player.getUser().getId(), gameEntitySaved);
        gameEntityByGameId.put(gameEntitySaved.getId(), gameEntitySaved);
        log.debug("Game with one player has two player now And it's starting " +gameEntitySaved);
        return gameEntity;
    }

    private GameEntity createNewGameEntity(MyUserPrincipal player){
        Game game = new Game(Board.getStandartBoard());
        GameEntity gameEntity = new GameEntity(player.getUser().getId(), null, game, GameStatus.WAITING);
        GameEntity gameEntitySaved = gameRepository.save(gameEntity);
        log.info("Game entity saved - {}", gameEntitySaved);

        gameEntityByUserId.put(player.getUser().getId(), gameEntitySaved);
        gameEntityByGameId.put(gameEntitySaved.getId(), gameEntitySaved);
        availableGameEntity.add(gameEntitySaved);

        return gameEntitySaved;
    }

    public void addTurn(User user, int gameId, String t) {
        log.info("{} made {} in game with id - {} ", user, t, gameId);
        Turn turn = Turn.getTurnFromString(t);
        GameEntity gameEntity = gameEntityByGameId.get(gameId);
        if(gameEntity.getGameStatus() != GameStatus.GAME){
            log.debug("Trying to access a game with the wrong status " + gameEntity);
            throw new GameNotAvailable("This game has " +gameEntity.getGameStatus() +" status");
        }
        Game game = gameEntity.getGame();

        if(gameEntity.isWhiteNow() && !gameEntity.getWhitePlayer().equals(user.getId())){
            throw new NotYourTurnException("Now is not your turn");
        }

        if(!gameEntity.isWhiteNow() && !gameEntity.getBlackPlayer().equals(user.getId())){
            throw new NotYourTurnException("Now is not your turn");
        }

            game.makeTurn(turn);
        gameEntity.setBoard(game.getBoard().toString());
        gameEntity.setWhiteNow(game.isWhiteNow);
        TurnEntity turnEntity = new TurnEntity(t, gameId);
        log.debug("Turn was save in  game entity{}", gameEntity);
        gameRepository.save(gameEntity);
        turnRepository.save(turnEntity);
        gameEntityByUserId.put(user.getId(), gameEntity);
        gameEntityByGameId.put(gameId, gameEntity);

    }
    public GameEntity getGameEntityById(int id){
        return gameEntityByGameId.get(id);
    }
}
