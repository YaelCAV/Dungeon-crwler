import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HUD extends JPanel implements Engine,Displayable{
    private String boxPath;
    private String faceSetPath;
    private String heartPath;
    private static ArrayList<Sprite> renderList;
    private BufferedImage boxSprite;
    private BufferedImage faceSetSprite;
    private BufferedImage heartSprite;
    public HUD( String boxPath, String faceSetPath, String heartPath) {
        this.boxPath = boxPath;
        this.faceSetPath = faceSetPath;
        this.heartPath = heartPath;
    }

    public void update(){

    }

    public void paintHUD(Graphics g){
        super.paintComponent(g);
        g.drawImage(heartSprite,0,-640,16,16,null,null);
    }

    @Override
    public void draw(RenderEngine r) {
        r.remove(this);
        r.add(this);
    }
}
