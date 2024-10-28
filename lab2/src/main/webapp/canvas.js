import { doFetch } from './script.js';
import { failColor } from './script.js';
import { successColor } from './script.js';

function makeCanvas(){
	ctx.beginPath();
	ctx.strokeStyle = netColor;
	ctx.font = "10px Arial";
	ctx.textAlign = "left";
	ctx.textBaseline = "top";

	for(let i=0; i<= width; i = i + scaleX) {
		ctx.moveTo(i, 0);
		ctx.lineTo(i, height);
		if((i-xAxis)===0){
			ctx.fillText(String((i-xAxis)/(2*scaleX)),i, yAxis+2);
		}else if(!(((i-xAxis)/scaleX)<=-3)){
			ctx.fillText((i-xAxis)/(2*scaleX) + "R",i, yAxis+2);
		}
	}

	for(let i=0; i<= height; i = i + scaleY){
		ctx.moveTo(0, i);
		ctx.lineTo(width, i);
		if((yAxis-i)!==0 && ((yAxis-i)/scaleY)<3 && ((yAxis-i)/scaleY)>-3){
			ctx.fillText((yAxis-i)/(2*scaleY) + "R",xAxis+2, i);
		}
	}

	ctx.stroke();
	ctx.closePath();

	//axes
	ctx.beginPath();
	ctx.strokeStyle = axesColor;
	ctx.moveTo(xAxis, 0);
	ctx.lineTo(xAxis, height);
	ctx.fillText("y", xAxis-10, 10);

	ctx.moveTo(0, yAxis);
	ctx.lineTo(width, yAxis);
	ctx.fillText("x", width-10, yAxis+10);

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
	ctx.moveTo(xAxis-arrow3, 300-(arrow2*scaleY-arrow1));
	ctx.lineTo(xAxis, 0);
	ctx.moveTo(xAxis+arrow3, 300-(arrow2*scaleY-arrow1));
	ctx.lineTo(xAxis, 0);
	ctx.stroke();
	ctx.closePath();
}

export function makePoint(x, y, r, status){
	ctx.clearRect(0, 0, width, height);
	ctx.fillStyle = "black";
	makeCanvas();

	ctx.beginPath();
	ctx.arc(xAxis +(2*x/r)*scaleX, yAxis-(2*y/r)*scaleY, markerRadius, 0, 2 * Math.PI, false);
	if(status==="true"){
		ctx.fillStyle = successColor;
	}else{
		ctx.fillStyle = failColor;
	}

	ctx.fill();
	ctx.lineWidth = 1;
	ctx.strokeStyle = pointStyle;
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
	ctx.lineTo(xAxis, yAxis-scaleY);
	ctx.lineTo(xAxis-scaleX, yAxis);
	ctx.lineTo(xAxis, yAxis);
	ctx.closePath();
	ctx.fill();
	//circle
	ctx.beginPath();
	ctx.arc(scaleX*3, scaleY*3, radius, Math.PI/2, Math.PI);
	ctx.lineTo(scaleX*3, scaleY*3);
	ctx.closePath();
	ctx.fill();
	//0.643501
	
	ctx.fillStyle = "black";
	ctx.globalAlpha = 1; //прозрачность на 100%
}
const markerRadius = 3;
const pointStyle = "#003300";
const netColor = "#c7c7c7"; 
const axesColor = "#000000";
//const pointStyle = "#003300";
const graphColor = "#0047ab"; 
const canvasPlot = document.getElementById("canvasPlot");
const ctx = canvasPlot.getContext("2d");
const width = canvasPlot.clientWidth;
const height = canvasPlot.clientHeight;
const scaleX = 50;
const scaleY = 50;
const xAxis = 150;
const yAxis = 150;
//const markerRadius = 3;

//squareCoords
const xSquare = 2;
const ySquare = 2;
//Arows const
const arrow1 = 10;
const arrow2 = 6;
const arrow3 = 3;
const rInput = document.getElementById("rInput");
//circle
const radius = 2*scaleX;
let lastPoint = JSON.parse(localStorage.getItem('lastPoint'));
if (lastPoint!= null){
	makePoint(lastPoint.x, lastPoint.y, lastPoint.r, lastPoint.status);
}else{
	makeCanvas();
}


canvasPlot.addEventListener('click', function(event) {
	let rect = canvasPlot.getBoundingClientRect(); //данные об окне (расстояние от краев и тд)
	let x = event.clientX - rect.left;
	let y = event.clientY - rect.top;
	let r = rInput.value.replace(",", ".");
	doFetch((x/50 - 3)*r/(2), -(y/50 - 3)*r/(2), r);
});