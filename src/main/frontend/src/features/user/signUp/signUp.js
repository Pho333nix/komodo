import React, { useEffect, useState } from 'react';
import {useSelector, useDispatch} from 'react-redux';
import { signUpUser, userSelector } from './../UserSlice';
import { useNavigate } from 'react-router-dom';

export function SignUp(){
  const [user, setUser] = useState(()=>{
    return {
      name: ' ',
      surname: ' ',
      pnr: ' ',
      email: ' ',
      password: ' ',
      role_id: 1,
      username: ' ',
    }
  });
  const [errorMsg, setErrorMsg] = useState('');
  const dispatch = useDispatch();
  const { status, res } = useSelector(userSelector);
  const navigate = useNavigate();

const setUserName=(uName)=>{
    setUser(prevState =>({
      ...prevState,
      username : uName
    }));
  }
  const setPassword=(passwd)=>{
     setUser(prevState =>({
      ...prevState,
      password: passwd
    }));
  }

  const setPID =(pid)=>{
   setUser(prevState =>({
     ...prevState,
     pnr: pid
   }));
  }
  const setMail=(mail)=>{
     setUser(prevState =>({
     ...prevState,
     email: mail
   }));
  }
  const setRole=(role)=>{
     setUser(prevState =>({
     ...prevState,
       role_id: parseInt(role)
     }));
  }
  const setName=(name)=>{
   setUser(prevState =>({
     ...prevState,
     name: name
   }));
  }
  const setSurName=(SName)=>{
     setUser(prevState =>({
     ...prevState,
     surname : SName
   }));
  }

  useEffect(()=>{
    if(status === 'success'){
      navigate("/UserRecruit")
    }else if(status === 'error'){
      setErrorMsg(res)
    }
  },[status, errorMsg])

  //TODO: make sure button is only clickable if we filled all values
  // TODO:https://www.npmjs.com/package/react-validation

  const handleSubmit =()=>{
   // e.preventDefault(); // stops refreshing of page
 dispatch(signUpUser(user))
  }

  return(
<div><p>Signup here</p>
  <form onSubmit={handleSubmit()}>
    <label>
      Username:
      <input type='text' name='username' value={user.userName}  onChange={(e)=>{setUserName(e.target.value)}}/>
     </label>
    <label>
      password:
      <input type='password' name='password' value={user.passWord}  onChange={(e)=>{setPassword(e.target.value)}}/>
    </label>
      <br/>
    <label>
        social security number:
      <input type='number' required={12} value={user.PID} onChange={(e)=>{setPID(e.target.value)}}/>
     </label>
    <label htmlFor='email'>
      email:
      <input type='email' value={user.email}  onChange={(e)=>{setMail(e.target.value)}}/>
      <br/>
    </label>
    <label>
      name:
      <input type='text'  value={user.name} onChange={(e)=>{setName(e.target.value)}}/>
     </label>
    <label>
      surname:
      <input type='text'  value={user.SName} onChange={(e)=>{setSurName(e.target.value)}}/>
    </label>
      <br/>
    <select name="role" value={user.role} onChange={(e)=>{setRole(e.target.value)}}>
      role:
      <option value="1">recruit</option>
      <option value="2">recruiter</option>
     </select>

  </form>
  <button>
      Sign up!
    </button>
{errorMsg && (
  <p style={{color:'red'}} className="error"> {errorMsg} </p>
)}
</div>);

}
