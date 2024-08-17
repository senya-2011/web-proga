
function checkNumber(a){
	return !(a>=maxNum || a<=-maxNum);
}	

function checkForm(element){
	if(isNaN(parseFloat(element.xInput.value)) || isNaN(parseFloat(element.yInput.value))){
		message.textContent = failMessage;
		message.style.backgroundColor = failColor;
		return false;
	}
	x = Number(element.xInput.value);
	y = Number(element.yInput.value);
	r = Number(element.radius.value);
	if(checkNumber(x) && checkNumber(y)){
		message.textContent = successMessage;
		message.style.backgroundColor = successColor;
		makePoint(x, y, r);
	}else{
		message.textContent = failMessage;
		message.style.backgroundColor = failColor;
		return false;
	}

	const coords = "x=" + encodeURIComponent(x) + "&y=" + encodeURIComponent(y) + "&r=" + encodeURIComponent(r) +
      "&timezone=" + encodeURIComponent(Intl.DateTimeFormat().resolvedOptions().timeZone);  

    // Пример AJAX-запроса на PHP-скрипт
    fetch('php/script.php?' + coords, {
        method: 'GET',
    })
    .then(response => response.text())
    .then(data => {
        console.log('Ответ от сервера:', data);
        localStorage.setItem("session", data);
        document.getElementById("resultTable").innerHTML = data;
    })
    .catch(error => console.error('Ошибка:', error));


	return false;
}

var x, y, r, message;
const maxNum = 5;
const successMessage = "Данные валидны";
const failMessage = "Введенные данные не валидны";
const successColor = "green";
const failColor = "red";
message = document.getElementById("mainMessage");
document.getElementById("resultTable").innerHTML = localStorage.getItem("session");