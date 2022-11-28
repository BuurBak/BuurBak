import { useEffect, useState } from 'react'
import { LocalStorage } from '../types/LocalStorage'

export function useLocalStorage<Type>(key: LocalStorage) {
  const [value, setValue] = useState<Type | null>(null)

  useEffect(() => {
    getItem()
  }, [])

  function setItem(value: Type) {
    localStorage.setItem(key, JSON.stringify(value))
    setValue(value)
  }

  function getItem(): Type {
    const value = localStorage.getItem(key)
    const parsedValue = JSON.parse(value)
    setValue(parsedValue)
    return parsedValue
  }

  function removeItem() {
    localStorage.removeItem(key)
    setValue(null)
  }

  return { value, setItem, getItem, removeItem }
}
