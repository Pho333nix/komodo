import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import axios from 'axios';
import userService from  '../../services/user.service';
import authService from '../../services/auth.services';

const user = JSON.parse(localStorage.getItem("user"));

export const signUpUser = createAsyncThunk('user/signUpUser',async (obj, thunkAPI) =>{
  try{
    const res = await authService.signUp(obj);
    return res;
  }catch(err){
   // throw new Error('user already exists, try signing in or reset account')
      return thunkAPI.rejectWithValue(err)
  }
})

export const signInUser = createAsyncThunk('user/signInUser',async(credentials, thunkAPI)=>{
  try{
    const res = await authService.signIn(credentials);
    return res;
  }catch(err){
    return thunkAPI.rejectWithValue(err);
  }
});

/**
 *  removes the jwt token stored in localStorage
 * */
export const logout = createAsyncThunk("user/logout", async() =>{
  await authService.logout()
});

const initialState = user ?
      {isLoggedIn: true, res : user,  status: 'idle'} :
      {isLoggedIn: false, res: null, status: 'idle'}

/**
 * This function creates userSlice. This function creates
 * the reducer and actions associated with the reducers.
 * extraReducers: handles the async reducer logic.
 */
export const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers:{
    clearState: (state) =>{
      state.status = 'idle';
    }
  },
  extraReducers:{
       [signUpUser.pending]: (state, action)=>{
        state.status = 'loading';
      },
      [signUpUser.fulfilled]:(state, action)=>{
        state.status = 'success';
        state.res = action.payload;
         state.isLoggedIn = false;
      },
      [signUpUser.rejected]:(state, action)=>{
        state.res = action.payload;
        state.status = 'error';
      },
    [signInUser.pending]:(state, action)=>{
      state.status = 'loading';
    },
    [signInUser.fulfilled]:(state, action)=>{
      state.status = 'success';
      state.res = action.payload;
      state.isLoggedIn = true;
    },
    [signInUser.rejected]:(state, action)=>{
      state.status = 'login failed';
      state.res = action.payload;
      state.isLoggedIn = false;
    },
    [logout.fulfilled]: (state, action)=>{
      state.isLoggedIn = false;
      state.res = null;
    }
  }
});

export const userSelector = (state) => state.user;
export const personSelector = (state) => state.user.res.person;
export const { clearState } = userSlice.actions;
export default userSlice.reducer;
