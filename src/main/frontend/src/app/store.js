import { configureStore, getDefaultMiddleware } from '@reduxjs/toolkit';
import homeReducer from '../features/home/homeSlice';
import userReducer from '../features/user/UserSlice';
import recruitReducer from '../features/user/usertypes/recruit/recruitSlice'
/**
 *creates and configures the redux store,
 * combines all our reducers
 * */
export const store = configureStore({
  reducer: {
    home : homeReducer,
    user: userReducer,
    recruit: recruitReducer
  }
  /*,
  middleware: getDefaultMiddleware({
    serializableCheck: false,
  })
  */
});
