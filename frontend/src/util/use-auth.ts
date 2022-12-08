import { useContext } from 'react'
import { authContext } from './auth-context'

// Hook for child components to get the auth object ...
// ... and re-render when it changes.
export const useAuth = () => {
  return useContext(authContext)
}
