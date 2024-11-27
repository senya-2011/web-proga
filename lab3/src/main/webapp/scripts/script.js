import {makeAjaxPoint, updateCanvas} from './canvas.js';
import { updatePageValue } from './autoUpdateScript.js';

const successColor = "green";
const failColor = "red";
export { successColor };
export { failColor };

export function updateTable(){
    PrimeFaces.ajax.Request.handle({
        source: 'mainButton',
        process: '@this',
        update: 'resultTable r_error x_error y_error xValue yValue rValue statusValue trigger',
        oncomplete: function(){
            updatePageValue();
            makeAjaxPoint();
        }
    });
}

export function onRChanged(){
    PrimeFaces.ajax.Request.handle({
        source: 'r',
        process: '@this',
        update: 'rValue rNoHide',
        oncomplete: function() {
            update();
        }
    });
}
function update(){
    updateCanvas(document.getElementById('rValue').value);
}
