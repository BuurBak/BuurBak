import { Card, CardContent, Typography } from '@mui/material'
import React from 'react'
import { TrailerOffer } from '../../../types/TrailerOffer'

export default function ReservationForm(props: { trailerOffer: TrailerOffer }) {
  return (
    <Card variant="outlined">
      <CardContent>
        <Typography variant="h4">Van</Typography>
        <Typography variant="h4">Tot</Typography>
      </CardContent>
    </Card>
  )
}
