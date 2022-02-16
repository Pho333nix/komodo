//TODO: everything
/**
 * check in local storage, if there is a logged in user
 * return HTTP authorization, else return empty object.
 *
 * */
export default function authHeader() {
  const user = JSON.parse(localStorage.getItem('user'));
  if (user && user.accessToken) {
    return { Authorization: 'Bearer ' + user.accessToken };
  } else {
    return {};
  }
}
