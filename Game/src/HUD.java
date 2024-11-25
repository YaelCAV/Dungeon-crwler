import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HUD extends JPanel implements Engine,Displayable{
    private String boxPath;
    private String faceSetPath;
    private String heartPath;
    private static ArrayList<Sprite> renderList;
    private BufferedImage boxSprite;
    private BufferedImage faceSetSprite;
    private BufferedImage heartSprite;
    private int presentHeart;
    private int xBox,yBox,xFace,yFace,xHeart,yHeart;
    private DynamicSprite player;
    public HUD(DynamicSprite player, String boxPath, String faceSetPath, String heartPath) {
        this.boxPath = boxPath;
        this.faceSetPath = faceSetPath;
        this.heartPath = heartPath;
        this.player = player;
        try{
            this.boxSprite = ImageIO.read(new File(boxPath));
            this.faceSetSprite = ImageIO.read(new File(faceSetPath));
            this.heartSprite = ImageIO.read(new File(heartPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(){
        this.presentHeart =player.health;
        if (presentHeart< 0){presentHeart=0;}
    }

    public void paintHUD(Graphics g){
        super.paintComponent(g);
        g.drawImage(heartSprite,0,-640,16,16,null,null);

    }


    protected void paintComponent(Graphics g) {


        Graphics2D g2=(Graphics2D)g;
        g2.translate(0,this.getHeight()-64*2-14);
        g2.scale(3, 3);
        g2.drawRenderedImage(boxSprite,null);

        g2.scale(1, 1);
        g2.translate(4,4);

        g2.drawRenderedImage(faceSetSprite,null);

        g2.drawRenderedImage(heartSprite.getSubimage(presentHeart*16,0,16,16),null);
          //      h*iterator+spriteAlignmentY,
            //    w,h), null);
          }
    @Override
    public void draw(RenderEngine r) {
        r.remove(this);
        r.add(this);
    }


}
