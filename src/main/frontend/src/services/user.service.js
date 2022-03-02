/*
 * Data Service
 *
 * This handles retrieving of protected data from servers.
 *
 *
 */
import axios from 'axios';
import authHeader from './auth-headers';
const API_URL = '//localhost:8080/auth';

  const getRecruitPage = ()=> {
    return axios.get(API_URL + "/user/recruit", {headers: authHeader()})
  }
const getRecruiterPage = ()=> {
    return axios.get(API_URL + "/user/recruiter", {headers: authHeader()})
  }

const getCompetenceList = () =>{
    return axios.get(API_URL + "/api/competence", {headers: authHeader()})
}

const userService ={
  getRecruitPage,
  getRecruiterPage,
  getCompetenceList
}

export default userService;
