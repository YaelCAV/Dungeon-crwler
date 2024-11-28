package main.generators;

import main.entities.Displayable;
import main.entities.Sprite;
import main.entities.physicalEntities.MonsterSprite;
import main.entities.physicalEntities.SolidSprite;
import main.entities.entitiesEnums.Decorations;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DecorationsGenerator implements LevelGeneration{
    private final ArrayList<Sprite> environment = new ArrayList<>();
    private final ArrayList<MonsterSprite> monsterList = new ArrayList<>();

    public DecorationsGenerator(String pathName,String imgName) {
        try {
            final BufferedImage spriteSheet =ImageIO.read((new File(imgName)));
            final BufferedImage imageGrass1 = spriteSheet.getSubimage(Decorations.GRASS1.getX(), Decorations.GRASS1.getY(), 16, 16);
            final BufferedImage imageGrass2 = spriteSheet.getSubimage(Decorations.GRASS2.getX(), Decorations.GRASS2.getY(), 16, 16);
            final BufferedImage imageGrass3 = spriteSheet.getSubimage(Decorations.GRASS3.getX(), Decorations.GRASS3.getY(), 16, 16);
            final BufferedImage imageGrass4 = spriteSheet.getSubimage(Decorations.GRASS4.getX(), Decorations.GRASS4.getY(), 16, 16);
            final BufferedImage imageGrass5 = spriteSheet.getSubimage(Decorations.GRASS5.getX(), Decorations.GRASS5.getY(), 16, 16);
            final BufferedImage imageGrass6 = spriteSheet.getSubimage(Decorations.GRASS6.getX(), Decorations.GRASS6.getY(), 16, 16);
            final BufferedImage imageGrass7 = spriteSheet.getSubimage(Decorations.GRASS7.getX(), Decorations.GRASS7.getY(), 16, 16);
            final BufferedImage imageLog1 = spriteSheet.getSubimage(Decorations.LOG1.getX(), Decorations.LOG1.getY(), 16, 16);
            final BufferedImage imageLog2 = spriteSheet.getSubimage(Decorations.LOG2.getX(), Decorations.LOG2.getY(), 16, 16);
            final BufferedImage imageTallGrass1 = spriteSheet.getSubimage(Decorations.TALLGRASS1.getX(), Decorations.TALLGRASS1.getY(), 16, 16);
            final BufferedImage imageTallGrass2 = spriteSheet.getSubimage(Decorations.TALLGRASS2.getX(), Decorations.TALLGRASS2.getY(), 16, 16);


            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line = bufferedReader.readLine();
            int lineNumber = 0;
            int columnNumber = 0;
            while (line != null) {
                for (byte element : line.getBytes(StandardCharsets.UTF_8)) {
                    switch (element) {
                        case '1':
                            environment.add(new Sprite(imageGrass1, columnNumber * 16, lineNumber * 16, 16, 16));
                            break;
                        case '2':
                            environment.add(new Sprite(imageGrass2, columnNumber * 16, lineNumber * 16, 16, 16));
                            break;
                        case '3':
                            environment.add(new Sprite(imageGrass3, columnNumber * 16, lineNumber * 16, 16, 16));
                            break;
                        case '4':
                            environment.add(new Sprite(imageGrass4, columnNumber * 16, lineNumber * 16, 16, 16));
                            break;
                        case '5':
                            environment.add(new Sprite(imageGrass5, columnNumber * 16, lineNumber * 16, 16, 16));
                            break;
                        case '6':
                            environment.add(new Sprite(imageGrass6, columnNumber * 16, lineNumber * 16, 16, 16));
                            break;
                        case '7':
                            environment.add(new Sprite(imageGrass7, columnNumber * 16, lineNumber * 16, 16, 16));
                            break;
                            case 't':
                            environment.add(new Sprite(imageTallGrass1, columnNumber * 16, lineNumber * 16, 16, 16));
                            break;
                            case 'T':
                            environment.add(new Sprite(imageTallGrass2, columnNumber * 16, lineNumber * 16, 16, 16));
                            break;
                        case '#':

                            break;
                        case 'l':
                            environment.add(new Sprite(imageLog1, columnNumber * 16, lineNumber * 16, 16, 16));
                            break;
                        case 'L':
                            environment.add(new Sprite(imageLog2, columnNumber * 16, lineNumber * 16, 16, 16));
                            break;



                    }
                    columnNumber++;
                }
                columnNumber = 0;
                lineNumber++;
                line = bufferedReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Sprite> getSolidSpriteList() {
        ArrayList<Sprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite) solidSpriteArrayList.add(sprite);
        }
        return solidSpriteArrayList;
    }

    public ArrayList<Displayable> getSpriteList() {
        ArrayList<Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment) {
            displayableArrayList.add((Displayable) sprite);
        }
        return displayableArrayList;
    }

}

