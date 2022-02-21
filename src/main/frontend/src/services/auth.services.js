/*
 * Authentication service
 *
 * a service that communicates with
 * the server and stores user information (data & jwt token)
 * in
 * */
import axios from 'axios';

const API_URL = '//localhost:8080/auth';

const signUp = async (info) =>{
 const res = await axios.post("//localhost:8080/api/ins", info)
    return res.data;
}

const signIn = async (credentials) =>{
   const res = await axios.post(API_URL, credentials)
    if(res.data.jwt){
      localStorage.setItem("user", JSON.stringify(res.data))
      sessionStorage.setItem('jwt', res.data.jwt)
    }
    return res.data
}

const logout = () =>{
  localStorage.removeItem("user");
}


const authService = {
  signIn,
  signUp,
  logout,
}

export default authService;
