package main.generators;

import main.entities.Displayable;
import main.entities.Sprite;

import java.util.ArrayList;

public interface LevelGeneration {

    public ArrayList<Sprite> getSolidSpriteList();
    public ArrayList<Displayable> getSpriteList();
}
