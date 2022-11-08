package gobang;

public class Location {
    //棋盘位置横坐标
    private int x;
    //棋盘位纵坐标
    private int y;
    //占据该位置的棋手方，1是人类，-1是机器，0是空
    private int owner;

    public Location(int x, int y, int owner){
        this.x = x;
        this.y = y;
        this.owner = owner;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

}
