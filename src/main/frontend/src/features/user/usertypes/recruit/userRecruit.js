import React, { useEffect, useState } from 'react'
import {useSelector, useDispatch } from 'react-redux';
import { userSelector, logout } from '../../UserSlice'
import { useNavigate } from 'react-router-dom';
export function UserRecruit(){

const { res, isLoggedIn } = useSelector(userSelector);
const [currentUser, setCurrentUser] = useState({ currentUser: null });
const navigate = useNavigate();
const dispatch = useDispatch();

  useEffect(()=>{

  if(!res || !isLoggedIn){
    navigate("/SignIn")
  }else{
    setCurrentUser(res.person);
  }
  },[navigate, res, isLoggedIn]);

  const handleLogout = () =>{
    navigate("/SignIn")
    dispatch(logout())
  }

  const renderRecruit = () =>{
    return(<div className="container">
             <p>Welcome to your personal page {currentUser.name} please
               use the navigation bar on top of the page
               to do some of the following: change profile info, apply for
               a job or see your application status.
             </p>

           </div>)
  }

  const renderRecruiter = () =>{
     return(<div className="container">
             <p>Welcome to your personal page {currentUser.name} please
               use the navigation bar on top of the page
               to do some of the following: change profile info, go through applications
               and more.
             </p>
           </div>)
  }
  return(<div>
           <h1>Recruit profile page</h1>
           {currentUser.roleid == 1 ?  renderRecruiter() : renderRecruit() }
           <button onClick={handleLogout}>logout</button>
         </div>)
}
