package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class Player extends BaseCharacter {

    public Player(){super();}

    public Player(String name, ArrayList<String> skills, int health, int level, int magicPower){
        super(name, skills, health, level, magicPower);
    }
}
