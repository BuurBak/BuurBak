import { useState, useEffect } from 'react'
import { instance } from '../util/axios-instance'
import { AxiosRequestConfig, AxiosError } from 'axios'

export default function useAxios<T>(axiosParams: AxiosRequestConfig) {
  const [response, setResponse] = useState<T>()
  const [error, setError] = useState<AxiosError>()
  const [loading, setLoading] = useState(true)

  const fetchData = async (params: AxiosRequestConfig) => {
    try {
      const result = await instance.request<T>(params)
      setResponse(result.data)
    } catch (error) {
      if (error instanceof AxiosError) setError(error)
      console.error(error)
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
