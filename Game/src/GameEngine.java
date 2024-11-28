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
            if(player.getCharacterState()!=CharacterState.DEAD)
            player.idle();

        }
        else {
            player.idleBuffer += 5;
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_Z:
                player.setDirection(Direction.NORTH);
                player.setWalking(true);
                player.idleBuffer = 0;
                break;
            case KeyEvent.VK_S:
                player.setWalking(true);
                player.setDirection(Direction.SOUTH);
                player.idleBuffer = 0;
                break;
            case KeyEvent.VK_Q:
                player.setWalking(true);
                player.setDirection(Direction.WEST);
                player.idleBuffer = 0;
                break;
            case KeyEvent.VK_D:
                player.setWalking(true);
                player.setDirection(Direction.EAST);
                player.idleBuffer = 0;
                break;
            case KeyEvent.VK_CONTROL:
                player.setRunning(true);
                player.idleBuffer = 0;
                break;
            case KeyEvent.VK_E:
                player.attack();
                player.idleBuffer = -30;
                break;
            case KeyEvent.VK_1:
                player.setWeapon(Weapon.SAI);
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
