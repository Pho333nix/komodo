import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector} from 'react-redux';
import { signInUser, userSelector } from '../UserSlice'
import { useNavigate } from 'react-router-dom';

export function SignIn(){
  const [credentials, setCredentials] = useState({username: ' ', password:' '});
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [errorMsg, setErrorMsg]=useState('')

  const dispatch = useDispatch();
  const { status, res } = useSelector(userSelector)
  const navigate = useNavigate();
  const setUserName=(name)=>{
    setCredentials(state =>({...state, username: name}))
  }
  const setPassword=(passwd)=>{
    setCredentials(state =>({...state, password: passwd}))
  }

  useEffect(()=>{
    if(status === 'success'){
      setIsLoggedIn(true);
      navigate("/UserRecruit")
      console.log('navigating: ', res);
    }else if(status === 'error'){
      setErrorMsg(res)
      setIsLoggedIn(false);
      console.log(res)
    }

  },[errorMsg, isLoggedIn, navigate, res, status])

  const handleSubmit =()=>{
    dispatch(signInUser(credentials))
  }

  return(
<div>
  <form onSubmit={handleSubmit}>
    <label>
      Username:
      <input type='text'  onChange={(e)=>{setUserName(e.target.value)}}/>
     </label>
    <label>
      password:
      <input type='text'  onChange={(e)=>{setPassword(e.target.value)}}/>
    </label>
  </form>
  <button>Log in</button>
</div>);

}
