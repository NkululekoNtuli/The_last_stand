package org.agentofcode.the_last_stand_backend.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


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
    @Column(nullable = false, name = "user_id")
    private Long userId;
//    private ArrayList<Ability> abilities = new ArrayList<>();


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

//    @Override
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
}
