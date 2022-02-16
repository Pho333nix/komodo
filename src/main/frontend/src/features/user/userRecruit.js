import React from 'react'
import {useSelector, useDispatch} from 'react-redux';
import { signUpUser, userSelector } from './UserSlice'

export function UserRecruit(){

const state = useSelector(userSelector);

  return(<div><h1>Recruit profile page</h1>
         {Object.entries(state).map(([key, value])=>{
           <h2>key:value</h2>
         })}
         </div>)
}
