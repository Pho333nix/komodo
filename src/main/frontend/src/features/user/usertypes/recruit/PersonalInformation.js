import React, { useEffect } from 'react';
import { useSelector} from 'react-redux';
import { Formik, Form, Field, FieldArray } from 'formik';
import { personSelector } from '../../UserSlice'

const PersonalInformation = () =>{
  const person = useSelector(personSelector);
  useEffect(()=>{
    if(person){
      console.log("person: " + JSON.stringify(person));
    }
  },[person])
  return(Object.keys(person).map((key, val) => {
    <div>${key}</div>

  }));
}
