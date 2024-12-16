import { useSelector, useDispatch } from 'react-redux';
import { Navigate } from 'react-router-dom';
import axios from 'axios';
import PropTypes from 'prop-types';
import { login } from '../../store/authSlice';
import { useEffect, useState } from 'react';

const ProtectedRoute = ({ children }) => {
  const dispatch = useDispatch();
  const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const authenticate = async () => {
      try {
        const storedLogin = localStorage.getItem('userLogin');
        const storedPassword = localStorage.getItem('password');

        if (storedLogin && storedPassword) {
          const response = await axios.post('/api/auth/login', {
            login: storedLogin,
            password: storedPassword,
          });

          if (response.status === 200) {
            dispatch(login(storedLogin));
          }
        }
      } catch (error) {
        console.error('Ошибка авторизации:', error);
      } finally {
        setLoading(false); 
      }
    };

    authenticate();
  }, [dispatch]);

  if (loading) {
    return <div>Загрузка...</div>; 
  }

  return isAuthenticated ? children : <Navigate to="/login" />;
};

ProtectedRoute.propTypes = {
  children: PropTypes.node.isRequired,
};

export default ProtectedRoute;
