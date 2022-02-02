import { createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import axios from 'axios';
//import { client } from '../../api/api'
const initialState ={
  posts: [],
  status: null,
  error: null,
  hasAcc: false
};

export const fetchPosts = createAsyncThunk('home/fetchPosts', async(dispatch, getstate)=>{
  const res = await axios("https://jsonplaceholder.typicode.com/users");
  console.log('res', res)
  return res.data;
})
/*
 * accpets:
 * (1) A string that will be used as the prefix for the generated action types
   (2) A "payload creator" callback function that should return a Promise
   containing some data,  or a rejected Promise with an error
   response -->{data: []},
 */
export const homeSlice= createSlice({
  name: 'home',
  initialState,
  reducers:{
    toggleHasAcc: (state)=>{
      state.hasAcc = true;
    }
  },
  extraReducers:{
    [fetchPosts.pending]: (state, action)=>{
      state.status = 'loading'
    },
    [fetchPosts.fulfilled]:(state, action) =>{
      state.status = 'success'
      state.posts=action.payload;
    },
    [fetchPosts.rejected]: (state, action)=>{
      state.state = 'failed'
    }
  }
});

export const posts = (state) => state.home;
export const { toggleHasAcc } = homeSlice.actions;
export default homeSlice.reducer;
