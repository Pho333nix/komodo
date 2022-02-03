import React, { useEffect, useState } from 'react';
import { useDispatch} from 'react-redux';
import { signInUser } from '../UserSlice'
export function SignIn(){
  const [credentials, setCredentials] = useState({username: ' ', password:' '});
  const dispatch = useDispatch();

  const setUserName=(name)=>{
    setCredentials(state =>({...state, username: name}))
  }
  const setPassword=(passwd)=>{
    setCredentials(state =>({...state, password: passwd}))
  }

  useEffect(()=>{

  },[])

  const handleSubmit =()=>{
    dispatch(signInUser(credentials))
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
  <button onClick={()=>{handleSubmit()}}>Log in</button>
</div>);

}
