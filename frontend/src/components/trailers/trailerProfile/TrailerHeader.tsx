import { TrailerOffer } from '../../../types/TrailerOffer'
import { Box, Stack, Typography } from '@mui/material'
import { TbCurrencyEuro, TbMapPin, TbStar } from 'react-icons/tb'
import React from 'react'
import UnderTextHeader from './UnderTextHeader'
import useMediumBreakpoint from '../../../hooks/use-medium-breakpoint'

interface TrailerHeaderProps {
  trailerOffer: TrailerOffer
}
export default function TrailerHeader({ trailerOffer }: TrailerHeaderProps) {
  const matches = useMediumBreakpoint()

  return (
    <Box
      sx={{
        display: 'flex',
        flexWrap: 'wrap',
        justifyContent: 'space-between',
      }}
    >
      <Stack maxWidth="100%">
        <Typography variant="h4" color="primary">
          {trailerOffer.trailerType.name}
        </Typography>
        <Box
          sx={{
            display: 'flex',
            flexWrap: 'wrap',
            justifyContent: 'space-between',
            gap: 1,
          }}
        >
          <UnderTextHeader>
            <TbStar /> rating
          </UnderTextHeader>

          {/*<Divider variant="middle" flexItem orientation="vertical" />*/}

          {matches ? (
            <UnderTextHeader>
              <TbCurrencyEuro /> {trailerOffer.price}
            </UnderTextHeader>
          ) : null}

          {/*<Divider variant="middle" flexItem orientation="vertical" />*/}

          <UnderTextHeader>
            <TbMapPin /> {trailerOffer.location}
          </UnderTextHeader>
        </Box>
      </Stack>
      <Box
        bgcolor="primary.main"
        alignItems="center"
        justifyContent="center"
        display="flex"
        p={2}
        sx={{ borderRadius: 4 }}
      >
        <UnderTextHeader sx={{ color: 'primary.contrastText' }}>
          <TbCurrencyEuro /> {trailerOffer.price}
        </UnderTextHeader>
      </Box>
    </Box>
  )
}
