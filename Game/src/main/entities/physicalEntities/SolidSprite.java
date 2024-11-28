package main.entities.physicalEntities;

import main.entities.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SolidSprite extends Sprite {


    public SolidSprite(BufferedImage spriteSheet, int x, int y, int w, int h) {
        super(spriteSheet, x, y, w, h);
    }

    @Override
    protected void paintComponent(Graphics g){

        Graphics2D g2 = (Graphics2D) g;

        g2.scale(1, 1);
        g2.translate(x, y);
        g2.drawRenderedImage(spriteSheet,null);


    }}

