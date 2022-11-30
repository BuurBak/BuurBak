import { TrailerType } from './TrailerType'
import { User } from './User'
import { Image } from './Image'

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
  location: string
  price: number
  available: boolean
  createdAt: Date
  updatedAt: Date
  images: Image[]
}
