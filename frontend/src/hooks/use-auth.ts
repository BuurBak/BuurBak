import { useEffect } from 'react'
import { instance } from '../util/axios-instance'
import { User } from '../types/User'
import { Tokens } from '../types/Tokens'
import { LocalStorage } from '../types/LocalStorage'
import { useUser } from './use-user'
import { useLocalStorage } from './use-localstorage'

export function useAuth() {
  const { user, addUser, removeUser } = useUser()
  const { getItem, setItem } = useLocalStorage()

  useEffect(() => {
    const stringifiedUser = getItem(LocalStorage.User)
    if (stringifiedUser) {
      addUser(JSON.parse(stringifiedUser))
    }
  }, [])

  const login = async (data: { username: string; password: string }) => {
    const response = await instance.request<Tokens>({
      method: 'post',
      url: '/auth/login',
      data,
    })
    setItem(LocalStorage.Tokens, JSON.stringify(response.data))
    await getCurrentUser()
  }

  const register = async () => {}

  const logout = () => {
    setItem(LocalStorage.Tokens, '')
    removeUser()
  }

  const getCurrentUser = async () => {
    const response = await instance.request<User>({
      method: 'get',
      url: '/customers/self',
    })
    addUser(response.data)
  }

  // Return the user object and auth methods
  return {
    user,
    login,
    register,
    logout,
  }
}
