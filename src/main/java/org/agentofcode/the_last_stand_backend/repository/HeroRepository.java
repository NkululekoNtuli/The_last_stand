package org.agentofcode.the_last_stand_backend.repository;

import org.agentofcode.the_last_stand_backend.model.Heros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Heros, Integer> {
    Heros findHeroByName(String name);
}
