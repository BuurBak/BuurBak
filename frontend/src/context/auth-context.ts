import { createContext } from 'react'
import { User } from '../types/User'

interface AuthContextI {
  user: User | null
  setUser: (user: User | null) => void
}

export const AuthContext = createContext<AuthContextI>({
  user: null,
  setUser: () => {},
})