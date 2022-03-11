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
    if(state.status === 'success'){
      setList(state.list)
      console.log(state.list)
    }else{
    //  navigate("/SignIn")
      console.log('failed, state = ' + state.status)
    }
  },[setList. list, dispatch]);
  if(list && state.status === "success"){
  return(
  <div>
    <h1>Application Form: </h1>
    <h2>Personal information:</h2>
    <PersonalInformation/>
    <h2>Competens profile: </h2>
  <Formik initialValues={{
    ticketsales : { experience: false, years: 0 },
    lotteries: { experince: false, years: 0 },
    rollerCosterOp: { experience: false, years: 0 },
    startDate: "",
    endDate: ""
  }} onSubmit={(values)=>{alert(JSON.stringify(values, null, 2))}}>
      <Form>
        <div>experiences</div>
        <div>
            <label>
    <Field type="checkbox" name="ticketsales[experience]" />
              {list[0]}
            </label>
          <label> &nbsp; years of experience
              <Field type="number" name="ticketsales[years]"/>
            </label>
    </div>
    <div>
            <label>
              <Field type="checkbox" name="lotteries[experience]"/>
               {list[1]}
              &nbsp; years of experience
              <Field type="number" name="lotteries[years]"/>
            </label>
    <div/>
    <div>
            <label>
              <Field type="checkbox" name="rollerCosterOp[experience]"  />
              {list[2]}
              &nbsp; years of experience
              <Field type="number" name="rollerCosterOp[years]" />
            </label>
    </div>
    </div>
        <label >start date: </label>
        <Field name="startDate" type="date"/>
      <div>
        <label> end date: </label>
        <Field name="endDate" type="date"/>
      </div>
       <button type="submit">Submit</button>
      </Form>
  </Formik>

      </div>
)
  }else{
    return <p>loading...</p>
  }
}
