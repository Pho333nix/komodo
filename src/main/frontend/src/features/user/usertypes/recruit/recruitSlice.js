import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import axios from 'axios';
import userService from  '../../../../services/user.service';

const user = JSON.parse(localStorage.getItem("user"));

export const getCompetenceList = createAsyncThunk('recruit/getCompetence',async (thunkAPI) =>{
  try{
    const res = await userService.getCompetenceList();
    return res;
  }catch(err){
   throw new Error(err)
    //  return thunkAPI.rejectWithValue(err)
  }
})

const initialState = {
  list: null,
  state: 'idle',
  errorMsg: ' '
}

export const recruitSlice = createSlice({
  name: 'recruit',
  initialState,
  extraReducers:{
    [getCompetenceList.pending]: (state, action)=>{
      state.status = 'loading'
    },
    [getCompetenceList.fulfilled]: (state, action)=>{
      state.status = 'success';
      state.list = action.payload;
    },
    [getCompetenceList.rejected]: (state, action)=>{
    state.status = 'error';
    }
  }
})
export const stateSelector = (state) => state;
export default recruitSlice.reducer;
