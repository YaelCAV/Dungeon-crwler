import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MonsterSprite extends Sprite{


    private double speed;
    private int spriteSheetNumberOfColumn;
    private int timeBetweenFrame;
    private Direction direction;
    private int attitude = 1;
    int iterator = 0;
    int spriteAlignmentX;
    int spriteAlignmentY;
    public boolean isWalking;
    public int patternTracker = 0;
    BufferedReader pattern;

    public MonsterSprite(BufferedImage spriteSheet, int x, int y, int w, int h, double speed,int spriteSheetNumberOfColumn, int timeBetweenFrame, Direction direction, int spriteAlignmentX, int spriteAlignmentY, String patternPath) {
        super(spriteSheet, x, y, w, h);
        this.spriteSheetNumberOfColumn = spriteSheetNumberOfColumn;

        this.speed = speed;
        this.timeBetweenFrame = timeBetweenFrame;
        this.direction = direction;
        this.spriteAlignmentX = spriteAlignmentX;
        this.spriteAlignmentY = spriteAlignmentY;

        try {
            this.pattern = new BufferedReader(new FileReader(patternPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        this.attitude = direction.getFrameLineNumber();
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle2D.Double hitbox = new Rectangle2D.Double() ;
        switch (direction){
            case NORTH->hitbox.setRect(super.getHitBox().getX()+w/5,super.getHitBox().getY()+h/2-speed,super.getHitBox().getWidth()*3/5,super.getHitBox().getHeight()/2);
            case WEST -> hitbox.setRect(super.getHitBox().getX()+w/5-speed,super.getHitBox().getY()+h/2,super.getHitBox().getWidth()*3/5,super.getHitBox().getHeight()/2);
            case EAST -> hitbox.setRect(super.getHitBox().getX()+w/5+speed,super.getHitBox().getY()+h/2,super.getHitBox().getWidth()*3/5,super.getHitBox().getHeight()/2);
            case SOUTH -> hitbox.setRect(super.getHitBox().getX()+w/5,super.getHitBox().getY()+h/2+speed,super.getHitBox().getWidth()*3/5,super.getHitBox().getHeight()/2);
        }
        for (Sprite e : environment){
            if ((e != this )&&(hitbox.intersects(e.getHitBox()))&&e instanceof SolidSprite){

                return false;}


        }

        return true;
    }


    public void moveIfPossible(ArrayList<Sprite> environment){
        if (isMovingPossible(environment)){
            if (this.isWalking ==true){
                move();}
        }}



    private void move(){
        switch (direction){
            case NORTH ->this.y-=speed;
            case WEST ->this.x-=speed;
            case EAST -> this.x+=speed;
            case SOUTH -> this.y+=speed;



        }}
    @Override
    protected void paintComponent(Graphics g) {

        //super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        g2.scale(2, 2);



        g.drawImage(spriteSheet.getSubimage((attitude-1)*w+spriteAlignmentX,
                h*iterator+spriteAlignmentY,
                w,h), x, y, w, h, null, null);
        if(isWalking){
            iterator = (iterator+1)%spriteSheetNumberOfColumn;
        }}


}