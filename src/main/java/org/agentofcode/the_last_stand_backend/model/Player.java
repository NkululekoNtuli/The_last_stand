package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class Player implements Character {

    private int health = 200;
    private int level = 0;
    private int magicPower = 100 ;
    private String name;

    private HashMap<String, Integer> skills = new HashMap<>(Map.of(
            "plasma beam", 10,
            "atomic blast",20,
            "healing", 30,
            "bleed", 2
    ));


    private String passiveSkill;
    private String primarySkill;
    private String secondarySkill;
    private String ultimateSkill;

    public Player(){

    }
    public Player(String name, String[] chosenSkills){
        this.name = name;
        passiveSkill = chosenSkills[0];
        primarySkill = chosenSkills[1];
        secondarySkill = chosenSkills[2];
//        ultimateSkill = chosenSkills[3];
    }


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
