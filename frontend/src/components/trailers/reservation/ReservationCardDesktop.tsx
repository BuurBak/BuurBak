import { Button, Card, CardActions, CardContent } from '@mui/material'
import React from 'react'
import ReservationForm from './ReservationForm'
import { TrailerOffer } from '../../../types/TrailerOffer'

export default function ReservationCardDesktop({
  trailerOffer,
}: {
  trailerOffer: TrailerOffer
}) {
  return (
    <Card variant="outlined">
      <CardContent>
        <ReservationForm trailerOffer={trailerOffer} />
      </CardContent>
      <CardActions>
        <Button sx={{ marginX: 1 }} fullWidth variant="contained" size="large">
          Reserveer nu
        </Button>
      </CardActions>
    </Card>
  )
}
