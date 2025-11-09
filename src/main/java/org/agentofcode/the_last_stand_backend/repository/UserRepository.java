package org.agentofcode.the_last_stand_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.agentofcode.the_last_stand_backend.model.Users;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUserName(String username);

    List<Users> findAllByRatingGreaterThan(int minRating);

    List<Users> findAllByRatingLessThan(int minRating);

    List<Users> findUsersByRatingBetween(int minRating, int maxRating);

}
