import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DynamicSprite extends Sprite {

    public Boolean isWalking = false;
    public Boolean isRunning = false;
    private double speed;
    private double bufferSpeed = speed;
    private double runningSpeed;
    private final int spriteSheetNumberOfColumn;
    private int timeBetweenFrame;
    private int invincibilityBuffer= 0;
    private Direction direction;
    private CharacterState characterState = CharacterState.IDLE;
    private int attitude = 1;
    int iterator = 0;
    public int health;
    public int idleBuffer=0;


    public DynamicSprite(BufferedImage spriteSheet, int x, int y, int w, int h, double speed, int spriteSheetNumberOfColumn, int timeBetweenFrame, Direction direction,int health) {
        super(spriteSheet, x, y, w, h);

        this.speed = speed;
        this.spriteSheetNumberOfColumn = spriteSheetNumberOfColumn;
        this.timeBetweenFrame = timeBetweenFrame;
        this.direction= direction;
        this.health = health;
        this.runningSpeed =speed*1.6;
    }

    public void setRunning(Boolean running) {
        isRunning = running;
    }

    public void runningSpeed(){
        if(this.isRunning){
        bufferSpeed = runningSpeed;}
        else {bufferSpeed = speed;}
    }


    public void setDirection(Direction direction) {
        this.direction = direction;
        this.attitude = direction.getFrameLineNumber();
    }


    @Override
    protected void paintComponent(Graphics g) {

        //super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        g2.translate(x,y);
        g2.scale(2, 2);

        switch(characterState){
            case WALKING :{
                g2.drawRenderedImage(spriteSheet.getSubimage((attitude-1)*w,
                        h*iterator,
                        w,h), null);
                if(isWalking){
                    iterator = (iterator+1)%spriteSheetNumberOfColumn;
                }}
                break;
            case IDLE:
                //g2.drawRenderedImage(spriteSheet.getSubimage((attitude-1)*w,
                     //   h*iterator,
                     //   w,h), null);
                break;
            case ATTACKING: {
                g2.drawRenderedImage(spriteSheet.getSubimage((attitude - 1) * w,
                        h * 4, w, h), null);

                 }
                break;
            case DEAD:{g2.drawRenderedImage(spriteSheet.getSubimage(0,16*6,w,h),null);}

            break;

            case JUMPING:{}
            break;

        }




    }
    public void checkHealth(){
        if( health <= 0){
            this.characterState = CharacterState.DEAD;
        }
    }
    public void attack(){
        this.characterState = CharacterState.ATTACKING;
        System.out.println(this.characterState);

    }
    public void idle(){

    }

    public void setWalking(Boolean walking) {

        isWalking = walking;
    }

    private void move(){
        switch (direction){
            case NORTH ->this.y-=bufferSpeed;
            case WEST ->this.x-=bufferSpeed;
            case EAST -> this.x+=bufferSpeed;
            case SOUTH -> this.y+=bufferSpeed;



        }}

    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle2D.Double hitbox = new Rectangle2D.Double() ;

        switch (direction){
            case NORTH->hitbox.setRect(super.getHitBox().getX()+w/2,super.getHitBox().getY()+h/2-bufferSpeed,super.getHitBox().getWidth()*3/5,super.getHitBox().getHeight()/2);
            case WEST -> hitbox.setRect(super.getHitBox().getX()+w/2-bufferSpeed,super.getHitBox().getY()+h/2,super.getHitBox().getWidth()*3/5,super.getHitBox().getHeight()/2);
            case EAST -> hitbox.setRect(super.getHitBox().getX()+w/5+bufferSpeed,super.getHitBox().getY()+h/2,super.getHitBox().getWidth()*3/5,super.getHitBox().getHeight()/2);
            case SOUTH -> hitbox.setRect(super.getHitBox().getX()+w/2,super.getHitBox().getY()+h/2+bufferSpeed,super.getHitBox().getWidth()*3/5,super.getHitBox().getHeight()/2);
        }
        for (Sprite e : environment){
            if ((e != this )&&(hitbox.intersects(e.getHitBox()))&&e instanceof SolidSprite){


                return false;}
        }

        return true;

    }

    public void truceTime(){
        if (this.invincibilityBuffer<25) {
            this.invincibilityBuffer = this.invincibilityBuffer + 1;
        }
    }
    public void getDamaged(int damage){
        if (this.invincibilityBuffer<25){

        }
        else{
            this.health = this.health-damage;
            this.invincibilityBuffer = 0;
        }
    }







    public void moveIfPossible(ArrayList<Sprite> environment){
        if (this.characterState!=CharacterState.DEAD){
                if (isMovingPossible(environment)){
                    if (isWalking){
                      this.characterState = CharacterState.WALKING;
                        move();
                    }

            }


        }

    }


}
