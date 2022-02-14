import { configureStore } from '@reduxjs/toolkit';
import homeReducer from '../features/home/homeSlice';
import userReducer from '../features/user/UserSlice';

/**
 *creates and configures the redux store,
 * combines all our reducers
 * */
export const store = configureStore({
  reducer: {
    home : homeReducer,
    user: userReducer
  }
});
