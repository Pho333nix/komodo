import {createSlice} from '@reduxjs/toolkit';

const initialState = {
  userName: ' ',
  password: ' '
};

export const signUpSlice = createSlice({
  name: 'signUp',
  initialState,
  reducers:{
    signUp:(state, actions)=>{
      state.userName = actions.payload.name;
      state.password = actions.payload.passwd;
    }
  }
})

export const {signUp} = signUpSlice.actions;
export default signUpSlice.reducer;
