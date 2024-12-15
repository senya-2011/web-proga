import { useSelector } from 'react-redux';
import { Navigate } from 'react-router-dom';
import { login } from "../../store/authSlice"; 
import { useDispatch } from "react-redux";  


const ProtectedRoute = ({ children }) => {
  const dispatch = useDispatch(); 
  const storedLogin = localStorage.getItem('userLogin');
  if(storedLogin){
    dispatch(login(storedLogin));  
  }
  const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);
  return isAuthenticated ? children : <Navigate to="/login" />;
};

export default ProtectedRoute;