package org.agentofcode.the_last_stand_backend.service;

import org.agentofcode.the_last_stand_backend.model.Hero;
import org.agentofcode.the_last_stand_backend.repository.HeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {

    private HeroRepository heroRepository;

//    public HeroService(){}

    public HeroService(HeroRepository heroRepository){
        this.heroRepository = heroRepository;
    }


    public List<Hero> getHeroes(Long userId) {
        return this.heroRepository.findAllByUserIdIs(userId);
    }
}
