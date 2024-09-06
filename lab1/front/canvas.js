function makeCanvas(){
	ctx.beginPath();
	ctx.strokeStyle = netColor;
	ctx.font = "10px Arial";
	ctx.textAlign = "left";
	ctx.textBaseline = "top";

	for(let i=0; i<= width; i = i + scaleX) {
		ctx.moveTo(i, 0);
		ctx.lineTo(i, height);
		if((i-xAxis)==0){
			ctx.fillText((i-xAxis)/(2*scaleX),i, yAxis+2);	
		}else if(!(((i-xAxis)/scaleX)<=-3)){
			ctx.fillText((i-xAxis)/(2*scaleX) + "R",i, yAxis+2);
		}
	}

	for(let i=0; i<= height; i = i + scaleY){
		ctx.moveTo(0, i);
		ctx.lineTo(width, i);
		if((yAxis-i)!=0 && ((yAxis-i)/scaleY)<3 && ((yAxis-i)/scaleY)>-3){
			ctx.fillText((yAxis-i)/(2*scaleY) + "R",xAxis+2, i);
		}
	}

	ctx.stroke();
	ctx.closePath;

	//axes
	ctx.beginPath();
	ctx.strokeStyle = axesColor;
	ctx.moveTo(xAxis, 0);
	ctx.lineTo(xAxis, height);
	ctx.fillText("y", xAxis-20, 0);

	ctx.moveTo(0, yAxis);
	ctx.lineTo(width, yAxis);
	ctx.fillText("x", width-40, yAxis-15);

	ctx.stroke();
	ctx.closePath();
	makeGraphics();
	makeArrows();
}	

function makeArrows(){
	ctx.beginPath();
	ctx.strokeStyle = "black";
	//x Arrow
	ctx.moveTo(arrow2*scaleX-arrow1, yAxis-arrow3);
	ctx.lineTo(arrow2*scaleX, yAxis);
	ctx.moveTo(arrow2*scaleX-arrow1, yAxis+arrow3);
	ctx.lineTo(arrow2*scaleX, yAxis);
	//y Arrow
	ctx.moveTo((arrow2/2)*scaleX-arrow3*2, arrow1/2);
	ctx.lineTo((arrow2/2)*scaleX, 0);
	ctx.moveTo((arrow2/2)*scaleX+arrow3*2, arrow1/2);
	ctx.lineTo((arrow2/2)*scaleX, 0);
	ctx.stroke();
	ctx.closePath();
}

function makeGraphics(){
	ctx.globalAlpha = 0.3;

	//square
	ctx.fillStyle = graphColor;
	ctx.fillRect(xAxis, yAxis, xSquare*scaleX, ySquare*scaleY);
	//triangle
	ctx.beginPath()
	ctx.moveTo(xAxis, yAxis);
	ctx.lineTo(xAxis, yAxis+2*scaleY);
	ctx.lineTo(xAxis-2*scaleX, yAxis);
	ctx.lineTo(xAxis, yAxis);
	ctx.closePath();
	ctx.fill();
	//circle
	ctx.beginPath();
	ctx.arc(xAxis, yAxis+1.5*scaleY, 2.5*scaleY, 3/2*Math.PI, -0.643501, false); //координаты центра, радиус, начальный угол в радианах, конечный угол в радианах
	ctx.lineTo(xAxis, yAxis);
	ctx.closePath();
	ctx.fill();
	//0.643501
	
	ctx.fillStyle = "black";
	ctx.globalAlpha = 1; //прозрачность на 100%
}


function isThisHit(x, y, r){
	//square
	if(r*xSquare*x>=0 && Math.abs(x)<=r && -r*ySquare*y>=0 && Math.abs(y) <=r){
		return true;
	}
	//triangle
	if(isInTriangle(x, y, r)){
		return true;
	}
	//circle
	return isInCircle(x, y, r);
}
function isInTriangle(x,y,r){
	//проверка суммой площадей
	var s = r*r*Math.abs(x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2));
	var s1 = Math.abs(x*(r*y1-r*y2)+r*x1*(r*y2-y)+x2*(y-r*y1));
	var s2 = Math.abs(x*(r*y2-r*y3)+r*x2*(r*y3-y)+x3*(y-r*y2));
	var s3 = Math.abs(x*(r*y3-r*y1)+r*x3*(r*y1-y)+r*x1*(y-r*y3));
	
	return s==(s1+s2+s3);
}

function isInCircle(x, y, r){
	if(Math.sqrt(x*x + y*y)>Math.abs(r/2)){
		return false;
	}
	return (r*x>0 && r*y<0);
}

const netColor = "#c7c7c7"; 
const axesColor = "#000000";
const pointStyle = "#003300";
const graphColor = "#0047ab"; 
const canvasPlot = document.getElementById("canvasPlot");
const ctx = canvasPlot.getContext("2d");
const width = canvasPlot.clientWidth;
const height = canvasPlot.clientHeight;
const scaleX = 50;
const scaleY = scaleX/2;
const xAxis = 150;
const yAxis = xAxis/2;
const markerRadius = 3;

//squareCoords
const xSquare = 2;
const ySquare = 1;
//triangleCoords
const x1 = -1;
const x2 = 0;
const x3 = 0;
const y1 = 0;
const y2 = 0;
const y3 = -1;
//Arows const
const arrow1 = 10;
const arrow2 = 6;
const arrow3 = 3;

makeCanvas();