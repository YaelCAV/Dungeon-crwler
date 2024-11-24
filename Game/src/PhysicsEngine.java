import java.util.ArrayList;

public class PhysicsEngine implements Engine {
    private ArrayList<DynamicSprite> movingSpriteList ;
    private ArrayList<Sprite> environment;

    public PhysicsEngine(ArrayList<DynamicSprite> movingSpriteList, ArrayList<Sprite> environment) {
        this.movingSpriteList = movingSpriteList;
        this.environment = environment;
    }


    public void addToMoving(DynamicSprite S){
        movingSpriteList.add(S);
    }
    public void addToEnvironment(SolidSprite S){
        environment.add(S);
    }

    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    public void setMovingSpriteList(ArrayList<DynamicSprite> movingSpriteList) {
        this.movingSpriteList = movingSpriteList;
    }


@Override
public void update(){
    for (DynamicSprite S :movingSpriteList) {
        S.moveIfPossible(environment);
    }
}
}
