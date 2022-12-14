import { TrailerOffer } from '../../../types/TrailerOffer'
import { Box, Grid, Stack, Typography } from '@mui/material'
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
        <Grid container spacing={1}>
          <Grid item xs>
            <UnderTextHeader icon={<TbStar />} text="rating" />
          </Grid>

          {matches ? (
            <Grid item xs>
              <UnderTextHeader
                icon={<TbCurrencyEuro />}
                text={trailerOffer.price}
              />
            </Grid>
          ) : null}

          <Grid item xs>
            <UnderTextHeader
              icon={<TbMapPin />}
              text={trailerOffer.address.city}
            />
          </Grid>
        </Grid>
      </Stack>
      {matches ? (
        <Box
          bgcolor="primary.main"
          alignItems="center"
          justifyContent="center"
          display="flex"
          p={2}
          sx={{ borderRadius: 4 }}
        >
          <UnderTextHeader
            icon={<TbCurrencyEuro />}
            text={trailerOffer.price}
            sx={{ color: 'primary.contrastText' }}
          />
        </Box>
      ) : null}
    </Box>
  )
}
