import { Stack } from '@mui/material'
import React, { useState } from 'react'
import { DateTime } from 'luxon'
import DateTimePicker from './DateTimePicker'
import { TrailerOffer } from '../../../types/TrailerOffer'

export default function ReservationForm({
  trailerOffer,
}: {
  trailerOffer: TrailerOffer
}) {
  const now = DateTime.now()
  const [fromDate, setFromDate] = useState(now)
  const [toDate, setToDate] = useState(now)

  const handleSetFromDate = (newValue: DateTime) => {
    // Set to date to from date if from date is after to date
    if (toDate < newValue) {
      setToDate(newValue)
    }
    setFromDate(newValue)
  }

  console.log(trailerOffer)

  return (
    <Stack direction="column" gap={2}>
      <DateTimePicker
        startTime={DateTime.fromISO(trailerOffer.pickUpTimeStart)}
        endTime={DateTime.fromISO(trailerOffer.pickUpTimeEnd)}
        title={'Van'}
        value={fromDate}
        minDate={now}
        onChange={handleSetFromDate}
      />
      <DateTimePicker
        startTime={DateTime.fromISO(trailerOffer.dropOffTimeStart)}
        endTime={DateTime.fromISO(trailerOffer.dropOffTimeEnd)}
        title={'Tot'}
        value={toDate}
        minDate={fromDate}
        onChange={(newValue) => setToDate(newValue)}
      />
    </Stack>
  )
}
