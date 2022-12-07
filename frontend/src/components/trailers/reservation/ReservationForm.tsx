import { Card, CardContent, Stack } from '@mui/material'
import React, { useState } from 'react'
import { TrailerOffer } from '../../../types/TrailerOffer'
import { DateTime } from 'luxon'
import DateTimePicker from './DateTimePicker'

export default function ReservationForm(props: { trailerOffer: TrailerOffer }) {
  const now = DateTime.now()
  const [fromDate, setFromDate] = useState(now)
  const [toDate, setToDate] = useState(now)

  return (
    <Card variant="outlined">
      <CardContent>
        <Stack direction="column" gap={2}>
          <DateTimePicker
            title={'Van'}
            value={fromDate}
            minDate={now}
            onChange={(newValue) => setFromDate(newValue)}
          />
          <DateTimePicker
            title={'Tot'}
            value={toDate}
            minDate={fromDate}
            onChange={(newValue) => setToDate(newValue)}
          />
        </Stack>
      </CardContent>
    </Card>
  )
}
