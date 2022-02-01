import {createSlice, createAsyncThunk} from '@reduxjs/toolkit';
import axios from 'axios';


const initialState = {
  userName: ' ',
  password: ' ',
  pid: ' ',
  email: ' ',
  status: 'idle',
  res: ' '
};

export const signUpUser = createAsyncThunk('signUp/postUser', async(dispatch, getstate)=>{
  const res = await axios.post("https://localhost:8080/api/ins",{
    "name": "teeesting",
    "id": "",
    "surname": "",
    "pnr": "",
    "email": "",
    "password": "",
    "role_id": "" ,
    "username":""
  });
  console.log('POST: ', res)
  return res.data;
})

export const signUpSlice = createSlice({
  name: 'signUp',
  initialState,
  reducers:{
    signUp:(state, actions)=>{
      state.userName = actions.payload.name;
      state.password = actions.payload.passwd;
      state.pid = actions.payload.pid;
      state.email = actions.payload.email;
    },
    extraReducers:{
      [signUpUser.pending]: (state, action)=>{
        state.status = 'loading'
      },
      [signUpUser.fulfilled]:(state, action)=>{
        state.status = 'sucess'
        state.res = action.payload;
      },
      [signUpUser.rejected]:(state, action)=>{
        state.res = action.payload
      }
    }
  }
})

export const {signUp} = signUpSlice.actions;
export default signUpSlice.reducer;
