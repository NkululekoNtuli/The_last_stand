package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

@Component
public interface Character {

    int health = 0;
    int level = 0;
    int magicPower = 100 ;
    String name = "name";


    public int getHealth();

    public int getLevel();

    public int getMagicPower();

    public String getName();

    public String[] getSkills();
}
