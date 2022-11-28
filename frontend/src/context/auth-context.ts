import { createContext } from 'react'
import { User } from '../types/User'

export const AuthContext = createContext({
  user: null,
  setUser: (user: User) => {},
  removeUser: () => {},
})
