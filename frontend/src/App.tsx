import './App.css'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import Home from './pages/Home'
import Offer from './pages/trailers/Offer'
import TrailerProfile from './pages/trailers/TrailerProfile'
import Contact from './pages/Contact'
import ReservationForm from './components/trailers/reservation/ReservationForm'
import Header from './components/constants/header/Header'
import TrailerForm from './components/addTrailer/formsteps/TrailerForm'
import { useAuth } from './hooks/use-auth'
import { AuthContext } from './context/auth-context'

function App() {
  const { user } = useAuth()

  return (
    <AuthContext.Provider value={{ user, setUser: () => {} }}>
      <div className="App">
        <Router>
          <Header />
          <Routes>
            <Route path="/" element={<Home />}></Route>
            <Route path="/aanbod" element={<Offer />}></Route>
            <Route path="/verhuren" element={<TrailerForm />}></Route>
            <Route path="/aanbod/:id" element={<TrailerProfile />}></Route>
            <Route path="/contact" element={<Contact />}></Route>
            <Route path="/reserveren/:id" element={<ReservationForm />} />
          </Routes>
        </Router>
      </div>
    </AuthContext.Provider>
  )
}

export default App
