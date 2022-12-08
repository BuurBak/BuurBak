export default function authHeader() {
  const user = JSON.parse(localStorage.getItem('user'))

  if (user && user.accesToken) {
    return { Authorization: 'Bearer ' + user.accesToken }
  } else {
    return {}
  }
}
