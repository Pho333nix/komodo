import React, { useState } from 'react';
import {useSelector, useDispatch} from 'react-redux';
import { signUp, signUpUser } from './signUpSlice'
import { toggleHasAcc } from '../home/homeSlice';

export function SignUp(){
  const [userName, passWord] = useState({val: ' '});
  const [PId, email] = useState({val: ' '});
  const dispatch = useDispatch();

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
/*testing button
  const printV=()=>{
    console.log(userName.val +' '+ passWord.val);
  }*/
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
      dispatch(signUpUser())}
    }>
      Sign up!
    </button>
</div>);

}
