import java.io.IOException;

public class MonsterEngine implements Engine {

    public int Health;

    private final MonsterSprite monster;

    public MonsterEngine(MonsterSprite monster) {
        this.monster = monster;
    }



    private void patternProgress(){

        String patternString = new String();
        try{
            patternString = monster.pattern.readLine();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        switch(patternString.charAt(monster.patternTracker)){
            case 'N' -> monster.setDirection(Direction.NORTH);
            case 'S' -> monster.setDirection(Direction.SOUTH);
            case 'E' -> monster.setDirection(Direction.EAST);
            case 'W' -> monster.setDirection(Direction.WEST);
            case 'm' -> monster.isWalking = true;
            case 'h' -> monster.isWalking = false;
        }
        monster.patternTracker= (monster.patternTracker+1)%patternString.length();



    }
    public void update(){
    this.patternProgress();
    System.out.println("pattern progress");
    }
}
