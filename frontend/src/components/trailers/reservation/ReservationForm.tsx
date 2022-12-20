import { Button, Card, CardActions, CardContent, Stack } from '@mui/material'
import React, { useState } from 'react'
import { DateTime } from 'luxon'
import DateTimePicker from './DateTimePicker'

export default function ReservationForm() {
  const now = DateTime.now()
  const [fromDate, setFromDate] = useState(now)
  const [toDate, setToDate] = useState(now)

  const handleSetFromDate = (newValue: DateTime) => {
    if (toDate < newValue) {
      setToDate(newValue)
    }
    setFromDate(newValue)
  }

  return (
    <Card variant="outlined">
      <CardContent>
        <Stack direction="column" gap={2}>
          <DateTimePicker
            title={'Van'}
            value={fromDate}
            minDate={now}
            onChange={handleSetFromDate}
          />
          <DateTimePicker
            title={'Tot'}
            value={toDate}
            minDate={fromDate}
            onChange={(newValue) => setToDate(newValue)}
          />
        </Stack>
      </CardContent>
      <CardActions>
        <Button sx={{ marginX: 1 }} fullWidth variant="contained" size="large">
          Reserveer nu
        </Button>
      </CardActions>
    </Card>
  )
}
