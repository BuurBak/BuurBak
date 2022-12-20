import { useEffect, useState } from 'react'
import { instance } from '../util/axios-instance'
import { AxiosError, AxiosRequestConfig } from 'axios'

export default function useAxios<T>(axiosParams: AxiosRequestConfig) {
  const [response, setResponse] = useState<T>()
  const [error, setError] = useState<AxiosError | undefined>()
  const [loading, setLoading] = useState(true)

  function delay(time) {
    return new Promise((resolve) => setTimeout(resolve, time))
  }

  const fetchData = async (params: AxiosRequestConfig) => {
    try {
      // await delay(3000)
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
