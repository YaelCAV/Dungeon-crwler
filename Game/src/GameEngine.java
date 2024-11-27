import java.awt.event.*;

public class GameEngine implements Engine,KeyListener {

    private final DynamicSprite player;

    public GameEngine(DynamicSprite player) {
        this.player = player;
    }

    public void update(){
        player.runningSpeed();
        player.truceTime();
        player.checkHealth();
        if (player.idleBuffer == 10){
            player.idle();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_Z:
                player.setDirection(Direction.NORTH);
                player.setWalking(true);
                player.idleBuffer = player.idleBuffer-1;
                break;
            case KeyEvent.VK_S:
                player.setWalking(true);
                player.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_Q:
                player.setWalking(true);
                player.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_D:
                player.setWalking(true);
                player.setDirection(Direction.EAST);
                break;
            case KeyEvent.VK_CONTROL:
                player.setRunning(true);
                break;
            case KeyEvent.VK_E:
                player.attack();
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
                player.setWalking(false);
                break;
            case KeyEvent.VK_S:
                player.setWalking(false);
                player.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_Q:
                player.setWalking(false);
                player.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_D:
                player.setWalking(false);
                player.setDirection(Direction.EAST);
                break;
            case KeyEvent.VK_CONTROL:
                player.isRunning = false;
        }

    }
}
