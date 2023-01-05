package gobang;

public class ChessInformation {
    //棋子横坐标
    private int x;
    //棋子纵坐标
    private int y;
    //棋手方，1人类，-1机器，0空
    private int Competitor;

    public ChessInformation(int x, int y, int Competitor){
        this.x = x;
        this.y = y;
        this.Competitor = Competitor;
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

    public int getCompetitor() {
        return Competitor;
    }

    public void setCompetitor(int competitor) {
        this.Competitor = competitor;
    }

}
