package main.entities.physicalEntities;

import main.entities.Sprite;
import main.entities.entitiesEnums.CharacterState;
import main.entities.entitiesEnums.Direction;
import main.entities.entitiesEnums.Weapon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DynamicSprite extends Sprite implements Characters {

    private final double runningSpeed;
    private final int spriteSheetNumberOfColumn;
    public Boolean isWalking = false;
    public Boolean isRunning = false;
    public int health;
    public int idleBuffer = 0;
    int iterator = 0;
    private double speed;
    private double bufferSpeed = speed;
    private int timeBetweenFrame;
    private int invincibilityBuffer = 0;
    private Direction direction;
    private BufferedImage weaponSprite;
    private CharacterState characterState = CharacterState.IDLE;
    private int attitude = 1;
    private Weapon weapon = Weapon.SAI;


    public DynamicSprite(BufferedImage spriteSheet, int x, int y, int w, int h, double speed, int spriteSheetNumberOfColumn, int timeBetweenFrame, Direction direction, int health) {
        super(spriteSheet, x, y, w, h);

        this.speed = speed;
        this.spriteSheetNumberOfColumn = spriteSheetNumberOfColumn;
        this.timeBetweenFrame = timeBetweenFrame;
        this.direction = direction;
        this.health = health;
        this.runningSpeed = speed * 1.6;
        try {
            this.weaponSprite = ImageIO.read(new File(weapon.getWeaponSpriteInHandPath()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     *
     * @Setters ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     */
    public void setRunning(Boolean running) {
        isRunning = running;
    }

    public void runningSpeed() {
        if (this.isRunning) {
            bufferSpeed = runningSpeed;
        } else {
            bufferSpeed = speed;
        }
    }


    public void setDirection(Direction direction) {
        this.direction = direction;
        this.attitude = direction.getFrameLineNumber();
    }

    public void attack() {
        this.characterState = CharacterState.ATTACKING;


    }


    public void idle() {
        this.characterState = CharacterState.IDLE;

    }

    public void setWalking(Boolean walking) {
        isWalking = walking;
    }

    /**
     * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     *
     * @Getters ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     */
    public CharacterState getCharacterState() {
        return characterState;
    }

    public void checkHealth() {
        if (health <= 0) {
            this.characterState = CharacterState.DEAD;
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        try {
            this.weaponSprite = ImageIO.read(new File(weapon.getWeaponSpriteInHandPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     *
     * @checks ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     */
    public void attackIfPossible(ArrayList<MonsterSprite> environment) {
        if ((this.characterState != CharacterState.DEAD) && (this.characterState == CharacterState.ATTACKING)) {

            MonsterSprite attacked = attackHitSprite(environment);

            if (attacked != null) {
                attacked.getDamaged(this.weapon.getWeaponDamage());

            }

        }
    }

    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (this.characterState != CharacterState.DEAD) {
            if (isMovingPossible(environment)) {
                if (isWalking) {
                    this.characterState = CharacterState.WALKING;
                    move();
                }

            }


        }

    }

    /**
     * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     *
     * @HitBoxes ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     */


    private MonsterSprite attackHitSprite(ArrayList<MonsterSprite> environment) {
        Rectangle2D.Double hitbox = new Rectangle2D.Double();
        double attack_Range = 3.2 * this.weapon.getWeaponRange();
        switch (direction) {
            case Direction.NORTH ->
                    hitbox.setRect(super.getHitBox().getX(), super.getHitBox().getY() - attack_Range, super.getHitBox().getWidth(), super.getHitBox().getHeight() + attack_Range);
            case Direction.WEST ->
                    hitbox.setRect(super.getHitBox().getX() - attack_Range, super.getHitBox().getY(), super.getHitBox().getWidth() + attack_Range, super.getHitBox().getHeight());
            case Direction.EAST ->
                    hitbox.setRect(super.getHitBox().getX(), super.getHitBox().getY(), super.getHitBox().getWidth() + attack_Range, super.getHitBox().getHeight());
            case Direction.SOUTH ->
                    hitbox.setRect(super.getHitBox().getX(), super.getHitBox().getY(), super.getHitBox().getWidth(), super.getHitBox().getHeight() + attack_Range);
        }
        for (MonsterSprite e : environment) {
            if ((hitbox.intersects(e.getHitBox()))) {
                return e;
            }
        }
        return null;
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle2D.Double hitbox = new Rectangle2D.Double();

        switch (direction) {
            case Direction.NORTH ->
                    hitbox.setRect(super.getHitBox().getX() + (double) w / 2, super.getHitBox().getY() + (double) h / 2 - bufferSpeed, super.getHitBox().getWidth() * 3 / 5, super.getHitBox().getHeight() / 2);
            case Direction.WEST ->
                    hitbox.setRect(super.getHitBox().getX() + (double) w / 2 - bufferSpeed, super.getHitBox().getY() + (double) h / 2, super.getHitBox().getWidth() * 3 / 5, super.getHitBox().getHeight() / 2);
            case Direction.EAST ->
                    hitbox.setRect(super.getHitBox().getX() + (double) w / 5 + bufferSpeed, super.getHitBox().getY() + (double) h / 2, super.getHitBox().getWidth() * 3 / 5, super.getHitBox().getHeight() / 2);
            case Direction.SOUTH ->
                    hitbox.setRect(super.getHitBox().getX() + (double) w / 2, super.getHitBox().getY() + (double) h / 2 + bufferSpeed, super.getHitBox().getWidth() * 3 / 5, super.getHitBox().getHeight() / 2);
        }
        for (Sprite e : environment) {
            if ((e != this) && (hitbox.intersects(e.getHitBox())) && e instanceof SolidSprite) {
                return false;
            }
        }
        return true;
    }

    /**||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     * Game Interactions
     * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     */
    public void truceTime() {
        if (this.invincibilityBuffer < 25) {
            this.invincibilityBuffer = this.invincibilityBuffer + 1;
        }
    }

    public void getDamaged(int damage) {
        if (this.invincibilityBuffer < 25) {

        } else {
            this.health = this.health - damage;
            this.invincibilityBuffer = 0;
        }
    }

    private void move() {
        switch (direction) {
            case Direction.NORTH -> this.y -= (int) bufferSpeed;
            case Direction.WEST -> this.x -= (int) bufferSpeed;
            case Direction.EAST -> this.x += (int) bufferSpeed;
            case Direction.SOUTH -> this.y += (int) bufferSpeed;
        }
    }


    /**
     * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     *
     * @Graphics Paints following Direction and CharacterState
     * In Attack state a weapon image is painted but is not considered as independent Sprite
     * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(x, y);
        g2.scale(2, 2);

        switch (characterState) {
            case CharacterState.WALKING: {
                g2.drawRenderedImage(spriteSheet.getSubimage((attitude - 1) * w, h * iterator, w, h), null);
                if (isWalking) {
                    iterator = (iterator + 1) % spriteSheetNumberOfColumn;
                }
            }
            break;
            case CharacterState.IDLE: {
                g2.drawRenderedImage(spriteSheet.getSubimage((attitude - 1) * w, 0, h, h), null);
            }
            break;
            case CharacterState.ATTACKING: {
                g2.drawRenderedImage(spriteSheet.getSubimage((attitude - 1) * w, h * 4, w, h), null);
                switch (direction) {
                    case Direction.EAST: {
                        g2.translate(w, h);
                        g2.rotate(Math.PI * 3 / 2);
                        g2.drawRenderedImage(weaponSprite, null);
                    }
                    break;
                    case Direction.WEST: {
                        g2.translate(-6, 8);
                        g2.rotate(Math.PI / 2, (double) weaponSprite.getWidth() / 2, (double) weaponSprite.getHeight() / 2);
                        g2.drawRenderedImage(weaponSprite, null);
                    }
                    break;
                    case Direction.NORTH: {
                        g2.translate(2, -h / 2);
                        g2.rotate(Math.PI, (double) weaponSprite.getWidth() / 2, (double) weaponSprite.getHeight() / 2);
                        g2.drawRenderedImage(weaponSprite, null);
                    }
                    break;
                    case Direction.SOUTH: {
                        g2.translate(4, h);
                        g2.rotate(0);
                        g2.drawRenderedImage(weaponSprite, null);

                    }
                    break;
                }

                g2.drawRenderedImage(weaponSprite, null);
            }
            break;
            case CharacterState.DEAD: {
                g2.drawRenderedImage(spriteSheet.getSubimage(0, 16 * 6, w, h), null);
            }
            break;
            case CharacterState.JUMPING: {
            }
            break;
        }
    }

}
