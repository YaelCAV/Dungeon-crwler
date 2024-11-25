import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Level{
private ArrayList<Sprite> environment = new ArrayList<>();
public Level (String pathName){
    try{
        final BufferedImage imageTree = ImageIO.read(new File("./Game/Sprites/tree.png"));
        final BufferedImage imageGrass = ImageIO.read(new File("./Game/Sprites/grass.png"));
        final BufferedImage imageRock = ImageIO.read(new File("./Game/Sprites/rock.png"));
        final BufferedImage imageTrap = ImageIO.read(new File("./Game/Sprites/trap.png"));
        final int imageTreeWidth = imageTree.getWidth(null);
        final int imageTreeHeight = imageTree.getHeight(null);
        final int imageGrassWidth = imageGrass.getWidth(null);
        final int imageGrassHeight = imageGrass.getHeight(null);
        final int imageRockWidth = imageRock.getWidth(null);
        final int imageRockHeight = imageRock.getHeight(null);
        final int imageTrapWidth = imageRock.getWidth(null);
        final int imageTrapHeight = imageRock.getHeight(null);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
        String line=bufferedReader.readLine();
        int lineNumber = 0;
        int columnNumber = 0;
        while (line!= null){
            for (byte element : line.getBytes(StandardCharsets.UTF_8)){
                switch (element){
                    case 'T' : environment.add(new SolidSprite(imageTree,columnNumber*imageTreeWidth,lineNumber*imageTreeHeight,imageTreeWidth,imageTreeHeight));
                        break;
                    case ' ' : environment.add(new Sprite(imageGrass,columnNumber*imageGrassWidth,
                            lineNumber*imageGrassHeight,  imageGrassWidth, imageGrassHeight));
                        break;
                    case 'R' : environment.add(new SolidSprite(imageRock,columnNumber*imageRockWidth,
                            lineNumber*imageRockHeight,  imageRockWidth, imageRockHeight));
                        break;
                    case 'H' : environment.add(new SolidSprite(imageTrap,columnNumber*imageTrapWidth,
                            lineNumber*imageTrapHeight,  imageTrapWidth, imageTrapHeight));
                        break;
                }
                columnNumber++;
            }
            columnNumber =0;
            lineNumber++;
            line=bufferedReader.readLine();
        }
    }
    catch (Exception e){
        e.printStackTrace();
    }
}
    public ArrayList<Sprite> getSolidSpriteList(){
        ArrayList <Sprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            if (sprite instanceof SolidSprite) solidSpriteArrayList.add(sprite);
        }
        return solidSpriteArrayList;
    }
    public ArrayList<Displayable> getSpriteList(){
        ArrayList <Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            displayableArrayList.add((Displayable) sprite);
        }
        return displayableArrayList;
    }

}
