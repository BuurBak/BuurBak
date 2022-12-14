import { Stack } from '@mui/material'
import TrailerDimension from './TrailerDimension'
import React from 'react'

export function TrailerDimensions(props: {
  length: number
  width: number
  weight: number
}) {
  return (
    <Stack direction="row" gap={2}>
      <TrailerDimension
        measurement={`${props.length} meter`}
        dimension="Lengte"
      />
      <TrailerDimension
        measurement={`${props.width} meter`}
        dimension="Breedte"
      />
      <TrailerDimension
        measurement={`${props.weight} kilogram`}
        dimension="Gewicht"
      />
    </Stack>
  )
}
