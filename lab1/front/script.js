 
function checkNumber(a){
	return !(a>=maxNum || a<=-maxNum);
}	

function makePoint(x, y, r, status){
	ctx.clearRect(0, 0, width, height);
	ctx.fillStyle = "black";
	makeCanvas();

	ctx.beginPath();
	ctx.arc(xAxis +(2*x/r)*scaleX, yAxis-(2*y/r)*scaleY, markerRadius, 0, 2 * Math.PI, false);
	if(status=="true"){
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

function checkForm(element){
	if(isNaN(parseFloat(element.xInput.value)) || isNaN(parseFloat(element.yInput.value))){
		message.textContent = failMessage;
		message.style.backgroundColor = failColor;
		return false;
	}
	x = Number(element.xInput.value);
	y = Number(element.yInput.value);
	r = Number(element.rInput.value);
	if(checkNumber(x) && checkNumber(y)){
		message.textContent = successMessage;
		message.style.backgroundColor = successColor;
	}else{
		message.textContent = failMessage;
		message.style.backgroundColor = failColor;
		return false;
	}
	var dataToServer = {
		x: x,
		y: y,
		user_r: r
	};
	fetch('/api/', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(dataToServer),
	})
	.then(response => response.json())
	.then(data => {
		const status=data.status;
		makePoint(x,y,r,status);
		var row = document.createElement('tr');
		row.innerHTML = `
		<td>${x}</td>
		<td>${y}</td>
		<td>${r}</td>
		<td>${status=="true" ? '<span style="color: #05da00">&#9745;</span>' : status=="false" ? '<span style="color: red">&#9746;</span>' : status}</td>
		<td>${new Date().toLocaleDateString()+" "+new Date().toLocaleTimeString()}</td>
		<td>${data.time + "ms"}</td>

		`;

		tbody.prepend(row);

		
		if(tbody){
			localStorage.setItem('tableData', tbody.innerHTML);
		}
        
	})
	.catch((error) => {
		console.error('Error:', error);
	});

	return false;
}

const element = document.querySelector(".js-choice");

const choices = new Choices(element, {
	searchEnabled: false,
	itemSelectText: "Выберите R"
});

var x, y, r, message;
const maxNum = 5;
const successMessage = "Данные валидны";
const failMessage = "Введенные данные не валидны";
const successColor = "green";
const failColor = "red";
message = document.getElementById("mainMessage");
const table = document.getElementById('resultTable');
const tbody = document.getElementById('megaTbodyEshkere');

window.onload = function() {
	
	var tableData = localStorage.getItem('tableData'); 
	if (tableData) {
		document.getElementById('megaTbodyEshkere').innerHTML = tableData;
	}
};
