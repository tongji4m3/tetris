const WIDTH_GAME = 20//游戏主界面的宽
const HEIGHT_GAME = 24//游戏主界面的高

const WIDTH_SMALL = 6;//设置小屏幕的宽
const HEIGHT_SMALL = 6;//设置小屏幕的高

const FIRST_X = 2; //定义初始方块的位置 防止数组越界(因为有的中心坐标上面还有数组)
const FIRST_Y = WIDTH_GAME / 2;

const NEXT_X = 2;//定义next区域的显示位置
const NEXT_Y = (WIDTH_SMALL / 2) - 1;

const EMPTY = 0;//代表为空
const OLD = 1;//代表已经有落下的值
const NEW = 2;//代表是当前的值
const WALL = 3;//代表是墙

//使用到的键盘的键与值的对应
const KEY = {
    LEFT: 37,
    UP: 38,
    RIGHT: 39,
    DOWN: 40,
    SPACE: 32,
    SPEED_UP: 107,
    SPEED_DOWN: 109
}

const COLORS = ['red', 'orange', 'yellow', 'green', 'blue', 'purple'];//颜色
