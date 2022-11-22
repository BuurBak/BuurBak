import axios from 'axios'

const instance = axios.create({
  baseURL:
    process.env.NODE_ENV === 'development'
      ? 'http://localhost:8000/api/v1'
      : '/api/v1',
  timeout: 3000, // 3 seconds
})

instance.interceptors.request.use((config) => {
  const tokens = JSON.parse(localStorage.getItem('tokens'))

  if (tokens && tokens['access_token']) {
    config.headers.Authorization = 'Bearer ' + tokens['access_token']
  }

  return config
})

export { instance }
