import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { Formik, Form, Field, FieldArray } from 'formik';
import { stateSelector } from './recruitSlice'
import { PersonalInformation } from './PersonalInformation'
import { getCompetenceList } from './recruitSlice';
export const ApplicationForm = () =>{

const [approved, setApproved] = useState(false);
const [list, setList] = useState([]);
const dispatch = useDispatch()
  const navigate = useNavigate()
const state = useSelector(stateSelector);

  useEffect(()=>{
    dispatch(getCompetenceList())
    if(state.status == 'success'){
      setList(state.list)

    }else{
    //  navigate("/SignIn")
    }
  });
  return(
  <div>
    <h1>Application Form: </h1>
    <h2>Personal information:</h2>
    <h4>Get it from person object</h4>
    <PersonalInformation/>
    <h2>Competens profile: </h2>

    <Formik>
      <Form>
        <div>
        <label >start date: </label>
        <Field name="startDate" type="date"/>
      </div>
      <div>
        <label> end date: </label>
        <Field name="endDate" type="date"/>
      </div>
        <div>
          <button type="submit">Submit</button>
        </div>
    </Form>
    </Formik>
      </div>
)
}



