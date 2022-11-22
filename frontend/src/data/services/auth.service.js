import { instance } from '../axiosInstance'

const signUp = async (email, password, name, number, address) => {
  const response = await instance.post('/auth/register', {
    email,
    password,
    name,
    number,
    address,
  })
  await login(email, password)
  await getCurrentUser()
  return response.data
}

const login = async (email, password) => {
  const response = await instance.post('/auth/login', { email, password })
  localStorage.setItem('tokens', JSON.stringify(response.data))
  await getCurrentUser()
  return response.data
}

const getCurrentUser = async () => {
  const response = await instance.get('/customers/self')
  return response.data
}

const logout = () => {
  localStorage.removeItem('tokens')
}

const authService = {
  signUp,
  login,
  logout,
  getCurrentUser,
}

export default authService
