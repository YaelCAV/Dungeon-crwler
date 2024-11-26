import java.io.IOException;

public class MonsterEngine implements Engine {

    public int Health;

    private final MonsterSprite monster;

    private int Behaviour;
    public MonsterEngine(MonsterSprite monster) {
        this.monster = monster;
    }



    private void patternProgress(){


            switch(monster.pattern.charAt(monster.patternTracker)){
            case 'N' -> monster.setDirection(Direction.NORTH);
            case 'S' -> monster.setDirection(Direction.SOUTH);
            case 'E' -> monster.setDirection(Direction.EAST);
            case 'W' -> monster.setDirection(Direction.WEST);
            case 'm' -> monster.addStep();
            case 'h' -> monster.setWalking(false);
        }
        monster.patternTracker= (monster.patternTracker+1)%monster.pattern.length();



    }
    public void update(){
    this.patternProgress();


    }

}
