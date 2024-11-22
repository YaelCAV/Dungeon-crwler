import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Sprite extends JPanel implements Displayable {

    protected BufferedImage spriteSheet;
    protected int x;
    protected int y;
    protected int w;
    protected int h;

    public Sprite(BufferedImage spriteSheet, int x, int y, int w, int h) {

        this.spriteSheet = spriteSheet;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void draw(RenderEngine r) {
        r.remove(this);
        r.add(this);

    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);


    }
}
