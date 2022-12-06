import { TrailerType } from './TrailerType'

export interface TrailerOfferInfo {
  id: string
  trailerType: TrailerType
  location: string
  price: number
  images?: string[]
}
