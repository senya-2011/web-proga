import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useDispatch } from "react-redux";  
import { login } from "../../store/authSlice"; 

export default function LoginPage() {
  const [loginValue, setLogin] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const dispatch = useDispatch(); 
  

  async function hashString(str) {
    const encoder = new TextEncoder();
    const data = encoder.encode(str);
    const hashBuffer = await crypto.subtle.digest('SHA-256', data);
    const hashArray = Array.from(new Uint8Array(hashBuffer));
    const hashHex = hashArray.map(byte => byte.toString(16).padStart(2, '0')).join('');
  
    return hashHex;
  }

  const handleLogin = async (e) => {
    e.preventDefault();

    e.preventDefault();

  if (loginValue.trim().length < 4) {
    setError("Логин должен содержать хотя бы 4 символа");
    return; 
  }

  if (password.trim().length < 4) {
    setError("Пароль должен содержать хотя бы 4 символа");
    return; 
  }

  setError(null);

    try {
        const hashPassword = await hashString(password);
        const response = await axios.post("/api/auth/login", { login: loginValue, password:  hashPassword});
        if (response.status===200) {
            dispatch(login(loginValue));  
            localStorage.setItem('userLogin', loginValue);
            localStorage.setItem('password', hashPassword);
            navigate("/");
        } else {
            setError("Ошибка авторизации. Проверьте логин и пароль.");
        }
    } catch {
      setError("Ошибка запроса.");
    }
  };

  const handleLoginChange = (e) => {
    const valueWithoutSpaces = e.target.value.replace(/\s+/g, '');
    setLogin(valueWithoutSpaces);
  };

  const handlePasswordChange = (e) => {
    const valueWithoutSpaces = e.target.value.replace(/\s+/g, '');
    setPassword(valueWithoutSpaces);
  };

  return (
    <>
      <div style={{ margin: "0 auto", width: "300px" }}>
        <h1  className="loginPage">Авторизация</h1>
        <form onSubmit={handleLogin}>
          <div>
            <label className="loginPage" htmlFor="login">Логин:&nbsp;&nbsp;&nbsp;&nbsp;</label>
            <input
              type="text"
              id="login"
              value={loginValue}
              onChange={handleLoginChange}
              required
            />
          </div>
          <br/>
          <div>
            <label  className="loginPage" htmlFor="password">Пароль:&nbsp;</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={handlePasswordChange}
              required
            />
          </div>
          <br/>
          <button id="mainButton" type="submit">Войти</button>
        </form>
        {error && <p style={{ color: "red" }}>{error}</p>}
      </div>
    </>
  );
}
