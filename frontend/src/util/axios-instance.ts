import axios from 'axios'
import { LocalStorage } from '../types/LocalStorage'
import { Tokens } from '../types/Tokens'

const instance = axios.create({
  baseURL:
    process.env.NODE_ENV === 'development'
      ? 'http://localhost:8000/api/v1'
      : '/api/v1',
  timeout: 3000, // 3 seconds
})

instance.interceptors.request.use((config) => {
  const stringifiedTokens: string | null = localStorage.getItem(
    LocalStorage.Tokens
  )

  if (!stringifiedTokens) return config

  const tokens: Tokens = JSON.parse(stringifiedTokens)

  config.headers = {
    ...config.headers,
    Authorization: 'Bearer ' + tokens.access_token,
  }

  return config
})

export { instance }
