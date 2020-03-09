package com.zemnitskiy.chess.entity;

import com.zemnitskiy.chess.GameStatus;
import com.zemnitskiy.chess.domain.Board;
import com.zemnitskiy.chess.domain.Game;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Optional;


@Table("game")
public class GameEntity {
    @Id
    int id;
    @Column("white_player_id")
    Integer whitePlayer;
    @Column("black_player_id")
    Integer blackPlayer;

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

    public GameEntity(Integer whitePlayer, Integer blackPlayer, Board board, GameStatus gameStatus) {
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


    public Integer getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(Integer whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public Integer getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(Integer blackPlayer) {
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

    public boolean hasOnlyOnePlayer(){return whitePlayer == null || blackPlayer == null;}

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
