package main.engines;

import main.entities.Displayable;

import javax.swing.*;
import java.util.ArrayList;

public class RenderEngine extends JFrame {

    private static ArrayList<Displayable> renderList;

   public RenderEngine(){
       super("The Game");
       //this.setUndecorated(true);
       this.setSize(1280,640);
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       renderList = new ArrayList<>();
       this.setVisible(true);
   }

    public static void setRenderList(ArrayList<Displayable> renderList) {
        RenderEngine.renderList = renderList;
    }


    public void addToRenderList(Displayable displayable){
        renderList.add(displayable);
    }


    public void update() {
        this.paint();
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void paint(){

        for (Displayable d : renderList){
            d.draw(this);
            this.setVisible(true);
    }

    }


}
