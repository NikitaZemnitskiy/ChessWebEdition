package com.zemnitskiy.chess.service;

import com.zemnitskiy.chess.GameStatus;
import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Game;
import com.zemnitskiy.chess.domain.Turn;
import com.zemnitskiy.chess.domain.boardRep.StandartBoard;
import com.zemnitskiy.chess.domain.exceptions.GameNotAvailable;
import com.zemnitskiy.chess.domain.exceptions.NotYourTurnException;
import com.zemnitskiy.chess.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
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

    Set<GameEntity> activeGames = new HashSet<>();

    public GameEntity createGameEntity(MyUserPrincipal whitePlayer, MyUserPrincipal blackPlayer) {
        Board standartBoard = StandartBoard.getStandartBoard();
        GameEntity gameEntity = new GameEntity(whitePlayer.getUser().getId(), blackPlayer.getUser().getId(), standartBoard, GameStatus.GAME);
        gameEntity.setGame(new Game(standartBoard));
        log.info("Game entity created - {}", gameEntity);
        gameEntity = gameRepository.save(gameEntity);
        log.info("Game entity saved - {}", gameEntity);
        activeGames.add(gameEntity);
        return gameEntity;
    }

    public GameEntity connectToGameOrCreateNew(MyUserPrincipal player) {
        Set<GameEntity> freeGames = getAllFreeGames();
        if (freeGames.isEmpty()) {
            Board standartBoard = StandartBoard.getStandartBoard();
            GameEntity gameEntity = new GameEntity(player.getUser().getId(), null, standartBoard, GameStatus.WAITING);
            gameEntity.setGame(new Game(standartBoard));
            log.info("Game entity created - {}", gameEntity);
            GameEntity gameEntitySaved = gameRepository.save(gameEntity);
            log.info("Game entity saved - {}", gameEntitySaved);
            activeGames.add(gameEntitySaved);
            return gameEntitySaved;
        }

        for(GameEntity gameEntity:activeGames) {
            if (gameEntity.getWhitePlayer().equals(player.getUser().getId()) ||
                    gameEntity.getBlackPlayer() != null && gameEntity.getBlackPlayer() !=
                            player.getUser().getId()) {
                log.debug(player.getUser().getUsername() + " is trying to create game, when he already has one");
                return gameEntity;
            }
        }

        GameEntity gameEntity = freeGames.iterator().next();
        gameEntity.setBlackPlayer(player.getUser().getId());
        gameEntity.setGameStatus(GameStatus.GAME);
        GameEntity gameEntitySaved = gameRepository.save(gameEntity);
        activeGames.add(gameEntitySaved);
        log.debug("Game with one player has two player now And it's starting " +gameEntitySaved);
        return gameEntity;
    }

    public void addTurn(User user, int gameId, String t) {
        log.info("{} made {} in game with id - {} ", user, t, gameId);
        Turn turn = Turn.getTurnFromString(t);
        GameEntity gameEntity = getGameEntityById(gameId);
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

            activeGames.add(gameEntity);
    }

    public GameEntity getGameEntityById(int gameId) {
        for (GameEntity g : activeGames) {
            if (g.getId() == gameId) {
                log.debug("Game was given from active games");
                return g;
            }
        }
        GameEntity gameEntity = gameRepository.findByGameId(gameId);
        if (gameEntity == null) {
            throw new IllegalStateException("Bad game id");
        }
            gameEntity.setGame(new Game(Board.getBoardFromString(gameEntity.getBoard())));
            gameEntity.getGame().isWhiteNow = gameEntity.isWhiteNow();
        log.debug("Game was given from rep");
        return gameEntity;
    }

    public Set<GameEntity> getAllFreeGames(){
        return this.activeGames.stream().filter(a -> a.hasOnlyOnePlayer()).collect(Collectors.toSet());
    }
}
