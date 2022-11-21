import { instance } from '../axiosInstance'

const signUp = async (
  email,
  password,
  firstName,
  lastName,
  number,
  address
) => {
  const name = firstName + ' ' + lastName
  return instance
    .post('/auth/register', {
      email,
      password,
      name,
      number,
      address,
    })
    .then((response) => {
      login(email, password)
      return response.data
    })
}

const login = async (email, password) => {
  return instance
    .post('/auth/login', {
      email,
      password,
    })
    .then((response) => {
      if (response.status === 200) {
        localStorage.setItem('tokens', JSON.stringify(response.data))
      }
      return response.data
    })
}

const logout = () => {
  localStorage.removeItem('tokens')
}

const authService = {
  signUp,
  login,
  logout,
}

export default authService
