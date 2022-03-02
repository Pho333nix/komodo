import React, { useEffect, useState } from 'react';
import { useSelector} from 'react-redux';
import { Formik, Form, Field, FieldArray } from 'formik';
import { userSelector, personSelector } from '../../UserSlice'

export const PersonalInformation = () =>{
  const { isLoggedIn, res } = useSelector(userSelector);
  const [user, setUser] = useState({user:null});

  useEffect(()=>{
    if( res && isLoggedIn){
      setUser(res.person)
      console.log("person: " + JSON.stringify(res.person));
    }
  },[isLoggedIn, res, user, setUser])

  return(
  <Formik>
  <Form>
  {Object.keys(user).map((key, val) => {
    if(key !== 'roleid') return <div key={key}>{key + ': ' + user[key]}
    </div>
  })};
  </Form>
  </Formik>
  )
}
