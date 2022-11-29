import { Address } from './Address'
import { Role } from './Role'

export interface User {
  id: string
  name?: string
  email?: string
  roles: Role[]
  iban?: string
  number?: string
  address?: Address
  date_of_birth?: Date
  profile_picture_url?: string
}
