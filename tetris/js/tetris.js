//定义二维位置坐标类,方便坐标表示
function Point(x, y)
{
    this.x = x;
    this.y = y;
}

//代表每一个小方块
//属性分别是:位置,类型,颜色,整体坐标
function Tetris(flag, x, y)
{
    this.point = new Point(x, y);
    this.flag = flag;//传入的flag为[1-21]
    //随机选一种颜色,随机数在[0,length)之间
    this.color = COLORS[Math.floor(Math.random() * COLORS.length)];
    //一共21种形状,根据位置和类型生成
    this.tetrisPoint = new Array(4);
    makeTetris(this);
}

//根据(x,y)与flag,确定具体形状为21种的哪一种
makeTetris= function (tetris)
{
    let x = tetris.point.x;
    let y = tetris.point.y;
    switch (tetris.flag)
    {
        case 1:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x + 1, y);
            tetris.tetrisPoint[2] = new Point(x, y + 1);
            tetris.tetrisPoint[3] = new Point(x + 1, y + 1);
        }
            break;
        case 2:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x - 1, y);
            tetris.tetrisPoint[2] = new Point(x + 1, y);
            tetris.tetrisPoint[3] = new Point(x + 2, y);
        }
            break;
        case 3:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x, y - 1);
            tetris.tetrisPoint[2] = new Point(x, y + 1);
            tetris.tetrisPoint[3] = new Point(x, y + 2);
        }
            break;
        case 4:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x + 1, y);
            tetris.tetrisPoint[2] = new Point(x - 1, y);
            tetris.tetrisPoint[3] = new Point(x - 2, y);
        }
            break;
        case 5:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x, y + 1);
            tetris.tetrisPoint[2] = new Point(x, y - 1);
            tetris.tetrisPoint[3] = new Point(x, y - 2);
        }
            break;
        case 6: //L
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x + 1, y);
            tetris.tetrisPoint[2] = new Point(x, y - 1);
            tetris.tetrisPoint[3] = new Point(x, y - 2);
        }
            break;
        case 7:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x + 1, y);
            tetris.tetrisPoint[2] = new Point(x + 2, y);
            tetris.tetrisPoint[3] = new Point(x, y + 1);
        }
            break;
        case 8:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x - 1, y);
            tetris.tetrisPoint[2] = new Point(x, y + 1);
            tetris.tetrisPoint[3] = new Point(x, y + 2);
        }
            break;
        case 9:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x - 1, y);
            tetris.tetrisPoint[2] = new Point(x - 2, y);
            tetris.tetrisPoint[3] = new Point(x, y - 1);
        }
            break;
        case 10:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x, y - 1);
            tetris.tetrisPoint[2] = new Point(x, y - 2);
            tetris.tetrisPoint[3] = new Point(x - 1, y);
        }
            break;
        case 11:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x, y - 1);
            tetris.tetrisPoint[2] = new Point(x + 1, y);
            tetris.tetrisPoint[3] = new Point(x + 2, y);
        }
            break;
        case 12:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x + 1, y);
            tetris.tetrisPoint[2] = new Point(x, y + 1);
            tetris.tetrisPoint[3] = new Point(x, y + 2);
        }
            break;
        case 13:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x - 1, y);
            tetris.tetrisPoint[2] = new Point(x - 2, y);
            tetris.tetrisPoint[3] = new Point(x, y + 1);
        }
            break;
        case 14:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x - 1, y);
            tetris.tetrisPoint[2] = new Point(x, y + 1);
            tetris.tetrisPoint[3] = new Point(x + 1, y + 1);
        }
            break;
        case 15:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x, y - 1);
            tetris.tetrisPoint[2] = new Point(x - 1, y);
            tetris.tetrisPoint[3] = new Point(x - 1, y + 1);
        }
            break;
        case 16:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x + 1, y);
            tetris.tetrisPoint[2] = new Point(x, y - 1);
            tetris.tetrisPoint[3] = new Point(x - 1, y - 1);
        }
            break;
        case 17:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x, y + 1);
            tetris.tetrisPoint[2] = new Point(x + 1, y);
            tetris.tetrisPoint[3] = new Point(x + 1, y - 1);
        }
            break;
        case 18:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x + 1, y);
            tetris.tetrisPoint[2] = new Point(x, y + 1);
            tetris.tetrisPoint[3] = new Point(x - 1, y + 1);
        }
            break;
        case 19:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x, y + 1);
            tetris.tetrisPoint[2] = new Point(x - 1, y);
            tetris.tetrisPoint[3] = new Point(x - 1, y - 1);
        }
            break;
        case 20:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x - 1, y);
            tetris.tetrisPoint[2] = new Point(x, y - 1);
            tetris.tetrisPoint[3] = new Point(x + 1, y - 1);
        }
            break;
        case 21:
        {
            tetris.tetrisPoint[0] = new Point(x, y);
            tetris.tetrisPoint[1] = new Point(x + 1, y);
            tetris.tetrisPoint[2] = new Point(x, y - 1);
            tetris.tetrisPoint[3] = new Point(x + 1, y + 1);
        }
            break;
        default:
            break;
    }
}

