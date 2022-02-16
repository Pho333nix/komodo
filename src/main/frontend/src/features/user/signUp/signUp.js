import React, { useEffect, useState } from 'react';
import {useSelector, useDispatch} from 'react-redux';
import { signUpUser, userSelector } from './../UserSlice';
import { useNavigate } from 'react-router-dom';
import Form from "react-validation/build/form"
import Input from "react-validation/build/input"
import Button from "react-validation/build/button"
import CheckButton from "react-validation/build/button"
import { isEmail } from "validator";

//@ts-check

/**
 * @component
 * functional component that renders the signup page.
 * uses the state-hook to build the credentials needed
 * for the user to sign-up. the credentials will be
 * an object that will later be sent as a json object
 * to the application.
 * */
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

  /**
   * @function required
   * alerts user that a field is required
   * */
const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};
  /**
   * @function emailValidate
   * alerts user if the email provided is
   * incalid.
   * @param { value } is the user provided email
   * */
const emailValidate = value => {
  if (!isEmail(value)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid email.
      </div>
    );
  }
};

/**
 * function that will add the name to the state-hook object.
 * @param { uName } the function the user supplies */
const setUserName=(uName)=>{
    setUser(prevState =>({
      ...prevState,
      username : uName
    }));
  }
/**
 * function that will add the password to the state-hook object.
 * @param { passwd } the function the user supplies
 * */
  
  const setPassword=(passwd)=>{
     setUser(prevState =>({
      ...prevState,
      password: passwd
    }));
  }
/**
 * function that will add the password to the state-hook object.
 * @param { passwd } the function the user supplies
 * */
  const setPID =(pid)=>{
   setUser(prevState =>({
     ...prevState,
     pnr: pid
   }));
  }
/**
 * function that will add the email to the state-hook object.
 * @param { mail } the function the user supplies
 * */
  const setMail=(mail)=>{
     setUser(prevState =>({
     ...prevState,
     email: mail
   }));
  }

  /**
 * function that will add the role (recruiter or recruit) to the state-hook object.
 * @param { role } the function the user supplies
 * */
  const setRole=(role)=>{
     setUser(prevState =>({
     ...prevState,
       role_id: parseInt(role)
     }));
  }

 /**
  * function that will add the name to the state-hook object.
  * @param { name } the function the user supplies
 */
  const setName=(name)=>{
   setUser(prevState =>({
     ...prevState,
     name: name
   }));
  }
  /**
  * function that will add the surname to the state-hook object.
  * @param { SName } the function the user supplies
 */
  const setSurName=(SName)=>{
     setUser(prevState =>({
     ...prevState,
     surname : SName
   }));
  }
/**
 * useEffect hook, replaces componentWillUpdate and ComponentWillMount
 * in the dependency array we have status and errorMsg, as soon as one
 * changes the view will re-render.
 */
  useEffect(()=>{
    if(status === 'success'){
      navigate("/UserRecruit")
    }else if(status === 'error'){
      setErrorMsg(res)
    }
  },[status, errorMsg])

  //TODO: make sure button is only clickable if we filled all values
  // TODO:https://www.npmjs.com/package/react-validation
/**
 * a function that will dispatch the action to sign-up the user.
 */
  const handleSubmit =()=>{
 dispatch(signUpUser(user))
  }

  return(
<div><p>Signup here</p>
  <Form onSubmit={handleSubmit}>
    <label>
      Username:
      <Input type='text' name='username'
             value={user.userName}
             onChange={(e)=>{setUserName(e.target.value)}}
            validations={[required]}/>
     </label>
    <label>
      password:
      <Input type='password'
             name='password'
             value={user.passWord}
             onChange={(e)=>{setPassword(e.target.value)}}
             validations={[required]}/>
    </label>
      <br/>
    <label>
        social security number:
      <Input type='number'
             required={12}
             value={user.PID}
             onChange={(e)=>{setPID(e.target.value)}}
             validations={[required]}/>
     </label>
    <label htmlFor='email'>
      email:
      <Input type='email'
             value={user.email}
             onChange={(e)=>{setMail(e.target.value)}}
             validations={[required, emailValidate]}/>
    </label>
      <br/>
    <label>
      name:
      <Input type='text'
             value={user.name}
             onChange={(e)=>{setName(e.target.value)}}
            validations={[required]}/>
     </label>
    <label>
      surname:
      <Input type='text'
             value={user.SName}
             onChange={(e)=>{setSurName(e.target.value)}}
            validations={[required]}/>
    </label>
      <br/>
    <select name="role" value={user.role} onChange={(e)=>{setRole(e.target.value)}}>
      role:
      <option value="1">recruiter</option>
      <option value="2">recruit</option>
     </select>
  <Button className="button"
          >
      Sign up!
    </Button>
  </Form>

{errorMsg && (
  <p style={{color:'red'}} className="error"> {errorMsg} </p>
)}
</div>);

}
