package main.entities.physicalEntities;

import main.entities.Sprite;
import main.entities.entitiesEnums.CharacterState;
import main.entities.entitiesEnums.Direction;
import main.entities.entitiesEnums.Weapon;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

interface Characters {

    void checkHealth();

    void setDirection(Direction direction);

    CharacterState getCharacterState();

    void moveIfPossible(ArrayList<Sprite> environment);

    void truceTime();

    void getDamaged(int damage);

}
