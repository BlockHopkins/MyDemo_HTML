var CANVAS_SIZE = 500;//画布大小
var DIMENSION = 14;//每行格子数
var GRID_SIZE = 30;//每个格子大小
var CHESS_RADIUS = 10;//棋子半径
var WIN_CHESS_COUNT = 5;//胜利条件-棋子个数
var COLOR_BLACK = "#000000";//黑色
var COLOR_WHITE = "#FFFFFF";//白色
var COLORS = [COLOR_BLACK,COLOR_WHITE]//当前棋子颜色
var DATA_SIZE = DIMENSION+1;//棋盘位置横纵线数目
var PADDING_SIZE = (CANVAS_SIZE - GRID_SIZE * DIMENSION)/2;//棋盘边距
var WIN_MSG = ["黑方获得了胜利","白方获得了胜利"];//胜利消息

var currentTurn = 0;//当前棋子颜色：[黑色棋子:0 ; 白色棋子:1 ](开局黑先白后)

var chessData = null;//棋盘数组，记录棋子位置： [-1:未着子；0:黑子; 1白子]
var isOver = false;
var recordStack = [];//记录栈，记录着子步骤
var undoStack = [];//悔棋栈，存储已悔棋的步骤


$(document).ready(function(){
	initBasic();
	initData();
	$("#btn_reset").on("click",resetGame);
	$("#btn_undo").on("click",undo);
	$("#btn_redo").on("click",redo);
	$("#canvas_record").on("click",playEvent);
});

/**
 *	初始化数据
 */
var initData = function(){
	chessData = new Array(DATA_SIZE);
	for(var i = 0; i < 15; i++){
		chessData[i] = new Array(DATA_SIZE);
		for(var j = 0; j < 15; j++){
			chessData[i][j] = -1;
		}
	}
	isOver = false;
	currentTurn = 0;
	$("#span_msg").text("");
}

/**
 *	初始化棋盘
 */
var initBasic = function(){
	var canvasBasic = $("#canvas_basic").get(0);
	var ctx = canvasBasic.getContext("2d");
	//清空画布
	ctx.clearRect(0,0,canvasBasic.width,canvasBasic.height);

	//话棋盘格
	var currentPos = PADDING_SIZE;

	for(var i = 0; i <= DIMENSION; i++){
		currentPos = PADDING_SIZE + i * GRID_SIZE;
		//横线
		ctx.moveTo(PADDING_SIZE, currentPos);
		ctx.lineTo(CANVAS_SIZE-PADDING_SIZE, currentPos);
		ctx.stroke();
		//纵线
		ctx.moveTo(currentPos, PADDING_SIZE);
		ctx.lineTo(currentPos, CANVAS_SIZE-PADDING_SIZE);
		ctx.stroke();
	}

	//画五个定位点
	drawArc(ctx, PADDING_SIZE+3*GRID_SIZE, PADDING_SIZE+3*GRID_SIZE, 3, "#000000");
	drawArc(ctx, PADDING_SIZE+3*GRID_SIZE, CANVAS_SIZE-(PADDING_SIZE+3*GRID_SIZE), 3, "#000000");
	drawArc(ctx, CANVAS_SIZE-(PADDING_SIZE+3*GRID_SIZE), PADDING_SIZE+3*GRID_SIZE, 3, "#000000");
	drawArc(ctx, CANVAS_SIZE-(PADDING_SIZE+3*GRID_SIZE), CANVAS_SIZE-(PADDING_SIZE+3*GRID_SIZE), 3, "#000000");
	drawArc(ctx, PADDING_SIZE+7*GRID_SIZE, PADDING_SIZE+7*GRID_SIZE, 5, "#000000");
}

/**
 *	画圆
 */
var drawArc = function(ctx,x,y,r,color){
	ctx.fillStyle = color;
	ctx.beginPath();
	ctx.arc(x, y, r, 0, 2*Math.PI, true);
	ctx.stroke();
	ctx.closePath();
	ctx.fill();
}

/**
 *	清除棋子记录
 */
var clearRecord = function(){
	var canvasRecord = $("#canvas_record").get(0);
	var ctx = canvasRecord.getContext("2d");
	ctx.clearRect(0,0,canvasRecord.width,canvasRecord.height);
}

/**
 *	画棋子
 */
var drawChess = function(x,y,color){
	var canvasRecord = $("#canvas_record").get(0);
	var ctx = canvasRecord.getContext("2d"); 
	drawArc(ctx, x*GRID_SIZE+PADDING_SIZE, y*GRID_SIZE+PADDING_SIZE, CHESS_RADIUS, color);
}
/**
 *	清除目标棋子
 */
