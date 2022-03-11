import React, { useEffect, useState } from 'react';
import {useSelector, useDispatch} from 'react-redux';
import { signUpUser, userSelector } from './../UserSlice';
import { useNavigate } from 'react-router-dom';
import { Formik, Field, Form, ErrorMessage, FormikProvider } from "formik";
import * as Yup from "yup";

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
      name: '',
      surname: '',
      pnr: '',
      email: '',
      password: '',
      username: '',
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
useEffect(()=>{
    if(status === 'success'){
      setErrorMsg('');
      navigate("/SignIn")
    }else if(status === 'error'){
      setErrorMsg(res.message)
    }
  },[status, errorMsg])
  //TODO: make sure button is only clickable if we filled all values
  const validationSchema = Yup.object().shape({
    username: Yup.string().required("This field is required!"),
    password: Yup.string().required("This field is required!"),
    name: Yup.string().required("This field is required!"),
    surname: Yup.string().required("This field is required!"),
      pnr: Yup.number().test("len", "the social security number needts to be 12 digits.",
                             (val)=> val && val.toString().length === 12)
              .required("This field is required!"),
    email: Yup.string().email("This is not a valid email.")
              .required("This field is required!"),
  });

 
/**
 * a function that will dispatch the action to sign-up the user.
 */
  const handleSubmit =(formValue)=>{
    //e.preventDefault();
    const { name,
      surname,
      pnr,
      email,
      password,
      username} = formValue;
    dispatch(signUpUser( { name,
      surname,
      pnr,
      email,
      password,
      username}))

  }

  return(
<div><p>Signup here</p>
 <Formik
          initialValues={user}
          validationSchema={validationSchema}
          onSubmit={handleSubmit}
        >
   <Form>
     <div>
       <label htmlFor="username" >
         Username:
       </label>
      <Field type='text' name='username'/>
      <ErrorMessage name="username"
                    className="alert alert-danger"/>
     </div>
     <div>
       <label htmlFor="password">
         password:
       </label>
       <Field name="password" type="password" />
       <ErrorMessage name="password" className="alert alert-danger" />
     </div>
     <div>
       <label>
         social security number:
       </label>
       <Field type='number'
              name="pnr"/>
       <ErrorMessage name="pnr" className="alert alert-danger" />
       <br/>
     </div>
     <div>
       <label htmlFor="email">Email: </label>
                  <Field name="email" type="email"  />
                  <ErrorMessage
                    name="email"
                    className="alert alert-danger"
                  />
        <br/>
     </div>
     <div>
       <label>
         name:
       </label>
       <Field type='text' name='name'/>
      <ErrorMessage name="name" className="alert alert-danger" />
     </div>
     <div>
       <label>
         surname:
       </label>
      <Field type='text' name='surname'/>
      <ErrorMessage name="surname"
                    className="alert alert-danger" />
      <br/>
     </div>
     <button className="button" type="submit" >
      Sign up!
    </button>
  </Form>
</Formik>
{errorMsg && (
  <p style={{color:'red'}} className="error"> {errorMsg} </p>
)}
</div>);
}
