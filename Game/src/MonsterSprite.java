import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MonsterSprite extends Sprite {


    private double speed;
    private int spriteSheetNumberOfColumn;
    private int timeBetweenFrame;
    private Direction direction;
    private int attitude = 1;
    int iterator = 0;

    public boolean isWalking;
    public int patternTracker = 0;
    public String pattern;
    private int stepsLeft;
    private int invincibilityBuffer = 0;
    private int health;
    private CharacterState characterState = CharacterState.IDLE;

    public MonsterSprite(BufferedImage spriteSheet, int x, int y, int w, int h, double speed, int spriteSheetNumberOfColumn, int timeBetweenFrame, Direction direction, String patternPath, int health) {
        super(spriteSheet, x, y, w, h);
        this.spriteSheetNumberOfColumn = spriteSheetNumberOfColumn;

        this.speed = speed;
        this.timeBetweenFrame = timeBetweenFrame;
        this.direction = direction;
        this.health = health;
        try {
            BufferedReader br = new BufferedReader(new FileReader(patternPath));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();


            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            this.pattern = sb.toString();
            System.out.println(this.pattern);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        this.attitude = direction.getFrameLineNumber();
    }

    public void setWalking(boolean walking) {
        isWalking = walking;
    }


    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle2D.Double hitbox = new Rectangle2D.Double();
        switch (direction) {
            case NORTH ->
                    hitbox.setRect(super.getHitBox().getX() + w / 2, super.getHitBox().getY() + h / 2 - speed, super.getHitBox().getWidth() * 3 / 5, super.getHitBox().getHeight() / 2);
            case WEST ->
                    hitbox.setRect(super.getHitBox().getX() + w / 2 - speed, super.getHitBox().getY() + h / 2, super.getHitBox().getWidth() * 3 / 5, super.getHitBox().getHeight() / 2);
            case EAST ->
                    hitbox.setRect(super.getHitBox().getX() + w / 5 + speed, super.getHitBox().getY() + h / 2, super.getHitBox().getWidth() * 3 / 5, super.getHitBox().getHeight() / 2);
            case SOUTH ->
                    hitbox.setRect(super.getHitBox().getX() + w / 2, super.getHitBox().getY() + h / 2 + speed, super.getHitBox().getWidth() * 3 / 5, super.getHitBox().getHeight() / 2);
        }
        for (Sprite e : environment) {
            if ((e != this) && (hitbox.intersects(e.getHitBox())) && e instanceof SolidSprite) {

                return false;
            }


        }

        return true;
    }

    public void addStep() {
        this.stepsLeft = this.stepsLeft + 1;
    }


    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            if (this.stepsLeft > 0) {
                if (this.characterState != CharacterState.DEAD) {
                    this.isWalking = true;

                    this.stepsLeft = this.stepsLeft - 1;
                    move();
                }

            } else {
                this.isWalking = false;
            }
        }
    }


    private void move() {
        switch (direction) {
            case NORTH -> this.y -= speed;
            case WEST -> this.x -= speed;
            case EAST -> this.x += speed;
            case SOUTH -> this.y += speed;


        }
    }

    public void encounterPlayer(ArrayList<DynamicSprite> dynamicSprites) {
        if(this.characterState!=CharacterState.DEAD){
        for (DynamicSprite p : dynamicSprites) {
            if (this.getHitBox().intersects(p.getHitBox())) {
                p.getDamaged(1);
            }
        }
        }
    }

    public void getDamaged(int damage) {
        if (this.invincibilityBuffer < 10) {

        } else {
            this.health = this.health - damage;
            this.invincibilityBuffer = 0;
            System.out.println(this.health);
        }
    }

    public void truceTime() {
        if (this.invincibilityBuffer < 10) {
            this.invincibilityBuffer = this.invincibilityBuffer + 1;
        }
    }

    public void checkHealth() {
        if (health <= 0) {
            this.characterState = CharacterState.DEAD;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(x, y);
        g2.scale(2, 2);

        switch (characterState) {
            case WALKING: {
                g2.drawRenderedImage(spriteSheet.getSubimage((attitude - 1) * w, h * iterator, w, h), null);
                if (isWalking) {
                    iterator = (iterator + 1) % spriteSheetNumberOfColumn;
                }
            }
            break;
            case IDLE: {
                g2.drawRenderedImage(spriteSheet.getSubimage((attitude - 1) * w, 0, h, h), null);
            }
            break;
            case ATTACKING: {
                g2.drawRenderedImage(spriteSheet.getSubimage((attitude - 1) * w, h * 4, w, h), null);

            }
            break;
            case DEAD: {
                g2.drawRenderedImage(spriteSheet.getSubimage(0, 16 * 6, w, h), null);
            }
            break;
            case JUMPING: {
            }
            break;
        }
    }

}
