import { useEffect} from "react";
import axios from "axios";
import { useSelector, useDispatch } from 'react-redux';
import { setPoints } from "../../store/pointSlice"; 

export default function Table(){
    const {points} = useSelector((state) => state.points);
    const {login} = useSelector((state) => state.auth);
    const dispatch = useDispatch();

    const fetchPoints = async () => {
        try {
            const response = await axios.get("/api/points",{
              params:  { login },
            });
            dispatch(setPoints(response.data));
        }catch (error){
            console.error("Ошибка при получении точек:", error);
        }
    };
    
    useEffect(() => {fetchPoints();}, []);

    return (
        <div id="resultTable">
          <table>
            <thead>
              <tr>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>Попал</th>
              </tr>
            </thead>
            <tbody>
              {points.map((point, index) => (
                <tr key={index}>
                  <td>{point.x.toFixed(2)}</td>
                  <td>{point.y.toFixed(2)}</td>
                  <td>{parseInt(point.r)}</td>
                  <td>{point.status ? "✔️" : "❌"}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      );
}