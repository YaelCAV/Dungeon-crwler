import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    //JFrame displayZoneFrame;
    RenderEngine renderEngine;




    public Main() throws Exception{



        renderEngine = new RenderEngine();

        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());

        //displayZoneFrame.getContentPane().add(renderEngine);

        renderTimer.start();

        DynamicSprite test = new DynamicSprite(
                ImageIO.read(new File("./Sprites/RagMan.png")),0,0,32,32, 1,4 ,2,Direction.EAST,0,110);
        renderEngine.addToRenderList(test);
        GameEngine game = new GameEngine(test);
        renderEngine.addKeyListener(game);
    }

    public static void main (String[] args) throws Exception {
        Main main = new Main();
    }


}