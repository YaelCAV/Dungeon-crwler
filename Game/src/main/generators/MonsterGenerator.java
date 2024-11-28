package main.generators;

import main.entities.physicalEntities.MonsterSprite;
import main.engines.MonsterEngine;

import java.awt.image.BufferedImage;

public class MonsterGenerator {
    private int numberInLevel;
    private int levelNumber;
    private final MonsterSprite sprite;
    private final MonsterEngine engine;
    private BufferedImage spriteImage;

    public MonsterGenerator(MonsterSprite sprite, MonsterEngine engine, int levelNumber, int numberInLevel) {
        this.sprite = sprite;
        this.engine = engine;
        this.levelNumber = levelNumber;
        this.numberInLevel = numberInLevel;
    }
}
