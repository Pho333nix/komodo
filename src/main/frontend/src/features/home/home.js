import React, {useEffect,useState  } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchPosts, posts } from './homeSlice';
import axios from 'axios';

export function Home(){

  //const { hasAcc } = useSelector(selectToggleAcc);
  const  [data, setData] =useState({hits:[]});
  //useEffect(()=>{ localState.status = hasAcc });
  const [isLoading, setIsLoading] = useState(false);
  const [isError, setIsError] = useState(false);
  const dispatch = useDispatch();
  const homeState = useSelector(posts);

  useEffect(()=>{
    dispatch(fetchPosts());
    console.log('dispatch');
  },[dispatch]);

   return(

     <div>
       <p>"Welcome to komodo, to search for your dream job please login or create an account
         if you have not yet done so." </p>
       {console.log('state: ', homeState)}
   </div>);

}
