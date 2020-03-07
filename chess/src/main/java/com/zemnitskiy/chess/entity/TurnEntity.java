package com.zemnitskiy.chess.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table("turn")
public class TurnEntity {
    @Id
    int id;
    @Column("turn")
    String turn;
    @Column("game_id")
    int gameId;

    public TurnEntity(String turn, int gameId) {
        this.turn = turn;
        this.gameId = gameId;
    }

    public TurnEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurnEntity that = (TurnEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TurnEntity{" +
                "turn='" + turn + '\'' +
                '}';
    }
}
