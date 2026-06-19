import './App.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import LandingPage from './components/commonComponents/LandingPage'
import LoginPage from './components/commonComponents/LoginPage'
import SignupPage from './components/commonComponents/SignupPage'
import UserHomePage from './components/userComponents/UserHomePage'

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<LandingPage />} />
        <Route path='/signin' element={<LoginPage />} />
        <Route path='/signup' element={<SignupPage />} />
        <Route path='/home' element={<UserHomePage />} />
      </Routes>
    </Router>
  )
}

export default App