var clearChess = function(x,y){
	var canvasRecord = $("#canvas_record").get(0);
	var ctx = canvasRecord.getContext("2d"); 
	ctx.clearRect(x*GRID_SIZE+PADDING_SIZE-CHESS_RADIUS, y*GRID_SIZE+PADDING_SIZE-CHESS_RADIUS, CHESS_RADIUS*2, CHESS_RADIUS*2);
}
/**
 *	重置游戏
 */
var resetGame = function(){
	clearRecord();
	initData();
}

/**
 *	着子事件
 */
var playEvent = function(event){
	if(isOver)
		return;
	var x = Math.round((event.offsetX - PADDING_SIZE)/GRID_SIZE);
	var y = Math.round((event.offsetY - PADDING_SIZE)/GRID_SIZE);
	if(x>0 && y>0 && x<DATA_SIZE && y<DATA_SIZE)
	if(chessData[x][y] == -1){
		drawChess(x,y,COLORS[currentTurn]);
		chessData[x][y] = currentTurn;

		if(isWin(x,y)){
			isOver = true;
			$("#span_msg").text(WIN_MSG[currentTurn]);
		}
		//记录步骤
		recordStack.push({x:x,y:y,turn:currentTurn});
		changeTurn();
		if(undoStack.length>0){
			undoStack = [];
		}
	}
}
/**
 *	悔棋
 */
var undo = function(){
	if(recordStack.length==0){
		return;
	}
	var record = recordStack.pop();
	undoStack.push(record);
	clearChess(record.x,record.y);
	chessData[record.x][record.y] = -1;
	if(isOver){
		isOver = false;
	}
	$("#span_msg").text("");
	currentTurn = record.turn;
}

/**
 *	撤销悔棋
 */
 var redo = function(){
 	if(undoStack.length==0){
 		return;
 	}
 	var record = undoStack.pop();
 	drawChess(record.x,record.y,COLORS[record.turn]);
	chessData[record.x][record.y] = record.turn;

	if(isWin(record.x,record.y)){
		isOver = true;
		$("#span_msg").text(WIN_MSG[record.turn]);
	}
	//记录步骤
	recordStack.push(record);
	currenturn = record.turn;
	changeTurn();
 }

/**
 *	是否胜利
 */
var isWin = function(x,y){
	var player = chessData[x][y];
	var count = 1;
	var i = 0;
	var j = 0;
	//检查左右
	//左
	for(i=x-1; i>=0; i--){
		if(chessData[i][y] == player)
			count++;
		else
			break;
	}
	//右
	for(i=x+1; i<=DATA_SIZE-1; i++){
		if(chessData[i][y] == player)
			count++;
		else
			break;
	}
	if(count >= WIN_CHESS_COUNT)
		return true;

	//检查上下
	count = 1;
	//上
	for(i=y-1; i>=0; i--){
		if(chessData[x][i] == player)
			count++;
		else
			break;
	}
	//下
	for(i=y+1; i<=DATA_SIZE-1; i++){
		if(chessData[x][i] == player)
			count++;
		else
			break;
	}
	if(count >= WIN_CHESS_COUNT)
		return true;

	//检查左上和右下
	count = 1;
	//左上
	for(i=x-1,j=y-1; i>=0 && j>=0; i--,j--){
		if(chessData[i][j] == player)
			count++;
		else
			break;
	}
	//右下
	for(i=x+1,j=y+1; i<=DATA_SIZE-1 && j<=DATA_SIZE-1; i++,j++){
		if(chessData[i][j] == player)
			count++;
		else
			break;
	}
	if(count >= WIN_CHESS_COUNT)
		return true;

	//检查左下和右上
	count = 1;
	//左下
	for(i=x-1,j=y+1; i>=0 && j<=DATA_SIZE-1; i--,j++){
		if(chessData[i][j] == player)
			count++;
		else
			break;
	}
	//右上
	for(i=x+1,j=y-1; i<=DATA_SIZE-1 && j>=0; i++,j--){
		if(chessData[i][j] == player)
			count++;
		else
			break;
	}
	if(count >= WIN_CHESS_COUNT)
		return true;

	//不符合胜利条件
	return false;
}
var changeTurn = function(){
	if(currentTurn == 0)
			currentTurn = 1;
		else
			currentTurn = 0;
}