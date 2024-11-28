package main;

import main.entities.*;
import main.entities.physicalEntities.DynamicSprite;
import main.entities.physicalEntities.MonsterSprite;
import main.generators.Level;
import main.engines.GameEngine;
import main.engines.MonsterEngine;
import main.engines.PhysicsEngine;
import main.engines.RenderEngine;
import main.entities.HUD;
import main.entities.entitiesEnums.Direction;
import main.entities.entitiesEnums.Weapon;
import main.entities.Sprite;


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
    public Main() throws Exception{



        renderEngine = new RenderEngine();
        physicsEngine = new PhysicsEngine(new ArrayList<DynamicSprite>(),new ArrayList<Sprite>(),new ArrayList<MonsterSprite>());
        DynamicSprite player = new DynamicSprite(
                ImageIO.read(new File("./Game/Sprites/Characters/RedNinja3/SpriteSheet.png")),82,82,16,16, 7,4 ,2, Direction.EAST,4);
        MonsterSprite Samurai = new MonsterSprite(
                ImageIO.read(new File("./Game/Sprites/Characters/Samurai/SpriteSheet.png")),800,102,16,16, 5,4 ,2,Direction.EAST,"./Game/Loadables/Patterns/Flame.txt",30, Weapon.KATANA);
        MonsterSprite Tengu = new MonsterSprite(
                ImageIO.read(new File("./Game/Sprites/Characters/Tengu2/SpriteSheet.png")),400,300,16,16, 7,4 ,2,Direction.EAST,"./Game/Loadables/Patterns/Tengu.txt",60,Weapon.SAI);
        MonsterSprite Tengu2 = new MonsterSprite(
                ImageIO.read(new File("./Game/Sprites/Characters/Tengu2/SpriteSheet.png")),600,100,16,16, 7,4 ,2,Direction.EAST,"./Game/Loadables/Patterns/Tengu2.txt",60,Weapon.CLUB);
        renderEngine.addToRenderList(player);
        hud = new HUD(player,"./Game/Sprites/UI/FacesetBox.png","./Game/Sprites/Characters/RedNinja3/Faceset.png","./Game/Sprites/UI/heart.png");
        gameEngine = new GameEngine(player);
        monsterEngineSamurai = new MonsterEngine(Samurai);
        monsterEngineTengu = new MonsterEngine(Tengu);
        monsterEngineTengu2 = new MonsterEngine(Tengu2);
        renderEngine.addToRenderList(Samurai);
        renderEngine.addToRenderList(Tengu);
        renderEngine.addToRenderList(Tengu2);
        renderEngine.addKeyListener(gameEngine);
        renderEngine.addToRenderList(hud);

        physicsEngine.addToMoving(player);
        physicsEngine.addToMoving(Samurai);
        physicsEngine.addToMoving(Tengu);
        physicsEngine.addToMoving(Tengu2);


        Timer gameTimer = new Timer(50,(time)->{
                renderEngine.update();
                gameEngine.update();;
                physicsEngine.update();
                monsterEngineSamurai.update();
                monsterEngineTengu.update();
                monsterEngineTengu2.update();
                hud.update();
        });

        gameTimer.start();


        Level level1 = new Level("./Game/Levels/level1.txt");
        for (Displayable levelRendering :level1.getSpriteList()) {
            renderEngine.addToRenderList(levelRendering);
        }
        physicsEngine.setEnvironment(level1.getSolidSpriteList());

        System.out.println(level1.getSolidSpriteList());


        }




    public static void main (String[] args) throws Exception {
        Main main = new Main();
    }


}