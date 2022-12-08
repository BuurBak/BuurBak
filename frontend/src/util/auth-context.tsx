import React, { createContext } from 'react'
import { User } from '../types/User'
import { useProvideAuth } from './use-provide-auth'

interface AuthContext {
  user: User | undefined
}

export const authContext = createContext<AuthContext>({
  user: undefined,
})

// Provider component that wraps your app and makes auth object ...
// ... available to any child component that calls useAuth().
export function ProvideAuth({ children }) {
  const auth = useProvideAuth()
  return <authContext.Provider value={auth}>{children}</authContext.Provider>
}
