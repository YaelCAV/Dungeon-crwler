package main.engines;

import main.entities.entitiesEnums.*;
import main.entities.physicalEntities.DynamicSprite;
import main.entities.physicalEntities.MonsterSprite;

public class MonsterEngine implements Engine {


    private final MonsterSprite monster;
    private final DynamicSprite player;

    public MonsterEngine(MonsterSprite monster, DynamicSprite player) {
        this.monster = monster;
        this.player = player;

    }


    private void patternProgress() {


        switch (monster.pattern.charAt(monster.patternTracker)) {
            case 'N' -> monster.setDirection(Direction.NORTH);
            case 'S' -> monster.setDirection(Direction.SOUTH);
            case 'E' -> monster.setDirection(Direction.EAST);
            case 'W' -> monster.setDirection(Direction.WEST);
            case 'm' -> monster.addStep();
            case 'h' -> {
                monster.setWalking(false);
                monster.isTracking = false;
            }
            case 'a' -> monster.attack();
            case 'P' -> {
                monster.track(player);
                monster.addStep();
                monster.isTracking = true;
            }
        }
        monster.patternTracker = (monster.patternTracker + 1) % monster.pattern.length();


    }

    public void update() {
        this.patternProgress();
        monster.truceTime();
        monster.checkHealth();


    }

}
