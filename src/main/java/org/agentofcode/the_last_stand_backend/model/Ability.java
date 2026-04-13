package org.agentofcode.the_last_stand_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Ability {
    private String name;
    private String element;
    private int power;
    private int manaCost;
    private int coolDown;
    private int slot;
    private String type;
    private List<String> effects;
    private List<String> synergyElements;

    public Ability(){}

    public Ability(String name, int power, String element, String type, int manaCost, int coolDown, int slot,
                   List<String> effects, List<String> synergyElements) {

        this.name = name;
        this.power = power;
        this.element = element;
        this.type = type;
        this.manaCost = manaCost;
        this.coolDown = coolDown;
        this.slot = slot;
        this.effects = effects;
        this.synergyElements = synergyElements;
    }

    // Getters
    public String getName() {return name;}
    public String getElement() {return element;}
    public List<String> getEffect() {return effects;}
    public int getSlot() {return slot;}
    public int getPower() {return power;}
    public int getManaCost() {return manaCost;}
    public int getCoolDown() {return coolDown;}

    public String getType() {return type;}
    public List<String> getEffects() {return effects;}
    public List<String> getSynergyElements() {return synergyElements;}

    @JsonIgnore
    public Ability getCounterElement() {

        Map<String, List<String>> counters = new HashMap<>();

        counters.put("air", List.of("Stone Fist", "Earthquake", "Root Snare"));
        counters.put("fire", List.of("Aqua Blade", "Tidal Crash"));
        counters.put("earth", List.of("Aqua Blade", "Hurricane Spiral"));
        counters.put("ice", List.of("Flame Burst", "Inferno Wave"));
        counters.put("water", List.of("Shock Bolt", "Chain Lightning"));
        counters.put("lightning", List.of("Stone Fist", "Iron Skin"));
        counters.put("shadow", List.of("Radiant Beam", "Purify"));
        counters.put("light", List.of("Umbral Nova", "Night Slash"));

        // combo elements
        counters.put("magma", List.of("Aqua Blade"));
        counters.put("mud", List.of("Gale Slash"));
        counters.put("metal", List.of("Earthquake"));
        counters.put("plasma", List.of("Stone Fist"));

        List<String> possibleCounters = counters.getOrDefault(this.element.toLowerCase(),
                List.of("Stone Fist"));

        int random = new Random().nextInt(possibleCounters.size());
        return Character.abiltityMap.get(possibleCounters.get(random));
    }

    @JsonIgnore
    public boolean canTriggerSynergy(List<String> playerElements) {
        if (synergyElements == null || synergyElements.isEmpty()) return true;
        return playerElements.containsAll(synergyElements);
    }
}

