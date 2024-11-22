import java.awt.event.*;

public class GameEngine implements Engine,KeyListener {

    private final DynamicSprite player;

    public GameEngine(DynamicSprite player) {
        this.player = player;
    }

    public void update(){

    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_Z:
                player.setDirection(Direction.NORTH);
                player.isWalking = true;
                System.out.println("north");
                break;
            case KeyEvent.VK_S:
                player.isWalking = true;
                player.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_Q:
                player.isWalking = true;
                player.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_D:
                player.isWalking = true;
                player.setDirection(Direction.EAST);
                break;
        }
    }
    @Override
    public void keyTyped(KeyEvent e){

    }
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_Z:
                player.setDirection(Direction.NORTH);
                player.isWalking = false;
                System.out.println("north");
                break;
            case KeyEvent.VK_S:
                player.isWalking = false;
                player.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_Q:
                player.isWalking = false;
                player.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_D:
                player.isWalking = false;
                player.setDirection(Direction.EAST);
                break;}

    }
}
