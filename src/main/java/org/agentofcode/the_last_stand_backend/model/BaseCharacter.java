package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class BaseCharacter implements Character{

    private String name;
    private int health;
    private int level;
    private int mana;
    private int maxHealth;
    private int maxMana;
    private ArrayList<Ability> abilities;
    private ArrayList<String> abilityNames = new ArrayList<>();


    public BaseCharacter(){}

    public BaseCharacter(String name, ArrayList<Ability> abilities, int health, int level, int mana){
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.level = level;
        this.mana = mana;
        this.maxMana = mana;
        this.abilities = abilities;

        abilities.forEach(ability -> {
            abilityNames.add(ability.getName());
        });
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public void increaseMaxHealth(int maxHealth) {
        this.maxHealth += maxHealth;
    }

    @Override
    public int getMaxMana() {
        return maxMana;
    }

    @Override
    public void increaseMaxMana(int maxMana) {
        this.maxMana += maxMana;
    }

    public void increaseLevel (int lv, int amt){
        level = lv;

    }

    @Override
    public int getHealth() {return this.health;}

    @Override
    public int getLevel() {return level;}

    @Override
    public int getMana() {return this.mana;}

    @Override
    public String getName() {return name;}

    @Override
    public ArrayList<Ability> getAbilities() {return abilities;}

    @Override
    public void decreaseHealth(int damage) {this.health -= damage;}

    @Override
    public void decreaseMana(int magicPower) {this.mana -= magicPower;}

    @Override
    public void increaseHealth(int health) {
        int tempHP = this.health + health;
        if (tempHP > maxHealth) {
        this.health = this.maxHealth;
        } else {
            this.health += health;
        }
    }

    @Override
    public void increaseMagicPower(int magicPower) {
        int tempMana = this.mana + magicPower;

        if (tempMana > maxHealth) {
            this.mana = maxMana;
        } else {
            this.mana += magicPower;
        }
    }

    @Override
    public int getDamageTaken(){
        return maxHealth - health;
    }

    @Override
    public HashMap<String, Object> getCharacterInfo() {
        return new HashMap<>(Map.ofEntries(
                Map.entry("name", name),
                Map.entry("Health", health),
                Map.entry("Level", level),
                Map.entry("Magic Power", mana),
                Map.entry("AbilityNames", abilityNames),
                Map.entry("Abilities", abilities)
        ));
    }
}
