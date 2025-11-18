package org.agentofcode.the_last_stand_backend.service;

import org.agentofcode.the_last_stand_backend.model.*;
import org.agentofcode.the_last_stand_backend.model.Character;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class GameService {

    private static Player player;
    private static Boss boss;

    public static void main(String[] args) {
        boolean payersTurn = true;


        while (player.getHealth() > 0 &&  boss.getHealth() > 0){

            if (payersTurn) {
             // execute players move
             payersTurn = false;
            }else {
                //execute boss move
            }
        }
    }

    public GameService(Player player, Boss boss){
        player = player;
        boss =boss;
    }

    public String gameIntro(){
        return "";
    }

    public Boss creatBoss(Player player){
        return new Boss();
    }

    public String executeSkill(String skill){
        return "boss action";
    }

    public String getBossLine() {
        return "";
    }

    public String getSkillInfo() {
        return "";
    }

    public String getOutro() {
        return "";
    }

    public void upgradeLevel(Character player){
        //upgrade level
    }

    public void concede() {
        //implement mp check
    }


    public void executeBossMove() {
        //implement boss logic

    }

}
