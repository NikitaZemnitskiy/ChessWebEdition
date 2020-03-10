package com.zemnitskiy.chess.entity;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface GameRepository extends CrudRepository<GameEntity, Integer> {
    @Query("select * from game g where g.id = :id")
    GameEntity findByGameId(@Param("id") int id);
    @Query("select * from game g where g.status = 'GAME'")
    Set<GameEntity> getAllStartedGame();
    @Modifying
    @Query(value = "DELETE FROM game WHERE status = 'WAITING'")
    int deleteAwaitGames();
  //  @Query("insert into game (white_player_id, black_player_id, board, created, status) VALUES (:wpi,:bpi,:board,:created, :stat::game_status)")
 //   void insertIntoGame(@Param("wpi") int wpi, @Param("bpi") int bpi, @Param("board")  String board, @Param("created") int created, @Param("stat") String string);
}
