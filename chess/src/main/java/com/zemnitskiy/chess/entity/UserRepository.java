package com.zemnitskiy.chess.entity;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

    /*public interface UserRepository extends Repository<User, Integer> {
        User findByUsername(String username);

    }*/
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("select * from users u where u.username = :username")
    User findByUsername(@Param("username") String username);

        @Query("select * from users u where u.id = :id")
        User findByUserID(@Param("id") int id);
}
