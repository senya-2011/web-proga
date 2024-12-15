import "./App.css";
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import MainPage from "./components/MainPage/MainPage";
import LoginPage from "./components/LoginPage/LoginPage";
import ProtectedRoute from "./components/ProtectedRouter/ProtectedRouter";

export default function App() {
  return (
    <>
     <Router>
      <Routes>
        <Route
            path="/"
            element={
              <ProtectedRoute>
                <>
                  <MainPage />
                </>
              </ProtectedRoute>
            }
          />

        <Route path="/login" element={
            <>
              <LoginPage />
            </>
        } />

        <Route path="/test" element={
          <div>
            <h1>test</h1>
          </div>
        } />
        
      </Routes>
        
     </Router>
    </> 
  );
}
