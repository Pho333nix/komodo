import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector} from 'react-redux';
import { signInUser, userSelector } from '../UserSlice'
import { useNavigate } from 'react-router-dom';
import { Formik, Field, Form, ErrorMessage } from "formik";
import * as Yup from "yup";

export function SignIn(){
  const [credentials, setCredentials] = useState({username: '', password:''});
  const [errorMsg, setErrorMsg]=useState('')

  const dispatch = useDispatch();
  const { status, res, isLoggedIn } = useSelector(userSelector)
  const navigate = useNavigate();

  const validationSchema = Yup.object().shape({
    username: Yup.string().required("This field is required!"),
    password: Yup.string().required("This field is required!"),
  });

  const setUserName=(name)=>{
    setCredentials(state =>({...state, username: name}))
  }
  const setPassword=(passwd)=>{
    setCredentials(state =>({...state, password: passwd}))
  }

  useEffect(()=>{
    if(isLoggedIn){
      navigate("/UserRecruit")
      setErrorMsg('')
    }else if(status === 'error'){
      setErrorMsg(res.message)
    }

  },[errorMsg, isLoggedIn, navigate, res, status])

  const handleSubmit =( formValue)=>{
    const   {username, password } = formValue;
    dispatch(signInUser( {username, password} ))

  }

  return(
    <Formik
      initialValues={credentials}
      validationSchema={validationSchema}
      onSubmit={handleSubmit}
    >
<Form>
    <div>
      <label htmlFor="username"> Username: </label>
      <Field name="username" type="text" />
      <ErrorMessage name="username" className="alert alert-danger"/>
    </div>
    <div>
    <label htmlFor="password">
      password:
    </label>
      <Field type='password'  name="password"/>
      <ErrorMessage name="password"/>
    </div>
  <div>
    <button type="submit">Log in</button>
  </div>
  </Form>
</Formik>
  );

}
