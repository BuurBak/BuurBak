import axios from 'axios'

export default function authHeader() {
  const tokens = JSON.parse(localStorage.getItem('tokens'))

  if (tokens && tokens['access_token']) {
    return { Authorization: 'Bearer ' + tokens['access_token'] }
  } else {
    return {}
  }
}

const instance = axios.create({
  baseURL:
    process.env.NODE_ENV === 'development'
      ? 'http://localhost:8000/api/v1'
      : '/api/v1',
  timeout: 3000, // 3 seconds
  headers: authHeader(),
})

export { instance }
