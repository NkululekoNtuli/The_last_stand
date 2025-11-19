package org.agentofcode.the_last_stand_backend.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseCharacter implements Character{

    private String name;
    private int health;
    private int level;
    private int magicPower;
    private ArrayList<Ability> abilities;
    private ArrayList<String> abilityNames = new ArrayList<>();


    public BaseCharacter(){}

    public BaseCharacter(String name, ArrayList<Ability> abilities, int health, int level, int magicPower){
        this.name = name;
        this.health = health;
        this.level = level;
        this.magicPower = magicPower;
        this.abilities = abilities;
        abilities.forEach(ability -> {
            abilityNames.add(ability.getName());
        });
    }

    @Override
    public int getHealth() {return this.health;}

    @Override
    public int getLevel() {return level;}

    @Override
    public int getMagicPower() {return magicPower;}

    @Override
    public String getName() {return name;}

    @Override
    public ArrayList<Ability> getAbilities() {return abilities;}

    @Override
    public void decreaseHealth(int damage) {this.health -= damage;}

    @Override
    public void decreaseMagicPower(int magicPower) {magicPower -= magicPower;}

    @Override
    public void increaseHealth(int health) { health += health;}

    @Override
    public void increaseMagicPower(int magicPower) {magicPower += magicPower;}

    @Override
    public HashMap<String, Object> getCharacterInfo() {
        return new HashMap<>(Map.ofEntries(
                Map.entry("name", name),
                Map.entry("Health", health),
                Map.entry("Level", level),
                Map.entry("Magic Power", magicPower),
                Map.entry("AbilityNames", abilityNames),
                Map.entry("Abilities", abilities)
        ));
    }
}
