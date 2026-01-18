package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public interface Character {
    String damage = "Damage";
    String aoe = "AOE";
    String cleans = "cleans";
    String debuff = "Debuff";
    String buff = "Buff";
    String cc = "Crowd Control";

    //Base abilities
    String air = "air", fire = "fire", ice = "ice", water = "water", magma =  "magma", light = "light";

    //Abilities from combinations
    String mud = "mud", lighting = "lighting", earth= "earth", metal = "metal", plasma = "plasma", shadow = "shadow";
    String gravity = "gravity";

    int mana = 10;
    int coolD = 12;
    int passive = 0;
    int primary = 1;
    int secondary = 2;
    int tertiary = 3;
    int ultimate = 4;

    ArrayList<Ability> abilities = new ArrayList<>( List.of
            (
                    // fire
                    new Ability("Flame Burst", 18, fire, damage, mana, coolD, passive),
                    new Ability("Inferno Wave", 24, fire, aoe, mana, coolD, primary),
                    new Ability("Scorch Mark", 12, fire, debuff, mana, coolD, secondary),
                    new Ability("Phoenix Rebirth", 20, fire, cleans, mana, coolD, ultimate),

                    // water
                    new Ability("Aqua Blade", 14, water, damage, mana, coolD, passive),
                    new Ability("Tidal Crash", 22, water, aoe, mana, coolD, ultimate),

                    // ice
                    new Ability("Frostbite", 16, ice, debuff, mana, coolD, primary),
                    new Ability("Glacial Prison", 10, ice, cc, mana, coolD, secondary),

                    // earth
                    new Ability("Stone Fist", 15, earth, damage, mana, coolD, passive),
                    new Ability("Earthquake", 26, earth, aoe, mana, coolD, secondary),
                    new Ability("Iron Skin", 14, earth, buff, mana, coolD, primary),
                    new Ability("Root Snare", 11, earth, cc, mana, coolD, ultimate),

                    // air
                    new Ability("Gale Slash", 17, air, damage, mana, coolD, passive),
                    new Ability("Hurricane Spiral", 23, air,  aoe, mana, coolD, ultimate),
                    new Ability("Wind Shield", 14, air, buff, mana, coolD, primary),
                    new Ability("Silence Gust", 10, air, debuff, mana, coolD, secondary),

                    // lighting //
                    new Ability("Shock Bolt", 16, lighting, damage, mana, coolD, passive),
                    new Ability("Storm Spear", 20, lighting, damage, mana, coolD, secondary),
                    new Ability("Chain Lightning", 25, lighting, aoe, mana, coolD, ultimate),
                    new Ability("Static Charge", 12, lighting, debuff, mana, coolD, primary),

                    // shadow
                    new Ability("Night Slash", 18, shadow, damage, mana, coolD, passive),
                    new Ability("Umbral Nova", 24, shadow, aoe, mana, coolD, secondary),
                    new Ability("Fear Gaze", 10, shadow, cc, mana, coolD, ultimate),
                    new Ability("Life Leech", 14, shadow, damage, mana, coolD, primary),

                    // light
                    new Ability("Radiant Beam", 17, light, damage, mana, coolD, passive),
                    new Ability("Holy Pulse", 22, light, aoe, mana, coolD, primary),
                    new Ability("Blessing Aura", 14, light, buff, mana, coolD, secondary),
                    new Ability("Purify", 50, light, cleans, mana, coolD, ultimate)

                    // arcane
//                    new Ability("Arcane Missile", 16, damage, mana, coolD, passive),
//                    new Ability("Mana Rift", 20, debuff, mana, coolD, secondary),
//                    new Ability("Astral Storm", 26, aoe, mana, coolD, ultimate),
//                    new Ability("Arcane Shield", 15, buff, mana, coolD, primary)
            )
    );

    Map<String, Ability> abiltityMap = abilities.stream()
            .collect(Collectors.toMap(Ability::getName, a -> a));

    ArrayList<String> abilityNames = abilities.stream()
            .map(Ability::getName)
            .collect(Collectors.toCollection(ArrayList::new));


    int getHealth();

    int getLevel();

    int getMana();

    int getMaxHealth();

    int getMaxMana();

    int getDamageTaken();

    String getName();

    ArrayList<Ability> getAbilities();

    void decreaseHealth(int damage);

    void decreaseMana(int magicPower);

    void increaseHealth(int health);

    void increaseMagicPower(int magicPower);

    void increaseMaxHealth(int health);

    void increaseMaxMana(int mana);

    public HashMap<String, Object> getCharacterInfo();
}
