import javax.swing.*;
import java.awt.*;

public class Sprite extends JPanel implements Displayable {

    private Image spriteSheet;
    private int x;
    private int y;
    private int width;
    private int height;

    public Sprite(Image spriteSheet, int x, int y, int width, int height) {

        this.spriteSheet = spriteSheet;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(RenderEngine r) {
        r.remove(this);
        r.add(this);

    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(spriteSheet,x,y,width,height,null,null);

    }
}
