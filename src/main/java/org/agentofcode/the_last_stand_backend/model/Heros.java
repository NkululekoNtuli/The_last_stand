package org.agentofcode.the_last_stand_backend.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
@Entity
public class Heros extends BaseCharacter {
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
    @Column(nullable = false, unique = true)
    private long userId;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Heros(){super();}

    public Heros(String name, ArrayList<Ability> abilities, int health, int level, int mana, int wins, int loses, long userId){
        super(name, abilities, health, level, mana);
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.userId = userId;

        for (Ability ability : abilities) {
            switch (ability.getCategory()){
                case "passive" -> passiveAbility = ability.getName();
                case "primary" -> primaryAbility = ability.getName();
                case "secondary" -> secondaryAbility = ability.getName();
                case "tertiary" -> tertiaryAbility = ability.getName();
            }
        }
    }
}
