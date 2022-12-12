import { Divider, Stack } from '@mui/material'
import TrailerDimension from './TrailerDimension'
import React from 'react'
import { TrailerOffer } from '../../../types/TrailerOffer'

interface TrailerDescriptionI {
  trailer: TrailerOffer
}

export function TrailerDescription({ trailer }: TrailerDescriptionI) {
  return (
    <Stack gap={2}>
      <Divider />
      <Stack direction="row" gap={2} justifyContent="space-around">
        <TrailerDimension
          measurement={`${trailer.length} meter`}
          dimension="Lengte"
        />
        <Divider orientation="vertical" />
        <TrailerDimension
          measurement={`${trailer.width} meter`}
          dimension="Breedte"
        />
        <Divider orientation="vertical" />
        <TrailerDimension
          measurement={`${trailer.weight} kilogram`}
          dimension="Gewicht"
        />
      </Stack>
    </Stack>
  )
}
