import { TrailerOffer } from '../../../types/TrailerOffer'
import { Box, Divider, Stack, Typography } from '@mui/material'
import { TbCurrencyEuro, TbMapPin, TbStar } from 'react-icons/tb'
import React from 'react'

interface TrailerHeaderProps {
  trailerOffer: TrailerOffer
}
export default function TrailerHeader({ trailerOffer }: TrailerHeaderProps) {
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
          <Typography
            sx={{
              display: 'flex',
              alignItems: 'center',
              whiteSpace: 'nowrap',
              overflow: 'hidden',
              textOverflow: 'ellipsis',
            }}
          >
            <TbStar /> rating
          </Typography>

          {/*<Divider variant="middle" flexItem orientation="vertical" />*/}

          <Typography
            sx={{
              display: 'flex',
              whiteSpace: 'nowrap',
              alignItems: 'center',
              overflow: 'hidden',
              textOverflow: 'ellipsis',
            }}
          >
            <TbCurrencyEuro /> {trailerOffer.price}
          </Typography>

          {/*<Divider variant="middle" flexItem orientation="vertical" />*/}

          <Typography
            sx={{
              display: 'flex',
              whiteSpace: 'nowrap',
              alignItems: 'center',
              overflow: 'hidden',
              textOverflow: 'ellipsis',
              width: 'initial',
            }}
          >
            <TbMapPin /> {trailerOffer.location}
          </Typography>
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
        <Typography
          variant="button"
          color="white"
          display="flex"
          alignItems="center"
        >
          <TbCurrencyEuro /> {trailerOffer.price}
        </Typography>
      </Box>
    </Box>
  )
}
