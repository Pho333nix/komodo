import React, { useEffect, useState } from 'react';
import {useSelector, useDispatch} from 'react-redux';
import { signUp, signUpUser } from './signUpSlice'
import { toggleHasAcc } from '../home/homeSlice';
import { useNavigate } from 'react-router-dom';

export function SignUp(){
  const [userName, passWord] = useState({val: ' '});
  const [PId, email] = useState({val: ' '});

  const dispatch = useDispatch();
  const state = useSelector(state => state.home);
  const isAuth = useSelector(state => state.home.hasAcc)
  const navigate = useNavigate();

  console.log('state: ', state)
const setUserName=(name)=>{
    userName.val= name;
    //console.log(userName);
  }
  const setPassword=(passwd)=>{
    passWord.val = passwd;
  }

  const setPID =(pid)=>{
    PId.val= pid;
  }
  const setMail=(mail)=>{
    email.val = mail;
    console.log(email);
  }
  useEffect(()=>{
    if(isAuth){
      navigate("/")
    }
  },[isAuth])
  const post=async()=>{
    try{
      await dispatch(signUpUser({
    "name": " test",
    "id": "1",
    "surname": "Wilkinson",
    "pnr": "1231312323123213",
    "email": "xaxaxidasdasdaa@sdad.se",
    "password": "LiZ98qvL8Lw",
    "role_id": "1" ,
    "username":"comeOO00OOasdanMaaan"
  }  )).unwrap()
    }catch(err){
      console.log('post err: ', err)
    }
  }
  const handleSubmit =()=>{
   // e.preventDefault(); // stops refreshing of page
    dispatch(toggleHasAcc());
  }

  return(
<div><p>Signup here</p>
  <form>
    <label>
      Username:
      <input type='text'  onChange={(e)=>{setUserName(e.target.value)}}/>
     </label>
    <label>
      password:
      <input type='text'  onChange={(e)=>{setPassword(e.target.value)}}/>
    </label>
      <br/>
    <label>
        social security number:
      <input type='number'  onChange={(e)=>{setPID(e.target.value)}}/>
     </label>
    <label>
      email:
      <input type='text'  onChange={(e)=>{setMail(e.target.value)}}/>
    </label>
  </form>
    <button onClick={()=>{
      dispatch(signUp({name: userName.val, passwd:passWord.val,
                       pid: PId.val, email: email.val}))
      post()}
    }>
      Sign up!
    </button>
</div>);

}
