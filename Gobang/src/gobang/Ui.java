package gobang;

import javax.swing.*;
import java.awt.event.*;

/**
 * 游戏界面和输赢判断
 */
public class Ui {
    //游戏的窗口
    private JFrame frame;
    //五子棋盘
    private Chessboard chessboard = new Chessboard();
    //界面选项
    private Chess chess = new Chess();
    private JMenuBar menu;//菜单栏
    private JMenu option;//菜单栏中的“选项”菜单
    private Action replayOption;//“选项”下拉项中的“重玩一盘”选项

    //游戏运行入口
    public static void main(String[] args){
        new Ui().init();
    }

    //载入游戏
    public void init(){
        frame = new JFrame("人机五子棋");//创建游戏界面窗口
        menu = new JMenuBar();//创建菜单栏
        option = new JMenu("选项");//创建“选项”菜单
        //把“选项”菜单加入到菜单栏
        menu.add(option);
        //把“重玩一盘”加入“选项”下拉项中
        replayOptionInit();
        option.add(replayOption);

        //把menu设置为frame的菜单栏
        frame.setJMenuBar(menu);
        frame.add(chessboard);//把五子棋盘加入到frame

        //初始化棋盘
        chessboard.init();
        chess.init();

        //对鼠标监听
        chessboard.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                //鼠标点击引发下棋事件
                play(e);
            }
        });
        //设置frame窗口左上角图标
        frame.setIconImage(frame.getToolkit().getImage("image/gobang.png"));
        frame.setSize(620, 670);//窗口大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    //处理“重玩一盘”事件
    public void replayOptionInit(){
        replayOption = new AbstractAction("重玩一盘"){
            public void actionPerformed(ActionEvent e){
                chessboard.init();//界面初始化重来
                chess.init();//逻辑初始化重来
            }
        };
    }

    //处理鼠标落子事件
    public void play(MouseEvent e){
        int cellSize = chessboard.getCellSize();//每个格子的边长
        int x = (e.getX() - 5) / cellSize;//像素值转换成棋盘坐标
        int y = (e.getY() - 5) / cellSize;//像素值转换成棋盘坐标
        //判断落子是否合法
        boolean isLegal = chess.isLegal(x, y);
        //如果落子合法
        if(isLegal){
            chessboard.addChessman(x, y, 1);//界面方面加一个棋子
            chess.addChessman(x, y, 1);//逻辑业务方面加一个棋子

            //判断人类是否胜利
            if(chess.isWin(x, y, 1)){
                JOptionPane.showMessageDialog(frame, "人类获胜", "Congratulations，您赢了！", JOptionPane.PLAIN_MESSAGE);
                chessboard.init();
                chess.init();
                return;
            }

            //机器落子
            Location loc = chess.searchLocation();
            chessboard.addChessman(loc);
            chess.addChessman(loc.getX(), loc.getY(), loc.getOwner());

            for(int i = 0; i<15; i++) {
                for(int j = 0; j<15; j++) {
                    System.out.print(chess.score[i][j] + " ");
                }
                System.out.print("\n");
            }

            //判断机器是否胜利
            if(chess.isWin(loc.getX(), loc.getY(), -1)){
                JOptionPane.showMessageDialog(frame, "机器获胜", "很遗憾，您输了！", JOptionPane.PLAIN_MESSAGE);
                chessboard.init();
                chess.init();
                return;
            }
        }
    }


}
