import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SolidSprite extends Sprite {


    public SolidSprite(BufferedImage spriteSheet, int x, int y, int w, int h) {
        super(spriteSheet, x, y, w, h);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(spriteSheet,x,y,w,h,null,null);

    }}

