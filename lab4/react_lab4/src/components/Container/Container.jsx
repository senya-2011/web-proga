import Form from "../Form/Form";
import CanvasPlot from "../Plot/Plot";


export default function Container(){

    return(
        <div className="mainPage">
            <div className="container">
                <Form/>
                <CanvasPlot/>
            </div>
        </div>
    );
}