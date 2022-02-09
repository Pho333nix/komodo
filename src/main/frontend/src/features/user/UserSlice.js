import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import axios from 'axios';
/**
 *
 * */
export const signUpUser = createAsyncThunk('user/signUpUser',async (obj, thunkAPI) =>{
  try{
  const res = await axios.post("//localhost:8080/api/ins",obj)
    return res.data;
  }catch(err){
   // throw new Error('user already exists, try signing in or reset account')
      return thunkAPI.rejectWithValue(err)
  }
})

export const signInUser = createAsyncThunk('user/signInUser',async(credentials, thunkAPI)=>{
  try{
    const res = await axios.post('//localhost:8080/auth', credentials)
    if(res.data.jwt){
      localStorage.setItem("user", JSON.stringify(res.data))
      sessionStorage.setItem('jwt', res.data.jwt)
    }
    return res.data;
  }catch(err){
    return thunkAPI.rejectWithValue(err);
  }
});

/**
 * initial state of the reducer
 */
const initialState = {
  userName: ' ',
  password: ' ',
  pid: ' ',
  email: ' ',
  role: ' ',
  status: 'idle',
  res: ' '
};

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
    },
    [signInUser.rejected]:(state, action)=>{
      state.status = 'login failed';
      state.res = action.payload;
    },
  }
});

export const userSelector = (state) => state.user;
export const { clearState } = userSlice.actions;
export default userSlice.reducer;
