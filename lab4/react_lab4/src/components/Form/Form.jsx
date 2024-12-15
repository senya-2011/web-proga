import React, { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { setX, setY, setR } from "../../store/coordinateSlice";
import { setPoints } from "../../store/pointSlice";
import axios from "axios";




export default function Form(){
    const { x, y, r } = useSelector((state) => state.coordinates);
    const { login} = useSelector((state) => state.auth);

    const dispatch = useDispatch();

    const handleSubmit = async (e) => {
        e.preventDefault();
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

    return(
        <div class="item left">
                <div>
                    <form onSubmit={handleSubmit}>
                        <div>
                            <label>
                            Выберите X:
                            <input
                                type="number"
                                value={x}
                                onChange={(e) => dispatch(setX(Number(e.target.value)))}
                                min="-3"
                                max="3"
                            />
                            </label>
                        </div>


                        <div>
                            <label>
                            Выберите Y:
                            <input type="number" value={y}
                                onChange={(e) =>  dispatch(setY(Number(e.target.value)))}
                                min="-3"
                                max="3"
                            />
                            <input
                                type="range"
                                value={y}
                                onChange={(e) => dispatch(setY(Number(e.target.value)))}
                                min="-3"
                                max="3"
                            />
                            </label>
                        </div>

                        <div>
                            <label>
                            Выберите R:
                            <select
                                value={r}
                                onChange={(e) =>  dispatch(setR(Number(e.target.value)))}
                            >   
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                            </select>
                            </label>
                        </div>

                        <button type="submit">
                            Отправить
                        </button>
                    </form>
                </div>
        </div>
    );
}