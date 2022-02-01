import React, { useState } from 'react';
import {useSelector, useDispatch} from 'react-redux';
import { signIn } from './signInSlice'

export function SignIn(){
  const [userName, passWord] = useState({val: ' '});
  const dispatch = useDispatch();

  const setUserName=(name)=>{
    userName.val= name;
    //console.log(userName);
  }
  const setPassword=(passwd)=>{
    passWord.val = passwd;
  }

  return(
<div>
  <form>
    <label>
      Username:
      <input type='text'  onChange={(e)=>{setUserName(e.target.value)}}/>
     </label>
    <label>
      password:
      <input type='text'  onChange={(e)=>{setPassword(e.target.value)}}/>
    </label>
  </form>
  <button onClick={()=>{dispatch(signIn({name: userName.val, passwd:passWord.val}))}}>Log in</button>
</div>);

}
