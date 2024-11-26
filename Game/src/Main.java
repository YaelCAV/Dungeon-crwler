import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
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
    HUD hud;
    public Main() throws Exception{



        renderEngine = new RenderEngine();
        physicsEngine = new PhysicsEngine(new ArrayList<DynamicSprite>(),new ArrayList<Sprite>(),new ArrayList<MonsterSprite>());
        DynamicSprite player = new DynamicSprite(
                ImageIO.read(new File("./Game/Sprites/Characters/RedNinja3/SpriteSheet.png")),82,82,16,16, 7,4 ,2,Direction.EAST,4);
        MonsterSprite flame = new MonsterSprite(
                ImageIO.read(new File("./Game/Sprites/Beast.png")),800,102,16,16, 5,4 ,2,Direction.EAST,0,0,"./Game/Patterns/Flame.txt");
        MonsterSprite Tengu = new MonsterSprite(
                ImageIO.read(new File("./Game/Sprites/Characters/Tengu2/SpriteSheet.png")),400,300,16,16, 7,4 ,2,Direction.EAST,0,0,"./Game/Patterns/Tengu.txt");
        renderEngine.addToRenderList(player);
        hud = new HUD(player,"./Game/Sprites/UI/FacesetBox.png","./Game/Sprites/Characters/RedNinja3/Faceset.png","./Game/Sprites/UI/heart.png");
        gameEngine = new GameEngine(player);
        monsterEngineFlame = new MonsterEngine(flame);
        monsterEngineTengu = new MonsterEngine(Tengu);
        renderEngine.addToRenderList(flame);
        renderEngine.addToRenderList(Tengu);
        renderEngine.addKeyListener(gameEngine);
        renderEngine.addToRenderList(hud);

        physicsEngine.addToMoving(player);
        physicsEngine.addToMoving(flame);
        physicsEngine.addToMoving(Tengu);


        Timer gameTimer = new Timer(50,(time)->{
                renderEngine.update();
                gameEngine.update();;
                physicsEngine.update();
                monsterEngineFlame.update();
                monsterEngineTengu.update();
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