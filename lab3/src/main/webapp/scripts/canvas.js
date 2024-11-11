import { failColor, successColor } from './script.js';

function makeCanvas(r) {
    let middleInt = width/(2*scaleX);
    ctx.clearRect(0, 0, width, height);
    ctx.beginPath();
    ctx.strokeStyle = netColor;
    ctx.font = "10px Arial";
    ctx.textAlign = "left";
    ctx.textBaseline = "top";
    ctx.fillStyle = netColor;

    for (let i = 0; i <= width; i += scaleX) {

        ctx.moveTo(i, 0);
        ctx.lineTo(i, height);

        if ((i - xAxis) === 0) {
            ctx.fillText(String(((i - xAxis) / (scaleX)).toFixed(1)), i, yAxis + 2);
        } else if (!((i - xAxis) / scaleX <= -middleInt)) {
            ctx.fillText(((i - xAxis) / (scaleX)).toFixed(1) , i, yAxis + 2);
        }
    }

    for (let i = 0; i <= height; i += scaleY) {
        ctx.moveTo(0, i);
        ctx.lineTo(width, i);

        if ((yAxis - i) !== 0 && (yAxis - i) / scaleY < middleInt && (yAxis - i) / scaleY > -middleInt) {
            ctx.fillText(((yAxis - i) / (scaleY)).toFixed(1) , xAxis + 2, i);
        }
    }

    ctx.stroke();
    ctx.closePath();

    ctx.beginPath();
    ctx.strokeStyle = axesColor;
    ctx.moveTo(xAxis, 0);
    ctx.lineTo(xAxis, height);
    ctx.fillText("y", xAxis - 10, 10);

    ctx.moveTo(0, yAxis);
    ctx.lineTo(width, yAxis);
    ctx.fillText("x", width - 10, yAxis + 10);

    ctx.stroke();
    ctx.closePath();
    makeGraphics(r);
}

function makeGraphics(r) {
    ctx.globalAlpha = 0.3;

    // Квадрат
    ctx.fillStyle = graphColor;
    ctx.fillRect(xAxis, yAxis, xSquare * scaleX * r / 2, ySquare * scaleY * r / 2);

    // Треугольник
    ctx.beginPath();
    ctx.moveTo(xAxis, yAxis);
    ctx.lineTo(xAxis, yAxis - scaleY * r / 2);
    ctx.lineTo(xAxis - scaleX * r / 2, yAxis);
    ctx.closePath();
    ctx.fill();

    // Четверть круга
    ctx.beginPath();
    ctx.arc(xAxis, yAxis, radius * r / 2, Math.PI / 2, Math.PI);
    ctx.lineTo(xAxis, yAxis);
    ctx.closePath();
    ctx.fill();

    ctx.fillStyle = "black";
    ctx.globalAlpha = 1;
}

export function makeAjaxPoint(){
    let x = document.getElementById("xValue").value;
    let y = document.getElementById("yValue").value;
    let r = document.getElementById("rValue").value;
    let status = document.getElementById("statusValue").value;
    makePoint(x,y,r,status);
}

function makePoint(x, y, r, status){
    ctx.clearRect(0, 0, width, height);
    ctx.fillStyle = "black";
    makeCanvas(r);

    ctx.beginPath();
    ctx.arc(xAxis +(x)*scaleX, yAxis-(y)*scaleY, markerRadius, 0, 2 * Math.PI, false);
    if(status===true || status==="true"){
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

export function updateCanvas(r) {
    makeCanvas(r || 1);
}


const markerRadius = 3;
const pointStyle = "#003300";
const netColor = "#c7c7c7";
const axesColor = "#000000";
const graphColor = "#0047ab";
const canvasPlot = document.getElementById("canvasPlot");
const ctx = canvasPlot.getContext("2d");
const width = canvasPlot.clientWidth;
const height = canvasPlot.clientHeight;
const targetValue = 4;
const scaleX = Math.floor(width / (2 * targetValue));
const scaleY = Math.floor(width / (2 * targetValue));
const xAxis = 150;
const yAxis = 150;

const xSquare = 2;
const ySquare = 2;
const radius = 2 * scaleX;

makeCanvas(document.getElementById('rValue').value);
canvasPlot.addEventListener('click', function(event) {
    let rect = canvasPlot.getBoundingClientRect(); //данные об окне (расстояние от краев и тд)
    let x = (event.clientX - rect.left);
    let y = (event.clientY - rect.top);
    PrimeFaces.widgets['spinnerX'].setValue(((x - xAxis)/scaleX).toFixed(3));
    document.querySelector('.ySelection').value = (-(y - yAxis)/scaleY).toFixed(3);
    document.querySelector('.button').click();
});