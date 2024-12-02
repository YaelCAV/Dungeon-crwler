package main.entities.physicalEntities;

import main.engines.MonsterEngine;
import main.entities.Sprite;
import main.entities.entitiesEnums.*;
import main.entities.entitiesEnums.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class MonsterSprite extends Sprite implements Characters {


    private final double speed;
    private final int spriteSheetNumberOfColumn;
    private final int timeBetweenFrame;
    //private final MonsterEngine monsterEngine;
    public boolean isWalking;
    public int patternTracker = 0;
    public String pattern;
    int iterator = 0;
    private Direction direction;
    private int attitude = 1;
    private int stepsLeft;
    private int invincibilityBuffer = 0;
    private int health;
    private CharacterState characterState = CharacterState.IDLE;
    private Weapon weapon;
    private BufferedImage weaponSprite;
    public boolean isTracking;
    private float trackX;
    private float trackY;

    public MonsterSprite(BufferedImage spriteSheet, int x, int y, int w, int h, double speed, int spriteSheetNumberOfColumn, int timeBetweenFrame, Direction direction, String patternPath, int health, Weapon weapon) {
        super(spriteSheet, x, y, w, h);
        this.spriteSheetNumberOfColumn = spriteSheetNumberOfColumn;
        this.speed = speed;
        this.timeBetweenFrame = timeBetweenFrame;
        this.direction = direction;
        this.health = health;
        this.weapon = weapon;
        //this.monsterEngine = new MonsterEngine(this,encounterPlayer(););
        try {
            this.weaponSprite = ImageIO.read(new File(this.weapon.getWeaponSpriteInHandPath()));
            BufferedReader br = new BufferedReader(new FileReader(patternPath));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();


            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            this.pattern = sb.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     *
     * @Setters ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     */

    public void setDirection(Direction direction) {
        this.direction = direction;
        this.attitude = direction.getFrameLineNumber();
    }

    public void setWalking(boolean walking) {
        isWalking = walking;
    }

    /**
     * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     *
     * @Getters ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     */
    public void checkHealth() {
        if (health <= 0) {
            this.characterState = CharacterState.DEAD;
        }
    }

    public void attack() {
        this.characterState = CharacterState.ATTACKING;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public CharacterState getCharacterState() {
        return characterState;
    }

    /**
     * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     *
     * @checks ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     */

    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            if (this.stepsLeft > 0) {
                if (this.characterState != CharacterState.DEAD) {
                    this.isWalking = true;
                    this.characterState = CharacterState.WALKING;

                    this.stepsLeft = this.stepsLeft - 1;
                    move();
                }

            } else {
                this.isWalking = false;
            }
        }
    }

    /**s
     * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     *
     * @HitBoxes ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     */

    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle2D.Double hitbox = new Rectangle2D.Double();
        switch (direction) {
            case Direction.NORTH ->
                    hitbox.setRect( super.getHitBox().getX() + (double) w / 2,
                                    super.getHitBox().getY() + (double) h / 2 - speed,
                                    super.getHitBox().getWidth() , super.getHitBox().getHeight() );
            case Direction.WEST ->
                    hitbox.setRect(super.getHitBox().getX() + (double) w / 2 - speed, super.getHitBox().getY() + (double) h / 2, super.getHitBox().getWidth() , super.getHitBox().getHeight() );
            case Direction.EAST ->
                    hitbox.setRect(super.getHitBox().getX() + (double) w / 2 + speed, super.getHitBox().getY() + (double) h / 2, super.getHitBox().getWidth() , super.getHitBox().getHeight() );
            case Direction.SOUTH ->
                    hitbox.setRect(super.getHitBox().getX() + (double) w / 2, super.getHitBox().getY() + (double) h / 2 + speed, super.getHitBox().getWidth() , super.getHitBox().getHeight() );
        }
        for (Sprite e : environment) {
            if ((e != this) && (hitbox.intersects(e.getHitBox())) && e instanceof SolidSprite) {

                return false;
            }


        }

        return true;
    }

    public void encounterPlayer(ArrayList<DynamicSprite> dynamicSprites) {
        if (this.characterState != CharacterState.DEAD) {
            for (DynamicSprite p : dynamicSprites) {
                Rectangle2D hitBox = this.attackHitBox();
                if (hitBox.intersects(p.getHitBox())) {
                    this.characterState = CharacterState.ATTACKING;
                    p.getDamaged(1);

                }
            }
        }
    }

    private Rectangle2D attackHitBox() {
        Rectangle2D.Double hitbox = new Rectangle2D.Double();
        int attack_Range = 5 * this.weapon.getWeaponRange();
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

        return hitbox;
    }

    /**
     * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     * Game Interactions
     * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
     */

    public void getDamaged(int damage) {
        if (this.invincibilityBuffer >= 10) {
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

    public void addStep() {
        this.stepsLeft = this.stepsLeft + 1;
    }

    private void move() {
        if (isTracking) {



            if (trackX > 0 && trackY > 0) {
                if (Math.abs(x) > Math.abs(y)) {
                    this.direction = Direction.EAST;

                } else {
                    this.direction = Direction.SOUTH;

                }
                this.x += (int) (this.trackX * speed);
                this.y += (int) (this.trackY * speed);


            } else if (trackX < 0 && trackY > 0) {
                if (Math.abs(x) > Math.abs(y)) {
                    this.direction = Direction.WEST;

                } else {
                    this.direction = Direction.SOUTH;

                }
                this.x += (int) (this.trackX * speed);
                this.y += (int) (this.trackY * speed);

            } else if (trackX < 0 && trackY < 0) {
                if (Math.abs(x) > Math.abs(y)) {
                    this.direction = Direction.WEST;

                } else {
                    this.direction = Direction.NORTH;

                }
                this.x += (int) (this.trackX * speed);
                this.y += (int) (this.trackY * speed);

            } else if (trackX > 0 && trackY < 0) {
                if (Math.abs(x) > Math.abs(y)) {
                    this.direction = Direction.EAST;

                } else {
                    this.direction = Direction.NORTH;

                }
                this.x += (int) (this.trackX * speed);
                this.y += (int) (this.trackY * speed);
            }
        } else {
            switch (direction) {
                case Direction.NORTH -> this.y -= (int) speed;
                case Direction.WEST -> this.x -= (int) speed;
                case Direction.EAST -> this.x += (int) speed;
                case Direction.SOUTH -> this.y += (int) speed;


            }
        }
    }


    public void track(DynamicSprite player) {
        int x = player.getXcoords();
        int y = player.getYcoords();

        float invNorm;
        float vectX = x - this.x;
        float vectY = y - this.y;

        invNorm = (1 / ((float) (Math.sqrt(vectX * vectX + vectY * vectY))));
        this.trackX = vectX * invNorm;
        this.trackY = vectY * invNorm;


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
                        g2.translate(-7, 8);
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
