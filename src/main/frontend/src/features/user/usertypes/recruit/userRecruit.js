import React, { useEffect, useState } from 'react'
import {useSelector, useDispatch } from 'react-redux';
import { userSelector, logout, personSelector } from '../../UserSlice'
import { useNavigate } from 'react-router-dom';
export function UserRecruit(){

const { res, isLoggedIn } = useSelector(userSelector);
  const [currentUser, setCurrentUser] = useState({ currentUser: '' });
const navigate = useNavigate();
const dispatch = useDispatch();

  useEffect(()=>{

  if(!res || !isLoggedIn){
    navigate("/SignIn")
  }else{
  }
  },[navigate, res, isLoggedIn]);

  const handleLogout = () =>{
    navigate("/SignIn")
    dispatch(logout())
  }

  const renderUser = () =>{
    //setCurrentUser({currentUser: res.person})
    return(<div className="container">
             <p>Welcome to your personal page, please
               use the navigation bar on top of the page
               to do some of the following: change profile info, apply for
               a job or see your application status.
             </p>

           </div>)
  }
  return(<div>
           <h1>Recruit profile page</h1>
           { renderUser() }
           <button onClick={handleLogout}>logout</button>
         </div>)
}
