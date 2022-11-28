import { instance } from '../util/axios-instance'
import { User } from '../types/User'
import { Tokens } from '../types/Tokens'
import { SignUp } from '../types/SignUp'
import { LocalStorage } from '../types/LocalStorage'
import { useLocalStorage } from './use-localstorage'

export function useAuth() {
  const {
    value: user,
    setItem: setUser,
    removeItem: removeUser,
  } = useLocalStorage<User>(LocalStorage.User)
  const { setItem: setTokens, removeItem: removeTokens } =
    useLocalStorage<Tokens>(LocalStorage.Tokens)

  const login = async (data: { username: string; password: string }) => {
    const response = await instance.request<Tokens>({
      method: 'post',
      url: '/auth/login',
      data,
    })
    setTokens(response.data)
    await getCurrentUser()
    // TODO find better fix for state not updating components
    window.location.reload()
  }

  const register = async (data: SignUp) => {
    await instance.request<String>({
      method: 'post',
      url: '/auth/register',
      data,
    })
    await login({ username: data.email, password: data.password })
  }

  const logout = () => {
    removeTokens()
    removeUser()
  }

  const getCurrentUser = async () => {
    const response = await instance.request<User>({
      method: 'get',
      url: '/customers/self',
    })
    setUser(response.data)
  }

  // Return the user object and auth methods
  return {
    user,
    login,
    register,
    logout,
  }
}
