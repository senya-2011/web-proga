import {makeAjaxPoint, updateCanvas} from './canvas.js';

const successColor = "green";
const failColor = "red";
export { successColor };
export { failColor };
const triggerInput = document.getElementById('trigger');
const rInput = document.getElementById('rValue');

const observer = new MutationObserver((mutations) => {
    mutations.forEach((mutation) => {
        if (mutation.type === 'attributes' && mutation.attributeName === 'value') {
            makeAjaxPoint();
        }
    });
});

const rObserver = new MutationObserver((mutations) => {
    mutations.forEach((mutation) => {
        if (mutation.type === 'attributes' && mutation.attributeName === 'value') {
            updateCanvas(rInput.value);
        }
    });
});

rObserver.observe(rInput, { attributes: true });
observer.observe(triggerInput, { attributes: true });