import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    //JFrame displayZoneFrame;
    RenderEngine renderEngine;
    PhysicsEngine physicsEngine;
    GameEngine gameEngine;
    MonsterEngine monsterEngineFlame;
    MonsterEngine monsterEngineTengu;

    public Main() throws Exception{



        renderEngine = new RenderEngine();
        physicsEngine = new PhysicsEngine(new ArrayList<DynamicSprite>(),new ArrayList<Sprite>(),new ArrayList<MonsterSprite>());
        DynamicSprite player = new DynamicSprite(
                ImageIO.read(new File("./Game/Sprites/Characters/RedNinja3/SpriteSheet.png")),82,82,16,16, 5,4 ,2,Direction.EAST,0,0);
        MonsterSprite flame = new MonsterSprite(
                ImageIO.read(new File("./Game/Sprites/SpriteSheet.png")),140,102,16,16, 5,4 ,2,Direction.EAST,0,0,"./Game/Patterns/Flame.txt");
        MonsterSprite Tengu = new MonsterSprite(
                ImageIO.read(new File("./Game/Sprites/Characters/Tengu2/SpriteSheet.png")),200,120,16,16, 7,4 ,2,Direction.EAST,0,0,"./Game/Patterns/Tengu.txt");
        renderEngine.addToRenderList(player);
        gameEngine = new GameEngine(player);
        monsterEngineFlame = new MonsterEngine(flame);
        monsterEngineTengu = new MonsterEngine(Tengu);
        renderEngine.addToRenderList(flame);
        renderEngine.addToRenderList(Tengu);
        renderEngine.addKeyListener(gameEngine);


        physicsEngine.addToMoving(player);
        physicsEngine.addToMoving(flame);
        physicsEngine.addToMoving(Tengu);


        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(50,(time)-> physicsEngine.update());
        Timer monsterTimer = new Timer(50,(time)-> monsterEngineFlame.update());
        Timer monsterTimer2 = new Timer(50,(time)-> monsterEngineTengu.update());
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();
        monsterTimer.start();
        monsterTimer2.start();

        Level level1 = new Level("./Game/Levels/level1.txt");
        for (Displayable levelRendering :level1.getSpriteList()) {
            renderEngine.addToRenderList(levelRendering);
        }
        physicsEngine.setEnvironment(level1.getSolidSpriteList());


        }




    public static void main (String[] args) throws Exception {
        Main main = new Main();
    }


}