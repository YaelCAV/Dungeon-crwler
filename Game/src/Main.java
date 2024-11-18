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

        Sprite test = new Sprite(
                ImageIO.read(new File("./Sprites/tree.png")),0,0,64,64);
        renderEngine.addToRenderList(test);


    }

    public static void main (String[] args) throws Exception {
        Main main = new Main();
    }


}