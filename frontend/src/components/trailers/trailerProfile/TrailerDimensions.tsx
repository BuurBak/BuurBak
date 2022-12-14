import { Stack } from '@mui/material'
import TrailerInfoIcon from './TrailerInfoIcon'
import React from 'react'
import { TbArrowsLeftRight } from 'react-icons/tb'
import { GiWeight } from 'react-icons/gi'

export function TrailerDimensions(props: {
  length: number
  width: number
  weight: number
}) {
  return (
    <Stack direction="row" gap={2}>
      <TrailerInfoIcon
        icon={<TbArrowsLeftRight size={40} />}
        subHeader={`${props.length} meter`}
        title="Lengte"
      />
      <TrailerInfoIcon
        icon={<TbArrowsLeftRight size={40} />}
        subHeader={`${props.width} meter`}
        title="Breedte"
      />
      <TrailerInfoIcon
        icon={<GiWeight size={40} />}
        subHeader={`${props.weight} kilogram`}
        title="Gewicht"
      />
    </Stack>
  )
}
