let flag = Math.floor(Math.random() * 21 + 1)//随机数在[0,21]之间
let nextFlag = Math.floor(Math.random() * 21 + 1)//随机数在[0,21]之间
let tetris = new Tetris(flag, FIRST_X, FIRST_Y);//方块
let nextTetris = new Tetris(nextFlag, NEXT_X, NEXT_Y);//下一方块
let gameOver = false;
let moveDownId;
let movable = true;
let speed = 20;//下落速度
let scope = 0;//得分


window.onload = function ()
{
    init();
    append();
    draw();
    updateSmall();
    drawSmall();
    moveDownId = setInterval(moveDown, 5000/speed);
    addEventListener();
}

//添加监听事件
function addEventListener()
{
    document.addEventListener('keydown', event =>
    {
        if (event.keyCode === KEY.UP)
        {
            changeTetris(tetris);
            if (!isMovable())
            {
                rechangeTetris(tetris);
            }
        }
        else if(event.keyCode === KEY.DOWN)
        {
            tetris.point.x++;
            makeTetris(tetris);
            if(!isMovable())
            {
                tetris.point.x--;
                makeTetris(tetris);
            }
        }
        else if(event.keyCode === KEY.LEFT)
        {
            tetris.point.y--;
            makeTetris(tetris);
            if(!isMovable())
            {
                tetris.point.y++;
                makeTetris(tetris);
            }
        }
        else if(event.keyCode === KEY.RIGHT)
        {
            tetris.point.y++;
            makeTetris(tetris);
            if(!isMovable())
            {
                tetris.point.y--;
                makeTetris(tetris);
            }
        }
        else if(event.keyCode === KEY.SPACE)
        {
            movable = !movable;
        }
        else if(event.keyCode === KEY.SPEED_UP)
        {
            speed += 5;
            clearInterval(moveDownId);
            moveDownId = setInterval(moveDown, 5000/speed);
            document.getElementById("speed").innerText = speed;
        }
        else if(event.keyCode === KEY.SPEED_DOWN)
        {
            speed = Math.max(1,speed-3);
            clearInterval(moveDownId);
            moveDownId = setInterval(moveDown, 5000/speed);
            document.getElementById("speed").innerText = speed;
        }
    });
}

//更新board
function updateBoard()
{
    //把上次的新的给擦除
    for (let i = 0; i < HEIGHT_GAME; i++)
    {
        for (let j = 0; j < WIDTH_GAME; j++)
        {
            if (board[i][j] === NEW) board[i][j] = EMPTY;
        }
    }

    for (let i = 0; i < tetris.tetrisPoint.length; i++)
    {
            board[tetris.tetrisPoint[i].x][tetris.tetrisPoint[i].y] = NEW;
    }
}

//更新下一方块区
function updateSmall()
{
    for (let i = 0; i < HEIGHT_SMALL; i++)
    {
        for (let j = 0; j < WIDTH_SMALL; j++)
        {
            nextBoard[i][j] = EMPTY;
        }
    }
    for (let i = 0; i < nextTetris.tetrisPoint.length; i++)
    {
        nextBoard[nextTetris.tetrisPoint[i].x][nextTetris.tetrisPoint[i].y] = NEW;
    }
}

//碰撞检测
function isMovable()
{
    for (let i = 0; i < tetris.tetrisPoint.length; i++)
    {
        if(tetris.tetrisPoint[i].x<0 || tetris.tetrisPoint[i].x>=board.length) return false;
        if(tetris.tetrisPoint[i].y<0 || tetris.tetrisPoint[i].y>=board[0].length) return false;
        let value = board[tetris.tetrisPoint[i].x][tetris.tetrisPoint[i].y];
        if (value === OLD || value === WALL) return false;
    }
    return true;
}

//向下移动
function moveDown()
{
    if(movable) //实现暂停功能
    {
        //将整体都向下移动一格
        tetris.point.x++;
        makeTetris(tetris);

        if (!isMovable())
        {
            newStart();
        }
        else
        {
            updateBoard();
            draw();
        }
    }
}

//当一个方块下落到不能继续下落时
function newStart()
{
    //将整体都向上移动一格,抵消改变
    tetris.point.x--;
    makeTetris(tetris);

    //将新的变成老的
    updateBoard();
    for (let i = 0; i < tetris.tetrisPoint.length; i++)
    {
        board[tetris.tetrisPoint[i].x][tetris.tetrisPoint[i].y] = OLD;
    }

    //判断清除行的时机
    clearLine();
    draw();

    if (isOver())
    {
        newGame();
    }
    else
    {
        //新开个tetris
        flag = nextFlag;
        nextFlag = Math.floor(Math.random() * 21 + 1);
        tetris = nextTetris;
        tetris.point.x = FIRST_X;//从中间下落
        tetris.point.y = FIRST_Y;
        nextTetris = new Tetris(flag, NEXT_X, NEXT_Y);//方块

        //绘制下一方块
        updateSmall();
        drawSmall();
    }
}

function newGame()
{
    init();
    draw();
    clearInterval(moveDownId);
}

//判断游戏是否结束
function isOver()
{
    for (let j = 0; j < WIDTH_GAME; j++)
    {
        if (board[0][j] === OLD)
        {
            gameOver = true;
            break;
        }
    }
    if (board[2][FIRST_Y] === OLD) gameOver = true;
    return gameOver;
}

//消除满行
function clearLine()
{
    for (let i = HEIGHT_GAME-2; i>=1 ; i--)
    {
        let count = 0;
        for (let j= WIDTH_GAME-2; j >=1 ; j--)//数一行有多少个方块
        {
            if(board[i][j]===OLD) ++count;
        }
        if(count===WIDTH_GAME-2)//一行全是方块
        {
            //先清除这一行
            for (let j = WIDTH_GAME-2; j >=1 ; j--)
            {
                board[i][j] = EMPTY;
            }
            //让他们上面的下移
            for (let k = i+1; k>=1 ; k--)
            {
                for (let j = WIDTH_GAME-2; j >=1 ; j--)
                {
                    let temp=k;
                    while(board[temp][j]===OLD && board[temp+1][j]===EMPTY)
                    {
                        board[temp][j] = EMPTY;
                        board[temp + 1][j] = OLD;
                        temp++;
                    }
                }
            }
            //重新从底部开始扫描,因为可能消去之后下面的区域又全满了
            i = HEIGHT_GAME - 1;

            scope += 100;
            document.getElementById("scope").innerText = scope;

        }

    }
}

