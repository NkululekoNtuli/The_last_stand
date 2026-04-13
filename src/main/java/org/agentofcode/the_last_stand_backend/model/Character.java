package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.*;
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


    List<Ability> abilities = Arrays.asList(

            //FIRE
            new Ability("Flame Burst", 14, "fire", "damage", 5, 2, 0,
                    Arrays.asList("burn"), null),

            new Ability("Inferno Wave", 24, "fire", "aoe", 10, 6, 1,
                    Arrays.asList("burn"), null),

            new Ability("Phoenix Ember", 16, "fire", "heal", 12, 8, 2,
                    Arrays.asList("lifesteal"), null),

            new Ability("Phoenix Rebirth", 50, "fire", "ultimate", 25, 40,
                    3, Arrays.asList("revive", "explode"), null),

            //WATER
            new Ability("Aqua Blade", 13, "water", "damage", 5, 2, 0,
                    Arrays.asList("none"), null),

            new Ability("Tidal Crash", 22, "water", "aoe", 10, 6, 1,
                    Arrays.asList("knockback"), null),

            new Ability("Healing Tide", 18, "water", "heal", 12, 8, 2,
                    Arrays.asList("regen"), null),

            new Ability("Tsunami Core", 40, "water", "ultimate", 25, 35,
                    3, Arrays.asList("knockback", "heal"), null),

            //ICE
            new Ability("Frost Shard", 14, "ice", "damage", 5, 2, 0,
                    Arrays.asList("slow"), null),

            new Ability("Frostbite", 22, "ice", "damage", 10, 6, 1,
                    Arrays.asList("bonus_vs_slow"), null),

            new Ability("Frozen Renewal", 15, "ice", "heal", 12, 8, 2,
                    Arrays.asList("shield"), null),

            new Ability("Absolute Zero", 0, "ice", "ultimate", 25, 40,
                    3, Arrays.asList("freeze_all"), null),


            //LIGHTNING
            new Ability("Shock Bolt", 15, "lightning", "damage", 5, 2,
                    0, Arrays.asList("shock"), null),

            new Ability("Storm Spear", 23, "lightning", "damage", 10, 6,
                    1, Arrays.asList("pierce"), null),

            new Ability("Static Recharge", 16, "lightning", "heal", 12, 8,
                    2, Arrays.asList("shield_from_damage"), null),

            new Ability("Chain Lightning", 45, "lightning", "ultimate", 25,
                    35, 3, Arrays.asList("chain"), null),

            //SHADOW
            new Ability("Night Slash", 15, "shadow", "damage", 5, 2,
                    0, Arrays.asList("none"), null),

            new Ability("Umbral Nova", 23, "shadow", "aoe", 10, 6, 1,
                    Arrays.asList("weaken"), null),

            new Ability("Life Leech", 18, "shadow", "heal", 12, 8, 2,
                    Arrays.asList("drain"), null),

            new Ability("Abyssal Domain", 40, "shadow", "ultimate", 25, 35,
                    3, Arrays.asList("drain_field"), null),

            //LIGHT
            new Ability("Radiant Beam", 14, "light", "damage", 5, 2, 0,
                    Arrays.asList("none"), null),

            new Ability("Holy Pulse", 20, "light", "aoe", 10, 6, 1,
                    Arrays.asList("minor_heal"), null),

            new Ability("Blessing Aura", 22, "light", "heal", 12, 8,
                    2, Arrays.asList("regen"), null),

            new Ability("Purify", 50, "light", "ultimate", 25, 40, 3,
                    Arrays.asList("cleanse_all"), null),

            //SYNERGY
            new Ability("Infernal Cyclone", 35, "fire", "aoe", 20, 15,
                    4, Arrays.asList("burn", "pull"), Arrays.asList("fire", "air"))
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
