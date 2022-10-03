import './App.css'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import Home from './pages/Home';
import Offer from './pages/trailers/Offer';
import TrailerProfile from './pages/trailers/TrailerProfile';
import Contact from './pages/Contact';
import ReservationForm from './components/trailers/reservation/ReservationForm';
import Header from './components/constants/header/Header';
import { useState } from 'react';
import Login from './components/constants/login/Login';

function App() {
  const [showLogin, setShowLogin] = useState(false)

  return (
    <div className="App">
      {showLogin ? <Login setShowLogin={setShowLogin} /> : null}
      <Router>
        <Header setShowLogin={setShowLogin} />
        <Routes>
          <Route path="/" element={<Home />}></Route>
          <Route path="/aanbod" element={<Offer />}></Route>
          <Route path="/aanbod/:id" element={<TrailerProfile />}></Route>
          <Route path="/contact" element={<Contact />}></Route>
          <Route path="/reserveren/:id" element={<ReservationForm />}></Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
