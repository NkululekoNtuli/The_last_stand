package org.agentofcode.the_last_stand_backend.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
@Entity
@Table(name = "heros")
public class Hero extends BaseCharacter {
    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private String passiveAbility;
    private String primaryAbility;
    private String secondaryAbility;
    private String tertiaryAbility;
    private String ultimateAbility;
    private int health;
    private int mana;
    private int wins;
    private int loses;
//    private int level;
//    private int maxHealth;
//    private int maxMana;
    @Column(nullable = false, name = "user_id")
    private Long userId;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Hero(){super();}

    public Hero(String name, ArrayList<Ability> abilities, int health, int level, int mana, int wins, int loses, long userId){
        super(name, abilities, health, level, mana);
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.userId = userId;
        this.wins = wins;
        this.loses = loses;
//        this.level = level;
//        this.maxHealth = health;
//        this.maxMana = mana;

        for (Ability ability : abilities) {
            switch (ability.getSlot()){
                case 0 -> passiveAbility = ability.getName();
                case 1 -> primaryAbility = ability.getName();
                case 2 -> secondaryAbility = ability.getName();
                case 3 -> tertiaryAbility = ability.getName();
                case 4 -> ultimateAbility = ability.getName();
            }
        }
    }


    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", passiveAbility='" + passiveAbility + '\'' +
                ", primaryAbility='" + primaryAbility + '\'' +
                ", secondaryAbility='" + secondaryAbility + '\'' +
                ", tertiaryAbility='" + tertiaryAbility + '\'' +
                ", ultimateAbility='" + ultimateAbility + '\'' +
                ", health=" + health +
                ", mana=" + mana +
                '}';
    }


//    @Override
//    public int getMaxMana() {
//        return maxMana;
//    }

//    @Override
//    public void increaseMaxMana(int maxMana) {
//        this.maxMana = maxMana;
//    }

    @Override
    public int getHealth() {return this.health;}

//    @Override
//    public int getLevel() {return level;}

    @Override
    public int getMana() {return this.mana;}

    @Override
    public String getName() {return name;}

    @Override
    public ArrayList<Ability> getAbilities() {
        ArrayList<Ability> abilities = new ArrayList<>();
        abilities.add(Character.abiltityMap.get(passiveAbility));
        abilities.add(Character.abiltityMap.get(primaryAbility));
        abilities.add(Character.abiltityMap.get(secondaryAbility));
        abilities.add(Character.abiltityMap.get(tertiaryAbility));
        abilities.add(Character.abiltityMap.get(ultimateAbility));

        return abilities;
    }

    @Override
    public void decreaseHealth(int damage) {this.health -= damage;}

    @Override
    public void decreaseMana(int magicPower) {this.mana -= magicPower;}

    @Override
    public void increaseHealth(int health) { this.health += health;}

    @Override
    public void increaseMagicPower(int magicPower) {magicPower += magicPower;}

//    @Override
//    public int getDamageTaken(){
//        return maxHealth - health;
//    }


    public Map<String, Object> getHeroInfo() {
        Map<String, Object> map = new HashMap<>();

        map.put("name", name);
        map.put("passiveAbility", passiveAbility);
        map.put("primaryAbility", primaryAbility);
        map.put("secondaryAbility", secondaryAbility);
        map.put("tertiaryAbility", tertiaryAbility);
        map.put("ultimateAbility", ultimateAbility);
        map.put("health", health);
        map.put("mana", mana);
        map.put("wins", wins);
        map.put("loses", loses);
        return map;
    }
}
