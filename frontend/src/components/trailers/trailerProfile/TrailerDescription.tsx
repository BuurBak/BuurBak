import { Divider, Stack, Typography } from '@mui/material'
import React from 'react'
import { TrailerOffer } from '../../../types/TrailerOffer'
import { TrailerDimensions } from './TrailerDimensions'

interface TrailerDescriptionI {
  trailer: TrailerOffer
}

export function TrailerDescription({ trailer }: TrailerDescriptionI) {
  return (
    <Stack gap={2}>
      <Divider />
      <TrailerDimensions
        length={trailer.length}
        width={trailer.width}
        weight={trailer.weight}
      />
      <Typography variant="h4" color="primary.main">
        Omschrijving
      </Typography>
      <Typography sx={{ wordWrap: 'break-word' }}>
        {trailer.description}
      </Typography>
    </Stack>
  )
}
