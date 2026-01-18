package org.agentofcode.the_last_stand_backend.repository;

import org.agentofcode.the_last_stand_backend.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, Integer> {
    Hero findHeroByName(String name);

    List<Hero> findHeroesByUserId(long userId);

    List<Hero> findAllByUserIdIs(long userId);

    Hero findHeroesByUserIdAndName(Long userId, String name);
}
