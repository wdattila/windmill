import './App.css';
import Navbar from './Components/Navbar';
import AuthProvider from './Context/AuthProvider';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import RequiredAuth from './Context/RequiredAuth';
import LoginForm from './Components/LoginForm';

function App() {
  return (
    <div className="App">
      <AuthProvider>
        <BrowserRouter>
          <Navbar />
          <Routes>
            <Route path='/' element={<RequiredAuth/>}>
              
            </Route>
            <Route path='/login' element={<LoginForm />} />
          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </div>
  );
}

export default App;
