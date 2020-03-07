package com.zemnitskiy.chess.entity;

import com.zemnitskiy.chess.GameStatus;
import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Game;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.Set;

@Table("game")
public class GameEntity {
    @Id
    int id;
    @Column("white_player_id")
    int whitePlayer;
    @Column("black_player_id")
    int blackPlayer;
    @Column("board")
    String board;
    @Column("is_white_now")
    boolean isWhiteNow;
    @Column("created")
    int created;
    @Transient
    Game game;
    @Column("status")
    GameStatus gameStatus;


    public GameEntity(int whitePlayer, int blackPlayer, Board board, GameStatus gameStatus) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.board = board.toString();
        this.isWhiteNow = true;
        this.game = new Game(board);
        this.gameStatus = gameStatus;
        this.created = 221015;
}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(int whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public int getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(int blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isWhiteNow() {
        return isWhiteNow;
    }

    public void setWhiteNow(boolean whiteNow) {
        isWhiteNow = whiteNow;
    }

    public GameEntity() {
    }


    @Override
    public String toString() {
        return "GameEntity{" +
                "id=" + id +
                ", whitePlayer=" + whitePlayer +
                ", blackPlayer=" + blackPlayer +
                ", board='" + board + '\'' +
                ", created=" + created +
                ", game=" + game +
                ", gameStatus=" + gameStatus +
                '}';
    }
}
