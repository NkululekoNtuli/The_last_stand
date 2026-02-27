package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class GameState {
    private String userName;
    private Hero hero;
    private int heroCurrHp;
    private int heroCurrMana;
    private int heroCurrLv = 0;
    private Boss boss;
    private int bossCurrHp;
    private int bossCurrMana;
    private int bossCurrLv = 0;
    private int MAX_LV = 3;


    public GameState() {}

    public GameState(Hero hero, Boss boss, String userName) {
        this.hero = hero;
        this.heroCurrHp = hero.getHealth();
        this.heroCurrMana = hero.getMana();
        this.boss = boss;
        this.bossCurrHp = boss.getHealth();
        this.bossCurrMana = boss.getMana();
        this.userName = userName;
        this.heroCurrLv = hero.getLevel();
    }

    public Hero getPlayer() {
        return hero;
    }

    public Boss getBoss() {
        return boss;
    }

    public String getUserName(){
        return userName;
    }


    public int getHeroCurrHp() {
        return heroCurrHp;
    }

    public int getHeroCurrMana() {
        return heroCurrMana;
    }

    public int getHeroCurrLV() {
        return heroCurrLv;
    }

    public int getBossCurrHP() {
        return bossCurrHp;
    }

    public int getBossCurrMana() {
        return bossCurrMana;
    }

    public int getBossCurrLV() {
        return bossCurrLv;
    }

    public void updateMana(int mana, int target) {
        if (target == 0) this.heroCurrMana -= mana;
        else this.bossCurrMana -= mana;
    }

    public void updateHealth(int hp, int target) {
        System.out.println("testing health");
        if (target == 0) this.heroCurrHp -= hp;
        else this.bossCurrHp -= hp;
    }

    public void updateLevel(int lv, int target) {
        if (target == 0 && heroCurrLv < 4) {
            this.heroCurrLv += lv;
        }

        if(target == 1 && bossCurrLv < 4) {
            this.bossCurrLv += lv;
        }
    }




    public HashMap<String, Object> getGameState(){
        HashMap<String, Object> state = new HashMap<>();
        state.put("playerName", this.hero.getName());
        state.put("playerLevel", this.hero.getLevel());
        state.put("playerHP", heroCurrHp);
        state.put("playerMana", this.heroCurrMana);
        state.put("playerAbilities", this.hero.getAbilities());
        state.put("playerMaxHP", this.hero.getHealth());
        state.put("playerMaxMana", this.hero.getMana());
        state.put("enemyName", this.boss.getName());
        state.put("enemyLevel", bossCurrLv);
        state.put("enemyHP", bossCurrHp);
        state.put("enemyMana", bossCurrMana);
        state.put("enemyAbilities", this.boss.getAbilities());
        state.put("enemyAbilityUsed", this.boss.getAbilityUsed());
        state.put("enemyMaxHP", this.boss.getHealth());
        state.put("enemyMaxMana", this.boss.getMana());

        return state;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "player=" + hero.getCharacterInfo() +
                ", boss=" + boss.getCharacterInfo() +
                '}';
    }

    public void updateGameState(Hero hero, Boss boss) {
        this.hero = hero;
        this.boss = boss;
    }
}
