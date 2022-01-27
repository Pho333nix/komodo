import React from 'react';
import logo from './logo.svg';
import { Counter } from './features/counter/Counter';
import { Home } from './features/home/home'
import './App.css';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

function App() {

return(
  <Router>
    <Home/>
  </Router>
);
}
export default App;
