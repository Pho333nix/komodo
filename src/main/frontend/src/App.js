import React from 'react';
import { Home } from './features/home/home'
import { Navbar } from './features/navbar/navBar'
import { SignIn } from './features/user/signIn/SignIn'
import { SignUp } from './features/user/signUp/signUp'
import { UserRecruit } from './features/user/userRecruit'
import './App.css';
import {BrowserRouter as Router, Routes, Switch, Route} from 'react-router-dom';
// @ts-check


/**
 * @component
 * functional component App, implements router functionality
 * through Router/Route/Switch components. Parent node to all
 * views/components
 */
function App() {

return(
  <Router>
    <Navbar/>
    <Routes>
    <Route exact path='/' element={<Home/>}/>
    <Route path='/SignUp' element={<SignUp/>}/>
    <Route path='/SignIn' element={<SignIn/>}/>
    <Route path='/UserRecruit' element={<UserRecruit/>}/>
  </Routes>
  </Router>
);
}
export default App;
