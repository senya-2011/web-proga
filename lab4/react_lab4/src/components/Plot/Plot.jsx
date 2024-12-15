import { useRef, useEffect } from 'react';
import axios from 'axios';
import {useSelector, useDispatch} from 'react-redux';
import { setPoints } from '../../store/pointSlice';
import PropTypes from 'prop-types'; 

const CanvasPlot = ({onPointClick }) => {
    const {r } = useSelector((state) => state.coordinates); 
    const { login} = useSelector((state) => state.auth);
    const {points } = useSelector((state) => state.points); 
    const dispatch = useDispatch();
    const canvasRef = useRef(null);

    const markerRadius = 3;
    const pointStyle = "#003300";
    const netColor = "#c7c7c7";
    const axesColor = "#000000";
    const graphColor = "#0047ab";
    const width = 300;
    const height = 300;
    const targetValue = 4;
    const scaleX = Math.floor(width / (2 * targetValue));
    const scaleY = Math.floor(height / (2 * targetValue));
    const xAxis = width / 2;
    const yAxis = height / 2;

    const drawCanvas = (ctx, r) => {
        r = 2*r;
        ctx.clearRect(0, 0, width, height);
        ctx.beginPath();
        ctx.strokeStyle = netColor;
        ctx.font = "10px Arial";
        ctx.textAlign = "left";
        ctx.textBaseline = "top";
        ctx.fillStyle = netColor;

        for (let i = 0; i <= width; i += scaleX) {
        ctx.moveTo(i, 0);
        ctx.lineTo(i, height);
        ctx.fillText(((i - xAxis) / scaleX).toFixed(1), i, yAxis + 2);
        }

        for (let i = 0; i <= height; i += scaleY) {
        ctx.moveTo(0, i);
        ctx.lineTo(width, i);
        ctx.fillText(((yAxis - i) / scaleY).toFixed(1), xAxis + 2, i);
        }

        ctx.stroke();
        ctx.closePath();

        ctx.beginPath();
        ctx.strokeStyle = axesColor;
        ctx.moveTo(xAxis, 0);
        ctx.lineTo(xAxis, height);
        ctx.fillText("y", xAxis - 10, 10);

        ctx.moveTo(0, yAxis);
        ctx.lineTo(width, yAxis);
        ctx.fillText("x", width - 10, yAxis + 10);
        ctx.stroke();
        ctx.closePath();

        drawGraphics(ctx, r);
    };

    const drawGraphics = (ctx, r) => {
        ctx.globalAlpha = 0.3;
        ctx.fillStyle = graphColor;

        // Square
        ctx.fillRect(xAxis, yAxis, scaleX * r / 2, scaleY * r / 2);

        // Triangle
        ctx.beginPath();
        ctx.moveTo(xAxis, yAxis);
        ctx.lineTo(xAxis, yAxis - scaleY * r / 2);
        ctx.lineTo(xAxis + scaleX * r / 2, yAxis);
        ctx.closePath();
        ctx.fill();

        // Circle
        ctx.beginPath();
        ctx.arc(xAxis, yAxis, scaleX * r / 2, Math.PI / 2, Math.PI);
        ctx.lineTo(xAxis, yAxis);
        ctx.closePath();
        ctx.fill();

        ctx.globalAlpha = 1;
    };

    const drawPoint = (ctx, x, y, r, status) => {
        drawCanvas(ctx, r);
        ctx.beginPath();
        ctx.arc(xAxis + x * scaleX, yAxis - y * scaleY, markerRadius, 0, 2 * Math.PI);
        ctx.fillStyle = status ? "#008000" : "#FF0000";
        ctx.fill();
        ctx.strokeStyle = pointStyle;
        ctx.stroke();
        ctx.closePath();
    };

    const handlePointClick = async (x, y) => {
        const formData = {
            x,
            y,
            r,
            login,
        };
        try {
            const response = await axios.post('/api/points', formData); 
            if (Array.isArray(response.data)) {
                dispatch(setPoints(response.data));
            } else {
                console.error("Ответ от API не является массивом:", response.data);
            }
        } catch (error) {
            console.error('Ошибка при отправке данных:', error);
        }
    };

    useEffect(() => {
        const canvas = canvasRef.current;
        const ctx = canvas.getContext('2d');
        if (points.length > 0){
            const firstPoint = points[0];
            drawPoint(ctx, firstPoint.x, firstPoint.y, r, firstPoint.status)
        }else{
            drawCanvas(ctx, r);
        }
        const handleClick = (event) => {
            const rect = canvas.getBoundingClientRect();
            const x = (event.clientX - rect.left - xAxis) / scaleX;
            const y = -(event.clientY - rect.top - yAxis) / scaleY;
            handlePointClick(x.toFixed(3), y.toFixed(3)); 
        };

        canvas.addEventListener('click', handleClick);
        return () => canvas.removeEventListener('click', handleClick);
    }, [r, points ,onPointClick]);

    return (
        <div id="canvasPlot">
            <canvas ref={canvasRef} width={width} height={height}/>
        </div>
        );
};

CanvasPlot.propTypes = {
    onPointClick: PropTypes.func.isRequired, 
};

export default CanvasPlot;