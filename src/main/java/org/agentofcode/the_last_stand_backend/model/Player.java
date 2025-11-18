package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Component
public class Player extends BaseCharacter {

    private int health = 200;
    private int level = 0;
    private int magicPower = 100 ;
    private String name =  "testing";
    private ArrayList<String> skills;
    private HashMap<String, Object> skillsDMG = new HashMap<>();


    public Player(){}

    public Player(String name, ArrayList<String> skills, int health, int level, int magicPower){
        this.name = name;
        this.health = health;
        this.level = level;
        this.magicPower = magicPower;
        this.skills = skills;
        for (String skill : skills) skillsDMG.put(skill, Character.Skills.get(skill));
    }

//    @Override
    public HashMap<String, Object> getCharacterInfo() {
        return new HashMap<>(Map.ofEntries(
                Map.entry("name", name),
                Map.entry("Health", health),
                Map.entry("Level", level),
                Map.entry("Magic Power", magicPower),
                Map.entry("Skills", skillsDMG)
        ));
    }
}
