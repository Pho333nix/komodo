import React, { useState } from 'react';
import {useSelector, useDispatch} from 'react-redux';
import { signUp } from './signUpSlice'

export function SignUp(){
  const [userName, passWord] = useState({val: ' '});
  const dispatch = useDispatch();

  const setUserName=(name)=>{
    userName.val= name;
    //console.log(userName);
  }
  const setPassword=(passwd)=>{
    passWord.val = passwd;
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
  </form>
  <button onClick={()=>dispatch(signUp({name: userName.val, passwd:passWord.val}))}>submit</button>
</div>);

}
