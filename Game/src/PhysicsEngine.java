import java.util.ArrayList;

public class PhysicsEngine implements Engine {
    private ArrayList<DynamicSprite> movingSpriteList ;
    private ArrayList<MonsterSprite> monsterSpriteList;
    private ArrayList<Sprite> environment;

    public PhysicsEngine(ArrayList<DynamicSprite> movingSpriteList, ArrayList<Sprite> environment,ArrayList<MonsterSprite>monsterSpriteList) {
        this.movingSpriteList = movingSpriteList;
        this.environment = environment;
        this.monsterSpriteList = monsterSpriteList;
    }


    public void addToMoving(DynamicSprite S){movingSpriteList.add(S);}

    public void addToMoving(MonsterSprite S){monsterSpriteList.add(S);}

    public void addToEnvironment(SolidSprite S){
        environment.add(S);
    }

    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    public void setMovingSpriteList(ArrayList<DynamicSprite> movingSpriteList) {
        this.movingSpriteList = movingSpriteList;
    }

    public void setMonsterSpriteList(ArrayList<MonsterSprite> monsterSpriteList) {
        this.monsterSpriteList = monsterSpriteList;
    }

    @Override
public void update(){
    for (DynamicSprite S :movingSpriteList) {
        S.moveIfPossible(environment);
    }
    for (MonsterSprite M :monsterSpriteList){
        M.moveIfPossible(environment);

    }
}
}
