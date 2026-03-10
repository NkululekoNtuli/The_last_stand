package org.agentofcode.the_last_stand_backend.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class GameState {
    private String userName;
    private Hero hero;
    private BaseCharacter hero2;
//    private int heroCurrLv = 0;
    private Boss boss;
    private int MAX_LV = 3;


    public GameState() {}

    public GameState(Hero hero, Boss boss, String userName) {
        this.hero = hero;
        this.hero2 = new BaseCharacter(hero.getName(), hero.getAbilities(), hero.getHealth(), hero.getLevel(),
                hero.getMana());
        this.boss = boss;
        this.userName = userName;
//        this.heroCurrLv = hero.getLevel();
    }

    public BaseCharacter getPlayer() {
        return hero2;
    }

    public Boss getBoss() {
        return boss;
    }

    public String getUserName(){
        return userName;
    }

//
//    public int getHeroCurrHp() {
//        return heroCurrHp;
//    }
//
//    public int getHeroCurrMana() {
//        return heroCurrMana;
//    }

//    public int getHeroCurrLV() {
//        return heroCurrLv;
//    }

//    public int getBossCurrHP() {
//        return bossCurrHp;
//    }

//    public int getBossCurrMana() {
//        return bossCurrMana;
//    }

//    public int getBossCurrLV() {
//        return bossCurrLv;
//    }

//    public void updateMana(int mana, int target) {
//        if (target == 0) this.heroCurrMana -= mana;
//
//    }

//    public void updateHealth(int hp, int target) {
//        if (target == 0) this.heroCurrHp -= hp;
//        else this.boss.decreaseHealth(hp);
//    }

//    public void updateLevel(int lv, int target) {
//        if (target == 0 && heroCurrLv < 4) {
//            this.heroCurrLv += lv;
//        }
//
//        if(target == 1 && bossCurrLv < 4) {
//            this.bossCurrLv += lv;
//        }
//    }




    public HashMap<String, Object> getGameState(){
        HashMap<String, Object> state = new HashMap<>();
        state.put("playerName", this.hero2.getName());
        state.put("playerLevel", this.hero2.getLevel());
        state.put("playerHP", this.hero2.getHealth());
        state.put("playerMana", this.hero2.getMana());
        state.put("playerAbilities", this.hero2.getAbilities());
        state.put("playerMaxHP", this.hero2.getMaxHealth());
        state.put("playerMaxMana", this.hero2.getMaxMana());
        state.put("enemyName", this.boss.getName());
        state.put("enemyLevel", this.boss.getLevel());
        state.put("enemyHP", this.boss.getHealth());
        state.put("enemyMana", this.boss.getMana());
        state.put("enemyAbilities", this.boss.getAbilities());
        state.put("enemyAbilityUsed", this.boss.getAbilityUsed());
        state.put("enemyMaxHP", this.boss.getMaxHealth());
        state.put("enemyMaxMana", this.boss.getMaxMana());
        return state;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "player=" + hero.getCharacterInfo() +
                ", boss=" + boss.getCharacterInfo() +
                '}';
    }

    public void updateGameState(BaseCharacter hero, Boss boss) {
        this.hero2 = hero;
        this.boss = boss;
    }
}