//改变动作
changeTetris = function (tetris)
{
    //变体,每个基本有四种形态,除了田字形(只有一种)
    switch (tetris.flag)
    {
        case 1:
            break;

        case 2:
            tetris.flag++;
            break;
        case 3:
            tetris.flag++;
            break;
        case 4:
            tetris.flag++;
            break;
        case 5:
            tetris.flag = 2;
            break;

        case 6:
            tetris.flag++;
            break;
        case 7:
            tetris.flag++;
            break;
        case 8:
            tetris.flag++;
            break;
        case 9:
            tetris.flag = 6;
            break;

        case 10:
            tetris.flag++;
            break;
        case 11:
            tetris.flag++;
            break;
        case 12:
            tetris.flag++;
            break;
        case 13:
            tetris.flag = 10;
            break;

        case 14:
            tetris.flag++;
            break;
        case 15:
            tetris.flag++;
            break;
        case 16:
            tetris.flag++;
            break;
        case 17:
            tetris.flag = 14;
            break;

        case 18:
            tetris.flag++;
            break;
        case 19:
            tetris.flag++;
            break;
        case 20:
            tetris.flag++;
            break;
        case 21:
            tetris.flag = 18;
            break;
    }
    //改变了flag,还要改变具体的形状
    makeTetris(tetris);
}

//撤销改变,例如改变后发现空间不足,则立刻撤销改变
rechangeTetris = function (tetris)
{
    switch (tetris.flag)
    {
        case 1:
            break;

        case 2:
            tetris.flag = 5;
            break;
        case 3:
            tetris.flag--;
            break;
        case 4:
            tetris.flag--;
            break;
        case 5:
            tetris.flag--;
            break;

        case 6:
            tetris.flag = 9;
            break;
        case 7:
            tetris.flag--;
            break;
        case 8:
            tetris.flag--;
            break;
        case 9:
            tetris.flag--;
            break;

        case 10:
            tetris.flag = 13;
            break;
        case 11:
            tetris.flag--;
            break;
        case 12:
            tetris.flag--;
            break;
        case 13:
            tetris.flag--;
            break;

        case 14:
            tetris.flag = 17;
            break;
        case 15:
            tetris.flag--;
            break;
        case 16:
            tetris.flag--;
            break;
        case 17:
            tetris.flag--;
            break;

        case 18:
            tetris.flag = 21;
            break;
        case 19:
            tetris.flag--;
            break;
        case 20:
            tetris.flag--;
            break;
        case 21:
            tetris.flag--;
            break;
    }
    //改变了flag,还要改变具体的形状
    makeTetris(tetris);
}
