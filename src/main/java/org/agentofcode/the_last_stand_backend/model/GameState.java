package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class GameState {
    private Hero heros;
    private Boss boss;
    private String userName;

    public GameState() {}

    public GameState(Hero heros, Boss boss, String userName) {
        this.heros = heros;
        this.boss = boss;
        this.userName = userName;
    }

    public Hero getPlayer() {
        return heros;
    }

    public Boss getBoss() {
        return boss;
    }

    public String getUserName(){
        return userName;
    }

    public HashMap<String, Object> getGameState(){
        HashMap<String, Object> state = new HashMap<>();
        state.put("playerName", heros.getName());
        state.put("playerLevel", heros.getLevel());
        state.put("playerHP", heros.getHealth());
        state.put("playerMana", heros.getMana());
        state.put("playerAbilities", heros.getAbilities());
        state.put("playerMaxHP", heros.getMaxHealth());
        state.put("playerMaxMana", heros.getMaxMana());
        state.put("enemyName", boss.getName());
        state.put("enemyLevel", boss.getLevel());
        state.put("enemyHP", boss.getHealth());
        state.put("enemyMana", boss.getMana());
        state.put("enemyAbilities", boss.getAbilities());
        state.put("enemyAbilityUsed", boss.getAbilityUsed());
        state.put("enemyMaxHP", boss.getMaxHealth());
        state.put("enemyMaxMana", boss.getMaxMana());

        return state;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "player=" + heros.getCharacterInfo() +
                ", boss=" + boss.getCharacterInfo() +
                '}';
    }
}
