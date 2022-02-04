import { configureStore } from '@reduxjs/toolkit';
import counterReducer from '../features/counter/counterSlice';
import homeReducer from '../features/home/homeSlice';
import userReducer from '../features/user/UserSlice';
import {reducer as reduxFormReducer } from 'redux-form';
export const store = configureStore({
  reducer: {
    counter: counterReducer,
    form: reduxFormReducer,
    home : homeReducer,
    user: userReducer
  }
});
