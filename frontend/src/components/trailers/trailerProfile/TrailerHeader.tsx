import { TrailerOffer } from '../../../types/TrailerOffer'
import { Box, Divider, Stack, Typography } from '@mui/material'
import { TbCurrencyEuro, TbMapPin, TbStar } from 'react-icons/tb'
import React from 'react'

interface TrailerHeaderProps {
  trailerOffer: TrailerOffer
}
export default function TrailerHeader({ trailerOffer }: TrailerHeaderProps) {
  return (
    <Stack direction={'row'} justifyContent="space-between">
      <Stack>
        <Typography variant="h4" color="primary">
          {trailerOffer.trailerType.name}
        </Typography>
        <Stack direction="row" gap={2} display="flex" alignItems="center">
          <Typography display="flex" alignItems="center">
            <TbStar /> rating
          </Typography>

          <Divider variant="middle" flexItem orientation="vertical" />

          <Typography display="flex" alignItems="center">
            <TbCurrencyEuro /> {trailerOffer.price}
          </Typography>

          <Divider variant="middle" flexItem orientation="vertical" />

          <Typography display="flex" alignItems="center">
            <TbMapPin /> {trailerOffer.location}
          </Typography>
        </Stack>
      </Stack>
      <Box
        bgcolor="primary.main"
        alignItems="center"
        justifyContent="center"
        display="flex"
        p={2}
        sx={{ borderRadius: 4 }}
      >
        <Typography variant="button" color="white">
          <TbCurrencyEuro /> {trailerOffer.price}
        </Typography>
      </Box>
    </Stack>
  )
}
