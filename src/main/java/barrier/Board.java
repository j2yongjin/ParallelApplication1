package barrier;

/**
 * Created by yjlee on 2018-01-21.
 */
public class Board {

    String name;
    int maxY;
    int maxX;

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public Board(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void commitNewValues() {

    }

    public boolean hasConverged() {
        return false;
    }

    public void setNewValue(int x, int y, int value) {

    }
}
