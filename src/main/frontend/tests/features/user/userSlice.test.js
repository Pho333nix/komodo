import reducer, { signUpUser, signInUser, logout} from '../../../src/features/user/UserSlice'

test('should return initial state', ()=>{
  expect(reducer(undefined, {})).toEqual([{
    isLoggedIn: false,
    res: null,
    status: 'idle'
  }])
})

test('should handle a user ')
