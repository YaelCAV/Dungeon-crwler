package main.entities;

import main.engines.RenderEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
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
        this.setOpaque(false);
    }
    public Rectangle2D.Double getHitBox(){
        return new Rectangle2D.Double(x,y,w,h);
    }
    public boolean intersection(Rectangle2D.Double hitBox){ return this.getHitBox().intersects(hitBox);}

    @Override
    public void draw(RenderEngine r) {
        r.remove(this);
        r.add(this);

    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.scale(1, 1);
        g2.translate(x, y);
        g2.drawRenderedImage(spriteSheet,null);


    }


    public int getXcoords() {
        return x;
    }


    public int getYcoords() {
        return y;
    }
}
