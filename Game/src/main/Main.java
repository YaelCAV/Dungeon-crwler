package main;

import main.entities.*;
import main.entities.entitiesEnums.Decorations;
import main.entities.physicalEntities.DynamicSprite;
import main.entities.physicalEntities.MonsterSprite;
import main.generators.DecorationsGenerator;
import main.generators.Level;
import main.engines.GameEngine;
import main.engines.MonsterEngine;
import main.engines.PhysicsEngine;
import main.engines.RenderEngine;
import main.entities.HUD;
import main.entities.entitiesEnums.Direction;
import main.entities.entitiesEnums.Weapon;
import main.entities.Sprite;
import main.utils.JsonFileReader;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    //JFrame displayZoneFrame;
    RenderEngine renderEngine;
    PhysicsEngine physicsEngine;
    GameEngine gameEngine;
    MonsterEngine monsterEngineSamurai;
    MonsterEngine monsterEngineTengu;
    MonsterEngine monsterEngineTengu2;
    HUD hud;

    public Main() throws Exception {

        //Entities construction
        DynamicSprite player = new DynamicSprite(ImageIO.read(new File("./Game/Sprites/Characters/RedNinja3/SpriteSheet.png")), 82, 82, 16, 16, 7, 4, 2, Direction.EAST, 4);
        MonsterSprite Samurai = new MonsterSprite(ImageIO.read(new File("./Game/Sprites/Characters/Samurai/SpriteSheet.png")), 800, 102, 16, 16, 5, 4, 2, Direction.EAST, "./Game/Loadables/Patterns/Flame.txt", 30, Weapon.KATANA);
        MonsterSprite Tengu = new MonsterSprite(ImageIO.read(new File("./Game/Sprites/Characters/Tengu2/SpriteSheet.png")), 400, 300, 16, 16, 7, 4, 2, Direction.EAST, "./Game/Loadables/Patterns/Tengu.txt", 60, Weapon.SAI);
        MonsterSprite Tengu2 = new MonsterSprite(ImageIO.read(new File("./Game/Sprites/Characters/Tengu2/SpriteSheet.png")), 600, 100, 16, 16, 7, 4, 2, Direction.EAST, "./Game/Loadables/Patterns/Tengu2.txt", 60, Weapon.CLUB);
        hud = new HUD(player, "./Game/Sprites/UI/FacesetBox.png", "./Game/Sprites/Characters/RedNinja3/Faceset.png", "./Game/Sprites/UI/heart.png");

       //Engines creation
        renderEngine = new RenderEngine();
        physicsEngine = new PhysicsEngine(new ArrayList<DynamicSprite>(), new ArrayList<Sprite>(), new ArrayList<MonsterSprite>());
        gameEngine = new GameEngine(player);
        monsterEngineSamurai = new MonsterEngine(Samurai);
        monsterEngineTengu = new MonsterEngine(Tengu);
        monsterEngineTengu2 = new MonsterEngine(Tengu2);

       //Setting up renderEngine
        renderEngine.addKeyListener(gameEngine);
        renderEngine.addToRenderList(player);
        renderEngine.addToRenderList(Samurai);
        renderEngine.addToRenderList(Tengu);
        renderEngine.addToRenderList(Tengu2);
        renderEngine.addToRenderList(hud);

        //flimsy physics initialized
        physicsEngine.addToMoving(player);
        physicsEngine.addToMoving(Samurai);
        physicsEngine.addToMoving(Tengu);
        physicsEngine.addToMoving(Tengu2);


        Timer gameTimer = new Timer(50, (time) -> {
            renderEngine.update();
            gameEngine.update();
            physicsEngine.update();
            monsterEngineSamurai.update();
            monsterEngineTengu.update();
            monsterEngineTengu2.update();
            hud.update();
        });

        gameTimer.start();

        //Level Loading
        Level level1 = new Level("./Game/Loadables/Levels/Level1/level1.txt");
       // DecorationsGenerator deco1 = new DecorationsGenerator("./Game/Loadables/Levels/Level1/level1deco.txt","./Game/Sprites/Terrain/TilesetNature.png");
        for (Displayable levelRendering : level1.getSpriteList()) {
            renderEngine.addToRenderList(levelRendering);
        }
        //  for (Displayable decoRendering : deco1.getSpriteList()) {
        //  renderEngine.addToRenderList(decoRendering);
        //}
        physicsEngine.setEnvironment(level1.getSolidSpriteList());


    }


    public static void main(String[] args) throws Exception {
        Main main = new Main();
    }


}