import { createSlice } from "@reduxjs/toolkit"

const initialState ={
  userName: ' ',
  userPasswd: ' '
}

export const signInSlice = createSlice({
  name: 'singIn',
  initialState,
  reducers:{
    signIn: (state, action) =>{
      state.userName = action.payload.name;
      state.userPasswd = action.payload.passwd;
    }
  }
})

export const { signIn } = signInSlice.actions; //action created automatically.
export default signInSlice.reducer;
