package gobang;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 棋盘类
 */
public class Chessboard extends JPanel {
    //初始化棋盘大小15*15
    public static final int CHESSBOARD_SIZE = 15;
    //储存棋子的位置和持有者
    private List<Location> locationList = new ArrayList();
    //棋盘背景颜色
    private Color backgroundColor = new Color(255,245,186);
    //棋盘线条颜色
    private Color lineColor = new Color(66,66,66);
    //棋盘边缘长度
    private int margin = 20;

    //初始化棋盘
    public void init(){
        locationList.clear();
        repaint();
    }

    //画图方法
    public void paint(Graphics g) {
        super.paint(g);
        drawChessboard(g);
        drawChessman(g);
    }

    /**
     * 画棋盘
     */
    private void drawChessboard(Graphics g) {
        //先画背景
        g.setColor(backgroundColor);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        //画线
        g.setColor(lineColor);
        //每个格子的边长
        int cellSize = getCellSize();
        for (int i = 0; i<CHESSBOARD_SIZE; i++){
            //画横线
            g.drawLine(margin,margin+i*cellSize,580,margin+i*cellSize);
            //画竖线
            g.drawLine(margin+i*cellSize,margin,margin+i*cellSize,580);
        }
    }

    /**
     * 画棋子
     */
    private void drawChessman(Graphics g) {
        for(int i = 0; i<locationList.size(); i++){
            Location loc = locationList.get(i);
            //根据先后手设置棋子为黑色和白色
            if(loc.getOwner() == 1){
                g.setColor(Color.BLACK);
            }else{
                g.setColor(Color.WHITE);
            }
            //每个格子的边长
            int cellSize = getCellSize();
            //画棋子
            g.fillOval(margin + cellSize*loc.getX() - cellSize/2, margin + cellSize*loc.getY() - cellSize/2, cellSize, cellSize);
        }

    }

    //人类落子
    public void addChessman(int x, int y, int owner){
        locationList.add(new Location(x, y, owner));
        repaint();
    }

    //机器落子
    public void addChessman(Location loc){
        locationList.add(loc);
        repaint();
    }

    //计算棋盘每个小格子的大小
    public int getCellSize(){
        return 40;
    }

}

