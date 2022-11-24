import { useContext } from 'react'
import { AuthContext } from '../context/auth-context'
import { LocalStorage } from '../types/LocalStorage'
import { User } from '../types/User'
import { useLocalStorage } from './use-localstorage'

export const useUser = () => {
  const { user, setUser } = useContext(AuthContext)
  const { setItem } = useLocalStorage()

  const addUser = (user: User) => {
    setUser(user)
    setItem(LocalStorage.User, JSON.stringify(user))
  }

  const removeUser = () => {
    setUser(null)
    setItem(LocalStorage.User, '')
  }

  return { user, addUser, removeUser }
}
