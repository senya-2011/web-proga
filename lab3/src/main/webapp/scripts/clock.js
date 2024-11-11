function updateClock() {
    let now = new Date();

    let hours = String(now.getHours()).padStart(2, '0');
    let minutes = String(now.getMinutes()).padStart(2, '0');
    let seconds = String(now.getSeconds()).padStart(2, '0');
    let day = String(now.getDate()).padStart(2, '0');
    let month = now.toLocaleString('ru', { month: 'long' });
    let year = now.getFullYear();

    const currentTime = `${hours}:${minutes}:${seconds}`;
    const currentDate = `${day} ${month} ${year}`;

    timeElement.textContent = currentTime;
    dateElement.textContent = currentDate;
    setTimeout(updateClock, timeOut);
}

const timeOut = 9000;
const timeElement = document.getElementById('time');
const dateElement = document.getElementById('date');

updateClock();