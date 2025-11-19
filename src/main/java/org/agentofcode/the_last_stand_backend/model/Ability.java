package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

@Component
public class Ability {


    private String name;
    private int power;
    private int manaCost;
    private int coolDown;
    private String type;

    public Ability(){}

    public Ability(String name, int power, String type, int manaCost, int coolDown) {
        this.name = name;
        this.power = power;
        this.type = type;
        this.manaCost = manaCost;
        this.coolDown = coolDown;
    }

    public String getName() {return name;}
    public int getPower() {return power;}
    public String getType() {return type;}
    public int getManaCost() {return manaCost;}
    public int getCoolDown() {return coolDown;}

}
