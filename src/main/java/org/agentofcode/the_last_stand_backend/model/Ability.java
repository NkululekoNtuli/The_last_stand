package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

@Component
public class Ability {


    private String name;
    private int power;
    private int manaCost;
    private int coolDown;
    private String type;
    private String category;

    public Ability(){}

    public Ability(String name, int power, String type, int manaCost, int coolDown, String category) {
        this.name = name;
        this.power = power;
        this.type = type;
        this.manaCost = manaCost;
        this.coolDown = coolDown;
        this.category = category;
    }

    public String getName() {return name;}
    public String getType() {return type;}
    public String getCategory() {return category;}
    public int getPower() {return power;}
    public int getManaCost() {return manaCost;}
    public int getCoolDown() {return coolDown;}
}
