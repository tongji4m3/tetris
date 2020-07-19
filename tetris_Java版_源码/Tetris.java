import java.awt.Point;

public class Tetris
{
    private int flag;
    private int x;
    private int y;
    private Point[] tetrisPoint = new Point[4];

    public Tetris()
    {
    }

    public Tetris(int flag, int x, int y)
    {
        this.flag = flag;
        this.x = x;
        this.y = y;
        makeTetris();
    }

    public void makeTetris()
    {
        switch (flag)
        {
            case 1:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x + 1, y);
                tetrisPoint[2] = new Point(x, y + 1);
                tetrisPoint[3] = new Point(x + 1, y + 1);
            }
            break;
            case 2://----
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x - 1, y);
                tetrisPoint[2] = new Point(x + 1, y);
                tetrisPoint[3] = new Point(x + 2, y);
            }
            break;
            case 3:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x, y - 1);
                tetrisPoint[2] = new Point(x, y + 1);
                tetrisPoint[3] = new Point(x, y + 2);
            }
            break;
            case 4:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x + 1, y);
                tetrisPoint[2] = new Point(x - 1, y);
                tetrisPoint[3] = new Point(x - 2, y);
            }
            break;
            case 5:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x, y + 1);
                tetrisPoint[2] = new Point(x, y - 1);
                tetrisPoint[3] = new Point(x, y - 2);
            }
            break;
            case 6:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x + 1, y);
                tetrisPoint[2] = new Point(x, y - 1);
                tetrisPoint[3] = new Point(x, y - 2);
            }
            break;
            case 7:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x + 1, y);
                tetrisPoint[2] = new Point(x + 2, y);
                tetrisPoint[3] = new Point(x, y + 1);
            }
            break;
            case 8:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x - 1, y);
                tetrisPoint[2] = new Point(x, y + 1);
                tetrisPoint[3] = new Point(x, y + 2);
            }
            break;
            case 9:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x - 1, y);
                tetrisPoint[2] = new Point(x - 2, y);
                tetrisPoint[3] = new Point(x, y - 1);
            }
            break;
            case 10:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x, y - 1);
                tetrisPoint[2] = new Point(x, y - 2);
                tetrisPoint[3] = new Point(x - 1, y);
            }
            break;
            case 11:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x, y - 1);
                tetrisPoint[2] = new Point(x + 1, y);
                tetrisPoint[3] = new Point(x + 2, y);
            }
            break;
            case 12:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x + 1, y);
                tetrisPoint[2] = new Point(x, y + 1);
                tetrisPoint[3] = new Point(x, y + 2);
            }
            break;
            case 13:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x - 1, y);
                tetrisPoint[2] = new Point(x - 2, y);
                tetrisPoint[3] = new Point(x, y + 1);
            }
            break;
            case 14:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x - 1, y);
                tetrisPoint[2] = new Point(x, y + 1);
                tetrisPoint[3] = new Point(x + 1, y + 1);
            }
            break;
            case 15:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x, y - 1);
                tetrisPoint[2] = new Point(x - 1, y);
                tetrisPoint[3] = new Point(x - 1, y + 1);
            }
            break;
            case 16:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x + 1, y);
                tetrisPoint[2] = new Point(x, y - 1);
                tetrisPoint[3] = new Point(x - 1, y - 1);
            }
            break;
            case 17:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x, y + 1);
                tetrisPoint[2] = new Point(x + 1, y);
                tetrisPoint[3] = new Point(x + 1, y - 1);
            }
            break;
            case 18:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x + 1, y);
                tetrisPoint[2] = new Point(x, y + 1);
                tetrisPoint[3] = new Point(x - 1, y + 1);
            }
            break;
            case 19:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x, y + 1);
                tetrisPoint[2] = new Point(x - 1, y);
                tetrisPoint[3] = new Point(x - 1, y - 1);
            }
            break;
            case 20:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x - 1, y);
                tetrisPoint[2] = new Point(x, y - 1);
                tetrisPoint[3] = new Point(x + 1, y - 1);
            }
            break;
            case 21:
            {
                tetrisPoint[0] = new Point(x, y);
                tetrisPoint[1] = new Point(x + 1, y);
                tetrisPoint[2] = new Point(x, y - 1);
                tetrisPoint[3] = new Point(x + 1, y + 1);
            }
            break;
            default:
                break;

        }

    }

    public void changeTetris()
    {
        switch (flag)
        {
            case 1:
                break;

            case 2:
                flag++;
                break;
            case 3:
                flag++;
                break;
            case 4:
                flag++;
                break;
            case 5:
                flag = 2;
                break;

            case 6:
                flag++;
                break;
            case 7:
                flag++;
                break;
            case 8:
                flag++;
                break;
            case 9:
                flag = 6;
                break;

            case 10:
                flag++;
                break;
            case 11:
                flag++;
                break;
            case 12:
                flag++;
                break;
            case 13:
                flag = 10;
                break;

            case 14:
                flag++;
                break;
            case 15:
                flag++;
                break;
            case 16:
                flag++;
                break;
            case 17:
                flag = 14;
                break;

            case 18:
                flag++;
                break;
            case 19:
                flag++;
                break;
            case 20:
                flag++;
                break;
            case 21:
                flag = 18;
                break;
        }
        makeTetris();
    }

    public void rechangeTetris()
    {
        switch (flag)
        {
            case 1:
                break;

            case 2:
                flag = 5;
                break;
            case 3:
                flag--;
                break;
            case 4:
                flag--;
                break;
            case 5:
                flag--;
                break;

            case 6:
                flag = 9;
                break;
            case 7:
                flag--;
                break;
            case 8:
                flag--;
                break;
            case 9:
                flag--;
                break;

            case 10:
                flag = 10;
                break;
            case 11:
                flag--;
                break;
            case 12:
                flag--;
                break;
            case 13:
                flag--;
                break;

            case 14:
                flag = 17;
                break;
            case 15:
                flag--;
                break;
            case 16:
                flag--;
                break;
            case 17:
                flag--;
                break;

            case 18:
                flag = 21;
                break;
            case 19:
                flag--;
                break;
            case 20:
                flag--;
                break;
            case 21:
                flag--;
                break;
        }
        makeTetris();
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getX()
    {
        return x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getY()
    {
        return y;
    }

    public Point getPoint(int i)
    {
        return tetrisPoint[i];
    }

    public void setFlag(int nextflag)
    {
        flag = nextflag;
    }
}
