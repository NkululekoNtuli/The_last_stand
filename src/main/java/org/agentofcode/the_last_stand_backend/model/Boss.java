package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class Boss extends BaseCharacter {
    private String name;
    private int health;
    private int level;
    private int magicPower;
    private ArrayList<String> skills;
    private HashMap<String, Object> skillsDMG = new HashMap<>();

    public Boss(){}

    public Boss(String name, ArrayList<String> skills, int health, int level, int magicPower) {
        this.name = name;
        this.health = health;
        this.level = level;
        this.magicPower = magicPower;
        this.skills = skills;
        for (String skill : skills) skillsDMG.put(skill, Character.Skills.get(skill));
    }

    public void berserk(){
        // Something triggers the boss to go berserk this increases the bosses dmg and MP in exchange of some of its health
    }

    @Override
    public Map<String, Object> getCharacterInfo() {
        return Map.of();
    }
}
