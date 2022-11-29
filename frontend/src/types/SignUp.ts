import { CreateAddress } from './CreateAddress'

export interface SignUp {
  email: string
  password: string
  name: string
  number: string
  address: CreateAddress
}
