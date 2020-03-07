package com.zemnitskiy.chess.entity;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GameRepository extends CrudRepository<GameEntity, Integer> {
    @Query("select * from game g where g.id = :id")
    GameEntity findByGameId(@Param("id") int id);
}
