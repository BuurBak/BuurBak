import { ReactNode } from 'react'
import { AuthContext } from '../context/auth-context'
import { useLocalStorage } from '../hooks/use-localstorage'
import { LocalStorage } from '../types/LocalStorage'
import { User } from '../types/User'

interface AuthProviderProps {
  children: ReactNode
}

export default function AuthProvider({ children }: AuthProviderProps) {
  const {
    value: user,
    setItem: setUser,
    removeItem: removeUser,
  } = useLocalStorage<User>(LocalStorage.User)

  return (
    <AuthContext.Provider value={{ user, setUser, removeUser }}>
      {children}
    </AuthContext.Provider>
  )
}
