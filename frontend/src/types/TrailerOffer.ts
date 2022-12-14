import { TrailerType } from './TrailerType'
import { User } from './User'
import { Address } from './Address'

export interface TrailerOffer {
  id: string
  trailerType: TrailerType
  owner: User
  length: number
  height: number
  width: number
  weight: number
  capacity: number
  licensePlateNumber: string
  pickUpTimeStart: string
  pickUpTimeEnd: string
  dropOffTimeStart: string
  dropOffTimeEnd: string
  fakeLatitude: number
  fakeLongitude: number
  description: string
  price: number
  available: boolean
  address: Pick<Address, 'city'>
  createdAt: Date
  updatedAt: Date
}
