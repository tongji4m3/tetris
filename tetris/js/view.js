//定义游戏区二维数组
let board = new Array(HEIGHT_GAME);
//定义next区二维数组
let nextBoard = new Array(HEIGHT_SMALL);

//初始化函数
function init()
{
    //游戏区二维数组初始化
    for (let i = 0; i < HEIGHT_GAME; i++)
    {
        board[i] = new Array(WIDTH_GAME);
    }
    //next区二维数组初始化
    for (let i = 0; i < HEIGHT_SMALL; i++)
    {
        nextBoard[i] = new Array(WIDTH_SMALL);
    }

    //游戏区二维数组初始化
    for (let i = 0; i < HEIGHT_GAME; i++)
    {
        for (let j = 0; j < WIDTH_GAME; j++)
        {
            board[i][j] = EMPTY;
        }
        board[i][0] = WALL;
        board[i][WIDTH_GAME - 1] = WALL;
    }
    for (let j = 0; j < WIDTH_GAME; j++)
    {
        board[HEIGHT_GAME - 1][j] = WALL;
    }

    //next二维数组初始化
    for (let i = 0; i < HEIGHT_SMALL; i++)
    {
        for (let j = 0; j < WIDTH_SMALL; j++)
        {
            nextBoard[i][j] = EMPTY;
        }
    }
}

//在屏幕上动态添加表格
function append()
{
    //游戏区动态添加元素
    let boardTable = document.getElementById("boardTable");
    for (let i = 0; i < HEIGHT_GAME; i++)
    {
        let tr = document.createElement("tr");
        for (let j = 0; j < WIDTH_GAME; j++)
        {
            let td = document.createElement("td");
            tr.appendChild(td);
        }
        boardTable.appendChild(tr);
    }

    //下一方块区动态添加元素
    let nextBoardTable = document.getElementById("nextBoardTable");
    for (let i = 0; i < HEIGHT_SMALL; i++)
    {
        let tr = document.createElement("tr");
        for (let j = 0; j < WIDTH_SMALL; j++)
        {
            let td = document.createElement("td");
            tr.appendChild(td);
        }
        nextBoardTable.appendChild(tr);
    }
}

//绘图函数
function draw()
{
    let boardTable = document.getElementById("boardTable");
    for (let i = 0; i < HEIGHT_GAME; i++)
    {
        for (let j = 0; j < WIDTH_GAME; j++)
        {
            if (board[i][j] === EMPTY)
            {
                boardTable.rows[i].cells[j].style.backgroundColor = "white";
            } else if (board[i][j] === OLD)
            {
                boardTable.rows[i].cells[j].style.backgroundColor = "gray";
            } else if (board[i][j] === NEW)
            {
                boardTable.rows[i].cells[j].style.backgroundColor = tetris.color;
            } else if (board[i][j] === WALL)
            {
                boardTable.rows[i].cells[j].style.backgroundColor = "black";
            }
        }
    }
}

//next区的绘图函数
function drawSmall()
{
    let nextBoardTable = document.getElementById("nextBoardTable");
    for (let i = 0; i < HEIGHT_SMALL; i++)
    {
        for (let j = 0; j < WIDTH_SMALL; j++)
        {
            if(nextBoard[i][j] === EMPTY)
            {
                nextBoardTable.rows[i].cells[j].style.backgroundColor = "white";
            }
            else if(nextBoard[i][j] === NEW)
            {
                nextBoardTable.rows[i].cells[j].style.backgroundColor = nextTetris.color;
            }
        }
    }
}
