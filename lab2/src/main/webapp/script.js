 
function checkNumber(x, y, r){
	return ((x>=-4 && x<=4) && (y>=-3 && y<=3) && (r>=1 && r<=3));
}
function checkX(x){
	return (x>=-4 && x<=4);
}
function checkY(y){
	return (y>=-3 && y<=3);
}
function checkR(r){
	return (r>=1 && r<=3);
}

function submitForm(){
	let x = form.get('x');
	let y = form.get('y');
	let r = form.get('r');
	doFetch(x, y, r);
}

function doFetch(x, y, r){
	if (checkVal(x, y, r)){
		let request = {
			"x": x,
			"y": y,
			"r": r
		};
		fetch("ControllerServlet", {
			method: "POST",
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(request),
		})
			.then(response => response.json())
			.then(data => {
				makePoint(data.x,data.y,data.r,data.status);
				let row = document.createElement('tr');
				row.innerHTML = `
				<td>${data.x}</td>
				<td>${data.y}</td>
				<td>${data.r}</td>
				<td>${data.status=="true" ? '<span style="color: #05da00">&#9745;</span>' : data.status=="false" ? '<span style="color: red">&#9746;</span>' : data.status}</td>
				`;
				tbody.prepend(row);

			})
			.catch((error) => {
				console.error('Error:', error);
			});
	}
}

function doPost(){
	let x = (document.getElementById("xInput").value).replace(",", ".");
	let y = (document.getElementById("yInput").value).replace(",", ".");
	let r = (document.getElementById("rInput").value).replace(",", ".");
	doFetch(x, y, r);
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


function checkVal(x, y ,r){
	if(isNaN(parseFloat(x)) || isNaN(parseFloat(y)) || isNaN(parseFloat(r))){
		let xString = !(isNaN(parseFloat(x))) ? "" : ", введите x";
		let yString = !(isNaN(parseFloat(y))) ? "" : ", введите y";
		let rString = !(isNaN(parseFloat(r))) ? "" : ", введите r";
		message.textContent = failMessage + xString+yString+rString;
		message.style.backgroundColor = failColor;
		return false;
	}
	if(checkNumber(x, y, r)){
		message.textContent = successMessage;
		message.style.backgroundColor = successColor;
	}else{
		let xString = checkX(x) ? "" : ", |x|<=4";
		let yString = checkY(y) ? "" : ", |y|<=3";
		let rString = checkR(r) ? "" : ", 1<=r<=3";
		message.textContent = failMessage + xString+yString+rString;
		message.style.backgroundColor = failColor;
		return false;
	}

	return true;
}

const successMessage = "Данные валидны";
const failMessage = "Введенные данные не валидны";
const successColor = "green";
const failColor = "red";
const message = document.getElementById("mainMessage");
const tbody = document.getElementById('megaTbodyEshkere');
const form = document.getElementById("mainForm");

