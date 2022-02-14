import React from 'react';
import { Link, NavLink } from 'react-router-dom'
export function Navbar (){

  return(
    <nav>
      <div className='containter'>
        <ul>
          <li><NavLink to='/'>Home</NavLink></li>
          <li><NavLink to='/SignUp'>Sign up</NavLink></li>
          <li><NavLink to='/SignIn'>Sign in</NavLink></li>
        </ul>
      </div>
    </nav>
  );
}

