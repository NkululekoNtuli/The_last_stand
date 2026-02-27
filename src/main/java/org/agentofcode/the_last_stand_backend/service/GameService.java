package org.agentofcode.the_last_stand_backend.service;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.*;
import org.agentofcode.the_last_stand_backend.model.*;
import org.agentofcode.the_last_stand_backend.model.Character;
import org.agentofcode.the_last_stand_backend.repository.HeroRepository;
import org.agentofcode.the_last_stand_backend.repository.UserRepository;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class GameService {

    private Hero hero;
    private Boss boss;
    private Dotenv dotenv = Dotenv.load();
    private String AI_API_KEY = dotenv.get("AI_API_KEY");
    private String AI_API_BASE_URL = dotenv.get("AI_API_URL");
    private ConcurrentHashMap<String, GameState> gameStates = new ConcurrentHashMap<>();
    private HeroRepository heroRepository;
    private UserRepository userRepository;
    private UserService userService;
    private String userName;

    public GameService(HeroRepository heroRepository, UserService userService) {
        this.heroRepository = heroRepository;
        this.userService = userService;
    }

    public String promptAI() {
        ArrayList<String> bossSkills = new ArrayList<>();
        ArrayList<Ability>  testing = Character.abilities;
        testing.forEach(ability -> {
            bossSkills.add(ability.getName());
        });

        String prompt = "You are the boss in this situation, choose a skill to play based on the given information";
        String context = "This is a turn based game. player status is " + hero.getCharacterInfo() +
                " and the boss status is " + boss.getCharacterInfo()  + "Respond only with the skill name.";

        OkHttpClient client = new OkHttpClient();
        HttpUrl url = HttpUrl.parse(AI_API_BASE_URL)
                .newBuilder()
                .addQueryParameter("key", AI_API_KEY)
                .addQueryParameter("prompt", prompt)
                .addQueryParameter("context",  context)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String[] bodyComp = response.body().string().replace("}", "").split(":");
            String bossAttack = bodyComp[bodyComp.length - 1].replace("\"", "");

            return bossAttack;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void creatGameState(String userId, Hero hero, String userName){
        this.hero = hero;
        this.boss = creatBoss();
        this.gameStates.put(userId, new GameState(this.hero, this.boss, userName));
    }

    public Boss creatBoss(){
        ArrayList<Ability> bossAbilities = new ArrayList<>();
        ArrayList<Ability> abilities = this.hero.getAbilities();

        for (Ability ability : abilities) {
            int slot = ability.getSlot();

            switch (slot){
                case 0 -> bossAbilities.add(ability.getCounterElement());// passive
                case 1 -> bossAbilities.add(ability.getCounterElement());// primary
                case 2 -> bossAbilities.add(ability.getCounterElement());// secondary
                case 3 -> bossAbilities.add(ability.getCounterElement());// tertiary
                case 4 -> bossAbilities.add(ability.getCounterElement());// ultimate
            }
        }

        return new Boss("Demon General", bossAbilities, 500, 0, 600);
    }

    public void executeSkill(String userId, Ability ability){
        GameState game = this.gameStates.get(userId);

        hero = game.getPlayer();
        boss = game.getBoss();
        System.out.println("checking her info: "+ hero.getHeroInfo());
//        System.out.println("checking hero info from base " + hero.getCharacterInfo());
        game.updateMana(ability.getManaCost(), 0);

        if (ability.getEffect().equalsIgnoreCase("cleans")){
            game.updateHealth(-ability.getPower(), 0);
        }else {
            game.updateHealth(ability.getPower(), 1);
        }
        upgradeLevel(hero, boss);
//        String move = promptAI();
//        executeBossMove(move);
        executeBossMove(game);
//        upgradeLevel(boss, hero);
        game.updateGameState(hero, boss);
    }

    public void upgradeLevel(Character player, Character enemy) {
        System.out.println("testing upgrade");

        try {
            double damagePercent = ((double) enemy.getDamageTaken() / enemy.getMaxHealth()) * 100;

            if (damagePercent > (100.0 / 2)) { // >50% damage
                System.out.println("testing level 3");
                int increase = enemy.getDamageTaken() / 2;
                player.increaseHealth(increase);
                player.increaseMaxHealth(increase);
                player.increaseMagicPower(increase);
                player.increaseMaxMana(increase);
            } else if (damagePercent > (100.0 / 3)) { // >33% damage
                System.out.println("testing level 2");
                int increase = enemy.getDamageTaken() / 3;
                player.increaseHealth(increase);
                player.increaseMaxHealth(increase);
                player.increaseMagicPower(increase);
                player.increaseMaxMana(increase);
            } else if (damagePercent > (100.0 / 7)) { // >14% damage
                System.out.println("testing level 1");
                int increase = enemy.getDamageTaken() / 4;
                player.increaseHealth(increase);
                player.increaseMaxHealth(increase);
                player.increaseMagicPower(increase);
                player.increaseMaxMana(increase);
            } else {
                System.out.println("No upgrade");
            }

        } catch (ArithmeticException e) {
            // enemy.getMaxHealth() was 0
            System.out.println("No damage taken or enemy has zero max health.");
        }
    }

    public void executeBossMove(GameState game) {
        //implement boss logic
        Random random = new Random();

        ArrayList<Ability> abilities = boss.getAbilities();
        Ability  move = abilities.get(random.nextInt(abilities.size()));
        boss.setAbilityUsed(move.getName());
        game.updateHealth(move.getPower(), 0);
    }

    public Map<String, Object> gameState(String userId){
        return gameStates.get(userId).getGameState();
    }

    public void endGame(String userId) {
        GameState gameState = gameStates.get(userId);
        gamePoints(gameState.getUserName());
        gameStates.remove(userId);
    }

    private void gamePoints(String name){
        if (hero.getHealth() > boss.getHealth()) {
            userService.updateRating(10, name);
        } else {
            userService.updateRating(2, name);
        }
    }
}