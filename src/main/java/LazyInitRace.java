/**
 * Created by yjlee on 2018-01-14.
 */
public class LazyInitRace {
    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance(){
        if(instance == null){
            instance = new ExpensiveObject();
        }
        return  instance;
    }
}

class ExpensiveObject{

}
