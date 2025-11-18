package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Boss implements Character {
    private int health = 200;
    private int level = 0;
    private int magicPower = 100 ;
    private String name;


    public Boss(){}

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getMagicPower() {
        return 0;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String[] getSkills() {
        return new String[0];
    }

    @Override
    public Map<String, Object> getCharacterInfo() {
        return Map.of();
    }
}
