import React from 'react';
//import { Counter } from './features/counter/Counter';
import { Home } from './features/home/home'
import { Navbar } from './features/navbar/navBar'
import { SignIn } from './features/user/signIn/SignIn'
import { SignUp } from './features/user/signUp/signUp'
import './App.css';
import {BrowserRouter as Router, Routes, Switch, Route} from 'react-router-dom';

function App() {

return(
  <Router>
    <Navbar/>
    <Routes>
    <Route exact path='/' element={<Home/>}/>
    <Route path='/SignUp' element={<SignUp/>}/>
    <Route path='/SignIn' element={<SignIn/>}/>
  </Routes>
  </Router>
);
}
export default App;
