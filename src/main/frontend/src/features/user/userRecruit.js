import React, { useEffect } from 'react'
import {useSelector, useDispatch} from 'react-redux';
import { userSelector, logout } from './UserSlice'
import { useNavigate } from 'react-router-dom';
export function UserRecruit(){

  const { res: user } = useSelector(userSelector);
const navigate = useNavigate();
const dispatch = useDispatch();

  useEffect(()=>{
  if(!user){
    navigate("/SignIn")
  }
  },[user]);

  const handleLogout = () =>{
    dispatch(logout())
  }
  return(<div>
           <h1>Recruit profile page</h1>
           <button onClick={handleLogout}>logout</button>
         </div>)
}
