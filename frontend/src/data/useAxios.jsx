import { useState, useEffect } from 'react'
import { instance } from './axiosInstance'

const useAxios = (requestConfig) => {
  const [response, setResponse] = useState(null)
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await instance.request(requestConfig)
        setResponse(response.data)
      } catch (error) {
        setError(error)
      } finally {
        setLoading(false)
      }
    }
    fetchData()
  }, [requestConfig])

  return { response, error, loading }
}

export default useAxios
