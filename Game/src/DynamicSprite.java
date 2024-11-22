import java.awt.*;
import java.awt.image.BufferedImage;

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

        super.paintComponent(g);

        g.drawImage(spriteSheet.getSubimage(iterator*w+spriteAlignmentX,
                h*(attitude-1)+spriteAlignmentY,
                w,h), x, y, w, h, null, null);
    if(isWalking){
                iterator = (iterator+1)%spriteSheetNumberOfColumn;
    }}
}
