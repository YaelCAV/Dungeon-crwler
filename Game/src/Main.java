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


    public Main() throws Exception{



        renderEngine = new RenderEngine();
        physicsEngine = new PhysicsEngine(new ArrayList<DynamicSprite>(),new ArrayList<Sprite>());
        DynamicSprite player = new DynamicSprite(
                ImageIO.read(new File("./Game/Sprites/Characters/RedNinja3/SpriteSheet.png")),82,82,16,16, 5,4 ,2,Direction.EAST,0,0);
        renderEngine.addToRenderList(player);
        gameEngine = new GameEngine(player);
        renderEngine.addKeyListener(gameEngine);


        physicsEngine.addToMoving(player);


        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(50,(time)-> physicsEngine.update());
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();
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