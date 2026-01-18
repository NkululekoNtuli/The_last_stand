package org.agentofcode.the_last_stand_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class Ability {


    private String name;
    private String element;
    private String effect;

    private int power;
    private int manaCost;
    private int coolDown;
    private int slot;


    public Ability(){}

    public Ability(String name, int power, String element, String effect, int manaCost, int coolDown, int slot) {
        this.name = name;
        this.power = power;
        this.element = element;
        this.manaCost = manaCost;
        this.coolDown = coolDown;
        this.slot = slot;
        this.effect = effect;
    }

    public String getName() {return name;}
    public String getElement() {return element;}
    public String getEffect() {return effect;}
    public int getSlot() {return slot;}
    public int getPower() {return power;}
    public int getManaCost() {return manaCost;}
    public int getCoolDown() {return coolDown;}

    @JsonIgnore
    public Ability getCounterElement() {
        String air = "air", fire = "fire", ice = "ice", water = "water", magma =  "magma", light = "light";
        //Abilities from combinations
        String mud = "mud", lighting = "lighting", earth= "earth", metal = "metal", plasma = "plasma", shadow = "shadow";
        String gravity = "gravity";
        Ability ability =  Character.abiltityMap.get("Stone Fist");

        switch (this.element) {
            case "air" -> {
                int random = new Random().nextInt(3);

                List airCounters = new ArrayList<>(List.of( // earth, ice
                        Character.abiltityMap.get("Stone Fist"),
                        Character.abiltityMap.get("Earthquake"),
                        Character.abiltityMap.get("Iron Skin"),
                        Character.abiltityMap.get("Root Snare")
                ));
                ability = (Ability) airCounters.get(random);
            }
            case "fire" -> {
                int random = new Random().nextInt(3);

                List fireCounters = new ArrayList<>(List.of( // wate, ice
                        Character.abiltityMap.get("Flame Burst"),
                        Character.abiltityMap.get("Inferno Wave"),
                        Character.abiltityMap.get("Scorch Mark"),
                        Character.abiltityMap.get("Phoenix Rebirth")
                ));
                ability = (Ability) fireCounters.get(random);
            }
            case "earth" -> {
                int random = new Random().nextInt(3);

                List earthCounters = new ArrayList<>(List.of( // water, air
                        Character.abiltityMap.get("Stone Fist"),
                        Character.abiltityMap.get("Earthquake"),
                        Character.abiltityMap.get("Iron Skin"),
                        Character.abiltityMap.get("Root Snare")
                ));
                ability = (Ability) earthCounters.get(random);
            }
            case "ice" -> {
                int random = new Random().nextInt(1);

                List iceCounters = new ArrayList<>(List.of( // earth, ice
                        Character.abiltityMap.get("Flame Burst"),
                        Character.abiltityMap.get("Inferno Wave"),
                        Character.abiltityMap.get("Scorch Mark"),
                        Character.abiltityMap.get("Phoenix Rebirth")
                ));
                ability = (Ability) iceCounters.get(random);
            }
            case "water" -> { // fire
                int random = new Random().nextInt(1);

                List waterCounters = new ArrayList<>(List.of(
                        Character.abiltityMap.get("Aqua Blade"),
                        Character.abiltityMap.get("Tidal Crash")
                ));
                ability = (Ability) waterCounters.get(random);
            }
            case "magma" -> {
                int random = new Random().nextInt(1);

                List waterCounters = new ArrayList<>(List.of(
                        Character.abiltityMap.get("Aqua Blade"),
                        Character.abiltityMap.get("Tidal Crash")
                ));
                ability = (Ability) waterCounters.get(random);
            }
            case "mud" -> {
                int random = new Random().nextInt(1);

                List waterCounters = new ArrayList<>(List.of(
                        Character.abiltityMap.get("Aqua Blade"), 
                        Character.abiltityMap.get("Tidal Crash")
                ));
                ability = (Ability) waterCounters.get(random);
            }
            case "metal" -> {
                int random = new Random().nextInt(1);

                List waterCounters = new ArrayList<>(List.of(
                        Character.abiltityMap.get("Aqua Blade"),
                        Character.abiltityMap.get("Tidal Crash")
                ));
                ability = (Ability) waterCounters.get(random);
            }
            case "shadow" -> {
                int random = new Random().nextInt(1);

                List waterCounters = new ArrayList<>(List.of(
                        Character.abiltityMap.get("Aqua Blade"),
                        Character.abiltityMap.get("Tidal Crash")
                ));
                ability = (Ability) waterCounters.get(random);
            }
            case "lighting", "plasma" -> {
                int random = new Random().nextInt(3);

                List lightingCounter = new ArrayList<>(List.of(
                        Character.abiltityMap.get("Shock Bolt"),
                        Character.abiltityMap.get("Storm Spear"),
                        Character.abiltityMap.get("Chain Lightning"),
                        Character.abiltityMap.get("Static Charge")
                ));
                ability = (Ability) lightingCounter.get(random);
            }
        }

        return ability;
    }
}

