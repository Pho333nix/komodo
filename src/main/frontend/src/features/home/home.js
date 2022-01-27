import React from 'react';
import {useState} from 'react';
import {SignIn} from '../signIn/SignIn';
import {SignUp} from '../signUp/signUp';

export function Home(){
  const [authStatus] =  useState({auth: false});
  return(
    <div>
       <p>"Welcome to komodo, to search for your dream job please login or create an account
           if you have not yet done so."
      </p>
      {!authStatus.auth ? <SignUp/>: <SignIn/>}
    </div>
  );
}
