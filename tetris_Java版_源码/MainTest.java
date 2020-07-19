import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

//这个类用来编写主界面,启动整个游戏
public class MainTest extends JPanel
{
    private static final long serialVersionUID = 6531259998506083997L;
    private static final int WIDTH=24;//设置游戏区的宽
    private static final int HEIGHT=30;//设置游戏区的高

    private static final Random rand=new Random();
    private int flag=rand.nextInt(21)+1;//产生的序号为1到21
    private int nextflag=rand.nextInt(21)+1;//产生的序号为1到21

    private static final int firstX=WIDTH/2; //定义初始方块的位置
    private static final int firstY=2;//防止数组越界(因为有的中心坐标上面还有数组)
    private static final int WIDTH_SMALL=10;//设置小屏幕的宽
    private static final int HEIGHT_SMALL=10;//设置小屏幕的高
    private static final int  WIDTH_TIPS=10;//设置提升区的宽
    private static final int HEIGHT_TIPS=20;//设置小屏幕的高

    private static final int WIDTH_CELL=30;//设置每一个小格子的宽
    private static final int HEIGHT_CELL=30;//设置每一个小格子的高

    private static int isSpace=1;

    private static boolean shouldWait=false;
    private static int SleepCount=0;//用于退出的





    //	private static final int RIGHT=1;
//	private static final int LEFT=-1;
    private boolean moveable=true;

    private static int [][] game=new int [WIDTH][HEIGHT];//游戏区的数组
    private static int [][] small=new int [WIDTH_SMALL][HEIGHT_SMALL];//小屏幕的数组
    private static int [][] tips=new int [WIDTH_TIPS][HEIGHT_TIPS];//小屏幕的数组

