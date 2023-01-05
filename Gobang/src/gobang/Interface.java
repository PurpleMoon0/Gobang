package gobang;

import javax.swing.*;
import java.awt.event.*;

/**
 * 游戏界面和输赢判断
 */
public class Interface {
    //游戏的窗口
    private JFrame frame;
    //五子棋盘
    private Draw draw = new Draw();
    //界面选项
    private logic logic = new logic();
    private JMenuBar menu;//菜单栏
    private JMenu option;//选项菜单
    private Action replayOption;//重玩一盘选项

    //运行游戏
    public static void main(String[] args){
        new Interface().init();
    }

    //载入游戏
    public void init(){
        frame = new JFrame("人机五子棋");//创建窗口
        // 设置窗口
        frame.setIconImage(frame.getToolkit().getImage("image/gobang.png"));
        frame.setSize(620, 670);//窗口大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        menu = new JMenuBar();//创建菜单栏
        option = new JMenu("选项");//创建“选项”菜单
        //把“选项”菜单加入到菜单栏
        menu.add(option);
        //把“重玩一盘”加入“选项”下拉项中
        replayOptionInit();
        option.add(replayOption);

        //把menu设置为frame的菜单栏
        frame.setJMenuBar(menu);
        frame.add(draw);//加入棋盘

        //初始化棋盘
        draw.initialization();
        logic.initialization();

        //对鼠标监听
        draw.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent play){
                play(play);
            }
        });

    }

    //处理“重玩一盘”事件
    public void replayOptionInit(){
        replayOption = new AbstractAction("重玩一盘"){
            public void actionPerformed(ActionEvent e){
                draw.initialization();//界面初始化重来
                logic.initialization();//逻辑初始化重来
            }
        };
    }

    //处理鼠标落子事件
    public void play(MouseEvent play){
        int cellSize = draw.getCellSize();//每个格子的边长
        int x = (play.getX() - 5) / cellSize;//像素值转换成棋盘坐标
        int y = (play.getY() - 5) / cellSize;//像素值转换成棋盘坐标
        //判断落子是否合法
        boolean isLegal = logic.Compliance(x, y);
        //如果落子合法
        if(isLegal){
            draw.addChessman(x, y, 1);//界面方面加一个棋子
            logic.addChess(x, y, 1);//逻辑业务方面加一个棋子

            //判断人类是否胜利
            if(logic.isWin(x, y, 1)){
                JOptionPane.showMessageDialog(frame, "人类获胜", "Congratulations，您赢了！", JOptionPane.PLAIN_MESSAGE);
                draw.initialization();
                logic.initialization();
                return;
            }

            //机器落子
            ChessInformation loc = logic.searchLocation();
            draw.addChessman(loc);
            logic.addChess(loc.getX(), loc.getY(), loc.getCompetitor());

            logic.showScore();//测试：在控制台输出机器的算分

            //判断机器是否胜利
            if(logic.isWin(loc.getX(), loc.getY(), -1)){
                JOptionPane.showMessageDialog(frame, "机器获胜", "很遗憾，您输了！", JOptionPane.PLAIN_MESSAGE);
                draw.initialization();
                logic.initialization();
                return;
            }
        }
    }


}
