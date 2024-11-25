import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DynamicSprite extends Sprite {

    public Boolean isWalking = false;
    private double speed;
    private final int spriteSheetNumberOfColumn;
    private int timeBetweenFrame;
    private Direction direction;
    private int attitude = 1;
    int iterator = 0;
    int spriteAlignmentX;
    int spriteAlignmentY;
    public DynamicSprite(BufferedImage spriteSheet, int x, int y, int w, int h, double speed, int spriteSheetNumberOfColumn, int timeBetweenFrame, Direction direction,int spriteAlignmentX,int spriteAlignmentY) {
        super(spriteSheet, x, y, w, h);

        this.speed = speed;
        this.spriteSheetNumberOfColumn = spriteSheetNumberOfColumn;
        this.timeBetweenFrame = timeBetweenFrame;
        this.direction= direction;
        this.spriteAlignmentX =spriteAlignmentX;
        this.spriteAlignmentY = spriteAlignmentY;

    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        this.attitude = direction.getFrameLineNumber();
    }

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

    private void move(){
        switch (direction){
            case NORTH ->this.y-=speed;
            case WEST ->this.x-=speed;
            case EAST -> this.x+=speed;
            case SOUTH -> this.y+=speed;



        }}

    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle2D.Double hitbox = new Rectangle2D.Double() ;

        switch (direction){
            case NORTH->hitbox.setRect(super.getHitBox().getX(),super.getHitBox().getY()+h/2-speed,super.getHitBox().getWidth(),super.getHitBox().getHeight()/2);
            case WEST -> hitbox.setRect(super.getHitBox().getX()-speed,super.getHitBox().getY()+h/2,super.getHitBox().getWidth(),super.getHitBox().getHeight()/2);
            case EAST -> hitbox.setRect(super.getHitBox().getX()+speed,super.getHitBox().getY()+h/2,super.getHitBox().getWidth(),super.getHitBox().getHeight()/2);
            case SOUTH -> hitbox.setRect(super.getHitBox().getX(),super.getHitBox().getY()+speed+h/2,super.getHitBox().getWidth(),super.getHitBox().getHeight()/2);
        }
        for (Sprite e : environment){
            if ((e != this )&&(hitbox.intersects(e.getHitBox()))&&e instanceof SolidSprite){
            System.out.println("Y"+e.y);
            System.out.println("X:"+e.x);

                return false;}


        }

        return true;
    }


    public boolean encounterEnemy;




    public void moveIfPossible(ArrayList<Sprite> environment){
        if (isMovingPossible(environment)){
            if (this.isWalking ==true){
            move();}
        }

    }


}
