export function checkTrigger(){
    let newValue = parseInt(document.getElementById("updateTrigger").value);
    if (oldValue!==newValue){
        location.reload();
    }
}

let oldValue = parseInt(document.getElementById("updateTrigger").value);