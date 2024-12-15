import Form from "../Form/Form";
import CanvasPlot from "../Plot/Plot";


export default function Container(){

    return(
        <div class="mainPage">
            <div class="container">
                <Form/>
                <CanvasPlot/>
            </div>
        </div>
    );
}