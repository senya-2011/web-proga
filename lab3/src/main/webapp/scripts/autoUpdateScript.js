export function checkTrigger(){
    let newValue = parseInt(document.getElementById("updateTrigger").value);
    if (oldValue!==newValue){
        location.reload();
    }
}
export function checkPageTrigger(){
    let newValue = parseInt(document.getElementById("otherPageTrigger").value);
    if (oldPageValue !==newValue){
        oldPageValue=newValue;
        PrimeFaces.ajax.Request.handle({
            source: 'otherPageTrigger',
            process: '@this',
            update: 'resultTable',
        });
    }
}

export function updatePageValue(){
    oldPageValue = parseInt(document.getElementById("otherPageTrigger").value);
}

let oldValue = parseInt(document.getElementById("updateTrigger").value);
let oldPageValue = parseInt(document.getElementById("otherPageTrigger").value);