    private static MainTest maintest;//一开始就创建一个静态对象,就可以使用非静态方法了,用对象.调用
    private Tetris tetris;
    private Tetris tetris_small;
    private boolean isLao=false;
    private static int speed=20;
    private static int goal=0;
    private static JFrame frame;
    private boolean isOver=false;
    public  void initMap()
    {
        for(int i=0;i<WIDTH;i++)//i代表x,j代表y坐标
        {
            for(int j=0;j<HEIGHT;j++)
            {
                if(i==0||i==1||i==WIDTH-2||i==WIDTH-1)
                    game[i][j]=1;
                else if(j==HEIGHT-1||j==HEIGHT-2)
                    game[i][j]=4;
                else if(j==0||j==1)//上面为5
                    game[i][j]=5;
                else
                    game[i][j]=0;

            }
        }
        for(int i=0;i<WIDTH_SMALL;i++)//i代表x,j代表y坐标
        {
            for(int j=0;j<HEIGHT_SMALL;j++)
            {
                small[i][j]=0;
            }
        }
        for(int i=0;i<WIDTH_TIPS;i++)//i代表x,j代表y坐标
        {
            for(int j=0;j<HEIGHT_TIPS;j++)
            {
                tips[i][j]=0;
            }
        }


    }
    public void paint(Graphics g)
    {
        //0空白,1墙,2方块,3老方块,4地面
        //游戏区
        for(int i=0;i<WIDTH;i++)//i代表x,j代表y坐标
        {
            for(int j=0;j<HEIGHT;j++)
            {
                if(game[i][j]==0)
                    g.setColor(Color.WHITE);
                if(game[i][j]==2)
                    g.setColor(Color.RED);
                if(game[i][j]==1||game[i][j]==4||game[i][j]==5)
                    g.setColor(Color.GRAY);
                if(game[i][j]==3)
                    g.setColor(Color.RED);
                g.fill3DRect(i*WIDTH_CELL, j*HEIGHT_CELL, WIDTH_CELL, HEIGHT_CELL, true);
            }
        }


        //小屏幕区
        for(int i=0;i<WIDTH_SMALL;i++)//i代表x,j代表y坐标
        {
            for(int j=0;j<HEIGHT_SMALL;j++)
            {
                if(small[i][j]==0)
                    g.setColor(Color.BLACK);
                if(small[i][j]==2)
                    g.setColor(Color.WHITE);
                g.fill3DRect((WIDTH+i)*WIDTH_CELL, j*HEIGHT_CELL, WIDTH_CELL, HEIGHT_CELL, true);//把小屏幕放在游戏区的右上边
            }
        }

        //提升区:
        g.setColor(Color.WHITE);//把tips放在游戏区的右下边
        g.fill3DRect((WIDTH)*WIDTH_CELL, (HEIGHT_SMALL)*HEIGHT_CELL, WIDTH_TIPS*WIDTH_CELL, HEIGHT_TIPS*HEIGHT_CELL, true);
        g.setColor(Color.BLACK);
        g.setFont(new Font("宋体", Font.BOLD,40 ));
        g.drawString("变体:W",(WIDTH+1)*WIDTH_CELL, (HEIGHT_SMALL+3)*HEIGHT_CELL);
        g.drawString("移动:AD",(WIDTH+1)*WIDTH_CELL, (HEIGHT_SMALL+5)*HEIGHT_CELL);
        g.drawString("加速下滑:S",(WIDTH+1)*WIDTH_CELL, (HEIGHT_SMALL+7)*HEIGHT_CELL);
        g.drawString("改变速度:Q,E",(WIDTH+1)*WIDTH_CELL, (HEIGHT_SMALL+9)*HEIGHT_CELL);
        g.drawString("暂停:空格",(WIDTH+1)*WIDTH_CELL, (HEIGHT_SMALL+11)*HEIGHT_CELL);
        g.drawString("重开:R",(WIDTH+1)*WIDTH_CELL, (HEIGHT_SMALL+13)*HEIGHT_CELL);
        g.drawString("退出:ESC",(WIDTH+1)*WIDTH_CELL, (HEIGHT_SMALL+15)*HEIGHT_CELL);
        g.drawString("分数:"+goal,(WIDTH+1)*WIDTH_CELL, (HEIGHT_SMALL+17)*HEIGHT_CELL);
        g.drawString("速度:"+speed,(WIDTH+1)*WIDTH_CELL, (HEIGHT_SMALL+19)*HEIGHT_CELL);

        if(isOver)
        {
            g.setColor(Color.BLACK);
            g.setFont(new Font("微软雅黑", Font.BOLD,100 ));
            g.drawString("游戏结束",(WIDTH/2-6)*WIDTH_CELL, (HEIGHT/2)*HEIGHT_CELL);
            g.setColor(Color.BLUE);
            g.setFont(new Font("微软雅黑", Font.BOLD,40 ));
            g.drawString("3秒后自动退出",(WIDTH/2-4)*WIDTH_CELL, (HEIGHT/2+3)*HEIGHT_CELL);
            g.drawString("您的分数为:"+goal,(WIDTH/2-4)*WIDTH_CELL, (HEIGHT/2+5)*HEIGHT_CELL);
            g.drawString("重新请按R,直接退出请按ESC",(WIDTH/2-8)*WIDTH_CELL, (HEIGHT/2+7)*HEIGHT_CELL);
        }

    }
    public  void start() throws InterruptedException
    {
        //start也是静态的,要用非静态的方法,只能一开始就创建好了本类的对象
        initMap();
        getNewTetris();
        repaint();
        frame.addKeyListener(new KeyAdapter()
        {

            public void keyPressed(KeyEvent e)
            {
                super.keyPressed(e);
                switch(e.getKeyCode())
                {
//				case KeyEvent.VK_UP:
//
//					newsnake.changeDirection(UP);
//					newsnake.isRun=false;
//
//					break;
                    case KeyEvent.VK_Q:
                        addSpeed();
                        break;
                    case KeyEvent.VK_R:
                        isOver=false;
                        SleepCount=0;
                        initMap();
                        getNewTetris();
                        repaint();
                        speed=20;
                        goal=0;

                        break;
                    case KeyEvent.VK_S:
                        boolean isDown=true;
                        for(int i=0;i<4;i++)
                        {
                            if((game[tetris.getPoint(i).x][tetris.getPoint(i).y+1]==1)||(game[tetris.getPoint(i).x][tetris.getPoint(i).y+1]==3)||(game[tetris.getPoint(i).x][tetris.getPoint(i).y+1]==4))//左边不是墙,就移动
                            {
                                isDown=false;
                            }
                        }
                        if(isDown)
                        {
                            tetris.setY(tetris.getY()+1);
                            tetris.makeTetris();
                            clear();
                            initTetris();
                            repaint();
                        }
                        break;
                    case KeyEvent.VK_W:
                        boolean ischange=true;
                        tetris.changeTetris();
                        for(int i=0;i<4;i++)
                        {
                            if((game[tetris.getPoint(i).x][tetris.getPoint(i).y]==1)||(game[tetris.getPoint(i).x][tetris.getPoint(i).y]==3)||(game[tetris.getPoint(i).x][tetris.getPoint(i).y]==4))//左边不是墙,就移动
                            {
                                ischange=false;
                            }
                        }
                        if(!ischange)
                        {
                            tetris.rechangeTetris();
                            clear();
                            initTetris();
                            repaint();
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                        break;
                    case KeyEvent.VK_E:
                        slowSpeed();
                        break;
                    case KeyEvent.VK_SPACE:
                        isSpace++;
                        break;
                    case KeyEvent.VK_A:

                        boolean leftMoveable=true;
                        for(int i=0;i<4;i++)
                        {
                            if((game[tetris.getPoint(i).x-1][tetris.getPoint(i).y]==1)||(game[tetris.getPoint(i).x-1][tetris.getPoint(i).y]==3))//左边不是墙,就移动
                            {
                                leftMoveable=false;
                            }
                        }
                        if(leftMoveable)
                        {
                            tetris.setX(tetris.getX()-1);
                            tetris.makeTetris();
                            clear();
                            initTetris();
                            repaint();
                        }
                        break;
                    case KeyEvent.VK_D:
                        boolean rightMoveable=true;
                        shouldWait=true;
                        for(int i=0;i<4;i++)
                        {
                            if((game[tetris.getPoint(i).x+1][tetris.getPoint(i).y]==1)||(game[tetris.getPoint(i).x+1][tetris.getPoint(i).y]==3))//左边不是墙,就移动
                            {
                                rightMoveable=false;
                            }
                        }
                        if(rightMoveable)
                        {
                            tetris.setX(tetris.getX()+1);
                            tetris.makeTetris();
                            clear();
                            initTetris();
                            repaint();
                        }
                        shouldWait=false;
                        break;
                    default:break;
                }
            }


        });
        while(!shouldWait)
        {
            autoMove();
        }
    }

    private void autoMove() throws InterruptedException
    {
        isMoveable();
        if(moveable&&isSpace%2==1)
            move();

        clearLine();
        //initMap();
        //initSmallTetris();
        clear();
        initTetris();
        repaint();
        Thread.sleep(5000/speed);
    }
    private void clearLine()
    {
        int count=0;
        for(int j=HEIGHT-3;j>0;j--)
        {//这里i,j反过来了
            for(int i=0;i<WIDTH;i++)
            {
                if(game[i][j]==3)
                    count++;
            }
            if(count==(WIDTH-4))
            {

                for(int j3=2;j3<WIDTH-2;j3++)
                {
                    //别把墙给请了
                    //先清0,再把老的下移动
                    game[j3][j]=0;

                }
                for(int i=j;i>2;i--)
                {
                    for(int x=0;x<WIDTH;x++)
                        if(game[x][i]==3&&game[x][i+1]==0)
                        {
                            game[x][i]=0;
                            game[x][i+1]=3;
                        }
                }


                goal+=100;
            }
            count=0;
        }
    }
    private void isOver() throws InterruptedException
    {
        for(int i=0;i<WIDTH;i++)
        {
            if(game[i][2]==3)
            {
                isOver=true;
                initMap();
                repaint();
                while(isOver)
                {
                    Thread.sleep(200);
                    SleepCount++;

                    if(SleepCount==15)
                        System.exit(0);
                }

            }
        }

    }
    private void clear()
    {
        for(int i=0;i<WIDTH;i++)
        {
            for(int j=0;j<HEIGHT;j++)
            {
                //把下落着的方块变为空白就清理了痕迹
                if(game[i][j]==2)
                    game[i][j]=0;
            }
        }


    }
    private void isMoveable() throws InterruptedException
    {

        for(int i=0;i<4;i++)
        {
            //1为墙,3为老的,不能动的tetris
            if((game[tetris.getPoint(i).x][tetris.getPoint(i).y+1]==3)||(game[tetris.getPoint(i).x][tetris.getPoint(i).y+1]==4))
            {
                moveable=false;
                isLao=true;
                //变为老的,不能动的tetris,并赋值为3

            }
            else
            {
                moveable=true;
            }
        }
        if(isLao)
        {
            Thread.sleep(400);
            for(int i=0;i<4;i++)
            {
                //变为老的,不能动的tetris,并赋值为3
                game[tetris.getPoint(i).x][tetris.getPoint(i).y]=3;
            }
            isOver();
            //产生新的tetris,并且能动
            getNewTetris();
            clearSmall();
            initSmallTetris();
            moveable=true;
            isLao=false;
        }

    }
    public void clearSmall()
    {
        //把小窗口的也清理了
        for(int i=0;i<WIDTH_SMALL;i++)
        {
            for(int j=0;j<HEIGHT_SMALL;j++)
            {
                //把下落着的方块变为空白就清理了痕迹
                if(small[i][j]==2)
                    small[i][j]=0;
            }
        }
    }
    private void move()
    {
        tetris.setY(tetris.getY()+1);//向下移动一格
        tetris.makeTetris();//所有的坐标向下加一
    }
    private void getNewTetris()//随机产生方块序号,给方块赋值为2
    {
        tetris = new Tetris(flag,firstX,firstY);
        initTetris();
        tetris_small=new Tetris(nextflag,WIDTH_SMALL/2,HEIGHT_SMALL/2);
        initSmallTetris();
        flag=nextflag;
        nextflag=rand.nextInt(21)+1;//产生的序号为1到21

    }
    private void initSmallTetris()
    {
        for(int i=0;i<4;i++)
        {
            small[tetris_small.getPoint(i).x][tetris_small.getPoint(i).y]=2;
        }
    }
    private void addSpeed()
    {
        speed+=5;
    }
    private void slowSpeed()
    {
        if(speed>5)
            speed-=5;
    }
    public void initTetris()
    {
        for(int i=0;i<4;i++)
        {
            if(tetris.getPoint(i).y>=2)
                game[tetris.getPoint(i).x][tetris.getPoint(i).y]=2;
        }
    }
    public static void main (String [] args) throws InterruptedException
    {
        maintest = new MainTest();
        frame = new JFrame("俄罗斯方块");
        frame.setLayout(new BorderLayout());//设置为边界布局管理器
        frame.setSize((WIDTH+WIDTH_SMALL)*WIDTH_CELL+20, HEIGHT*HEIGHT_CELL+49);//尺寸大小为三个区域的大小*小方块长度,稍微加长点,适应屏幕
        frame.setLocation(450, 50);
        frame.add(maintest, BorderLayout.CENTER);
        frame.setVisible(true);
        //main函数是静态的,里面只能用静态的方法

        maintest.start();

    }
}
