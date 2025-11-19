package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public interface Character {

//    int health = 0;
//    int level = 0;
//    int magicPower = 0 ;
//    String name = "";

    Map<String, Object> Skills = new HashMap<>(Map.ofEntries(
            Map.entry("Atomic Blast", 22),             // High AoE burst
            Map.entry("Bleed", 12),                    // DoT effect
            Map.entry("World Cutting Slash", 28),      // High single-target crit attack
            Map.entry("Laser Beam", 18),               // Precise mid–high damage
            Map.entry("Energy Blast", 14),             // Standard ranged attack
            Map.entry("Blind", 8),                     // Low damage + accuracy debuff
            Map.entry("Heal", 16),                     // Mid-tier heal
            Map.entry("Hell Fire", 20),                // Burning DoT + AoE
            Map.entry("Cleave", 17),                   // Multi-target physical slash
            Map.entry("Stun", 10),                     // Low damage + control effect
            Map.entry("Shadow Strike", 15),            // Fast strike ignoring some defense
            Map.entry("Frost Nova", 16),               // Mid damage + slow effect
            Map.entry("Dragon Roar", 12),              // Debuffs enemy attack
            Map.entry("Meteor Crash", 25),             // Heavy AoE impact
            Map.entry("Thunder Lance", 18),            // Lightning pierce damage
            Map.entry("Spirit Shield", 14),            // Damage reduction buff
            Map.entry("Rage Boost", 10),               // Increase own attack temporarily
            Map.entry("Blood Drain", 13),              // Damage + small heal
            Map.entry("Phoenix Feather", 20),          // Strong heal + burn cleanse
            Map.entry("Void Rend", 26)                 // Late-game high magic burst
    ));

    int getHealth();

    int getLevel();

    int getMagicPower();

    String getName();

    ArrayList<String> getSkills();

    void decreaseHealth(int damage);

    void decreaseMagicPower(int magicPower);

    void increaseHealth(int health);

    void increaseMagicPower(int magicPower);

    public HashMap<String, Object> getCharacterInfo();
}
