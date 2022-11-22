import { useState, useEffect } from 'react'
import { instance } from './axiosInstance'

export default function useAxios(axiosParams) {
  const [response, setResponse] = useState(undefined)
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(true)

  const fetchData = async (params) => {
    try {
      const result = await instance.request(params)
      setResponse(result.data)
    } catch (error) {
      setError(error)
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    fetchData(axiosParams)
  }, []) // execute once only

  const refetch = () => {
    fetchData(axiosParams)
  }

  return { response, error, loading, refetch }
}
