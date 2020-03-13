package com.zemnitskiy.chess.service;

import com.zemnitskiy.chess.GameStatus;
import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Game;
import com.zemnitskiy.chess.domain.Turn;
import com.zemnitskiy.chess.domain.exceptions.GameNotAvailable;
import com.zemnitskiy.chess.domain.exceptions.WrongTurnException;
import com.zemnitskiy.chess.domain.figures.Color;
import com.zemnitskiy.chess.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {
    Logger log = LoggerFactory.getLogger(GameService.class);

    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TurnRepository turnRepository;

    Map<Integer, GameEntity> gameEntityByGameId = new ConcurrentHashMap<>();
    Map<Integer, GameEntity> gameEntityByUserId = new ConcurrentHashMap<>();
    GameEntity availableGameEntity = null;


    public synchronized GameEntity connectToGameOrCreateNew (MyUserPrincipal player) {
        GameEntity currentGameEntity = gameEntityByUserId.get(player.getUser().getId());
        if(currentGameEntity != null && currentGameEntity.getGame().status == GameStatus.GAME) {
            log.debug(player.getUser().getUsername() + " is trying to create game, when he already has one");
            return currentGameEntity;
        }

        if (availableGameEntity == null) {
            return createNewGameEntity(player);
        }

        GameEntity gameEntity = availableGameEntity;
        availableGameEntity = null;
        gameEntity.setBlackPlayer(player.getUser().getId());
        gameEntity.setGameStatus(GameStatus.GAME);
        gameEntity.setCreated(LocalDateTime.now());
        GameEntity gameEntitySaved = gameRepository.save(gameEntity);
        gameEntitySaved.getGame().joinGame(new Game.Player(player.getUsername()));

        gameEntityByUserId.put(player.getUser().getId(), gameEntitySaved);
        gameEntityByGameId.put(gameEntitySaved.getId(), gameEntitySaved);
        log.debug("Game with one player has two player now And it's starting " +gameEntitySaved);
        return gameEntity;
    }

    private GameEntity createNewGameEntity(MyUserPrincipal player){
        Game game = new Game(Board.getStandartBoard());
        game.startGame(new Game.Player(player.getUsername()));
        game.status = GameStatus.WAITING;
        GameEntity gameEntity = new GameEntity(player.getUser().getId(), null, game, GameStatus.WAITING);
        GameEntity gameEntitySaved = gameRepository.save(gameEntity);
        log.info("Game entity saved - {}", gameEntitySaved);

        gameEntityByUserId.put(player.getUser().getId(), gameEntitySaved);
        gameEntityByGameId.put(gameEntitySaved.getId(), gameEntitySaved);
        availableGameEntity = gameEntitySaved;

        return gameEntitySaved;
    }

    public void addTurn(User user, int gameId, String t) {
        log.info("{} made {} in game with id - {} ", user, t, gameId);
        GameEntity gameEntity = gameEntityByGameId.get(gameId);
        Game game = gameEntity.getGame();

        if(gameEntity.getGameStatus() != GameStatus.GAME){
            log.debug("Trying to access a game with the wrong status " + gameEntity);
            if(gameEntity.getGameStatus() == GameStatus.WAITING){
                throw new GameNotAvailable("This game has not started yet");
            }
            else {
                throw new GameNotAvailable("This game is already over");
            }
        }


        if(gameEntity.isWhiteNow() && !gameEntity.getWhitePlayer().equals(user.getId())){
            throw new WrongTurnException("Now is not your turn");
        }

        if(!gameEntity.isWhiteNow() && !gameEntity.getBlackPlayer().equals(user.getId())){
            throw new WrongTurnException("Now is not your turn");
        }


            Turn turn = Turn.getTurnFromString(t);
            if(gameEntity.getWhitePlayer().equals(user.getId()) && game.getBoard().figures[turn.from.x][turn.from.y].color != Color.WHITE){
                throw new WrongTurnException("It's not your figure");
            }
            if(gameEntity.getBlackPlayer().equals(user.getId()) && game.getBoard().figures[turn.from.x][turn.from.y].color != Color.BLACK){
                throw new WrongTurnException("It's not your figure");
            }
            game.makeTurn(turn);

        gameEntity.setBoard(game.getBoard().toString());
        gameEntity.setWhiteNow(game.isWhiteNow);
        gameEntity.setGameStatus(game.status);
        TurnEntity turnEntity = new TurnEntity(t, gameId);
        log.debug("Turn was save in game entity{}", gameEntity);

        gameRepository.save(gameEntity);
        turnRepository.save(turnEntity);
        gameEntityByUserId.put(user.getId(), gameEntity);
        gameEntityByGameId.put(gameId, gameEntity);

    }
    public void addSurrendered(MyUserPrincipal player, int gameId){

        log.debug(player.getUsername() + " surrendered in the game with id - "+gameId);
       GameEntity gameEntity = gameEntityByGameId.get(gameId);
       if(gameEntity.getGameStatus() != GameStatus.GAME){
           return;
       }
       gameEntity.getGame().addSurrendered(player.getUsername());
       gameEntity.setGameStatus(gameEntity.getGame().status);
       gameRepository.save(gameEntity);
       gameEntityByUserId.remove(gameEntity.getWhitePlayer());
       gameEntityByUserId.remove(gameEntity.getBlackPlayer());
    }
    public GameEntity getGameEntityById(int id){
        return gameEntityByGameId.get(id);
    }

    public void downloadGamesFromDataBase(){
        Set<GameEntity> games = gameRepository.getAllStartedGame();
        for(GameEntity g:games){
            Game game = new Game(Board.getBoardFromString(g.getBoard()));
            game.status = g.getGameStatus();
            game.white = new Game.Player(userRepository.findByUserID(g.getWhitePlayer()).getUsername());
            game.black = new Game.Player(userRepository.findByUserID(g.getBlackPlayer()).getUsername());
            game.isWhiteNow = g.isWhiteNow();
            g.setGame(game);
            gameEntityByUserId.put(g.getBlackPlayer(), g);
            gameEntityByUserId.put(g.getWhitePlayer(), g);
            gameEntityByGameId.put(g.getId(), g);
        }
    }
    public void deleteAwaitGames(){
        log.debug(gameRepository.deleteAwaitGames()+" Awaiting games were delete");
    }
}
