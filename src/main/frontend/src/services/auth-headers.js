
/**
 * @function authHeader is a service that will be used
 * to check wether a user has authorization to access certain
 * resourses.
 *
 * check in local storage, if there is a logged in user
 * return HTTP authorization, else return empty object.
 *
 * */
export default function authHeader() {
  const user = JSON.parse(localStorage.getItem('user'));
  if (user && user.jwtToken) {
    return { Authorization: 'Bearer ' + user.jwtToken };
  } else {
    return {};
  }
}
