package org.agentofcode.the_last_stand_backend.model;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

public class Character {

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

    public Character(){}
    public Character(String name, String[] chosenSkills){
        this.name = name;
        passiveSkill = chosenSkills[0];
        primarySkill = chosenSkills[1];
        secondarySkill = chosenSkills[2];
        ultimateSkill = chosenSkills[3];
    }

    public int getHealth() {return health;
    }

    public int getLevel() {
        return level;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public String getName() {
        return name;
    }

    public String[] getSkills() {
        return new String[] {passiveSkill, primarySkill, secondarySkill, ultimateSkill};
    }
}
