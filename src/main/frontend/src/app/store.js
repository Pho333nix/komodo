import { configureStore } from '@reduxjs/toolkit';
import counterReducer from '../features/counter/counterSlice';
import signInReducer from '../features/signIn/signInSlice';
import signUpReducer from '../features/signUp/signUpSlice'
import homeReducer from '../features/home/homeSlice';

export const store = configureStore({
  reducer: {
    counter: counterReducer,
    signIn: signInReducer,
    signUp: signUpReducer,
    home : homeReducer
  }});
