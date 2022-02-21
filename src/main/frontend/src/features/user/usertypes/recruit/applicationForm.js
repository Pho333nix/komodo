import React from 'react';
import { Formik, Form, Field, FieldArray } from 'formik';

export const ApplicationForm = () =>(

  <div>
    <h1>Application Form: </h1>
    <h2>Personal information:</h2>
    <h4>Get it from person object</h4>
    <h2>Competens profile: </h2>
    <Formik initialValues={{ competenceProfile: [''], startDate: '', endDate: '' }}
          onSubmit={ values => alert(JSON.stringify(values))}>
     {({ values })=>(
      <Form>
        <FieldArray
        name="competenceProfile"
        render={arrayHelpers =>(
          <div>{values.competenceProfile && values.competenceProfile.length > 0 ?(
            values.competenceProfile.map((competence, index)=>(
              <div key={index}>
                <Field name={`competenceProfile.${index}`}/>
              <button type="button"
                      onClick={()=> arrayHelpers.remove(index)}> remove skill </button>
                <br/>
              <button type="button"
                      onClick={()=> arrayHelpers.insert(index, '')}> Add another skill </button>
              </div>
            ))
          ) : (
            <button type="button"
                    onClick={()=> arrayHelpers.push('')}>Add competency</button>
          )}
          </div>
        )}/>
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
    )}
  </Formik>

</div>

)

/*
 *   <Formik
      initialValues={{startDate:'', endDate:'', worHours:[], }}
      onSubmit={(values, actions)=>{console.log(values)}}>

  </Formik>
 *
 */
