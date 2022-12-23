import {
  MenuItem,
  Select,
  Stack,
  TextField,
  Typography,
  useMediaQuery,
  useTheme,
} from '@mui/material'
import { DesktopDatePicker, MobileDatePicker } from '@mui/x-date-pickers'
import React from 'react'
import { DateTime } from 'luxon'

const getOptionsBetweenDates = ({
  startTime,
  endTime,
}: {
  startTime: DateTime
  endTime: DateTime
}) => {
  let option = startTime
  const options: string[] = ['Selecteer']
  while (option <= endTime) {
    options.push(option.toFormat('HH:mm'))
    option = option.plus({ minutes: 30 })
  }
  return options
}

export default function DateTimePicker({
  title,
  value,
  minDate,
  onChange,
  startTime,
  endTime,
}: {
  title: string
  value: DateTime
  minDate: DateTime
  onChange: (DateTime) => void
  startTime: DateTime
  endTime: DateTime
}) {
  const theme = useTheme()
  const isMobile = useMediaQuery(theme.breakpoints.down('md'))
  const options = getOptionsBetweenDates({ startTime, endTime })

  return (
    <>
      <Typography variant="h5">{title}</Typography>
      <Stack direction="row" gap={2}>
        {isMobile ? (
          <MobileDatePicker
            label="Datum"
            inputFormat="dd/MM/yyyy"
            value={value}
            minDate={minDate}
            onChange={onChange}
            renderInput={(params) => <TextField fullWidth {...params} />}
          />
        ) : (
          <DesktopDatePicker
            label="Datum"
            inputFormat="dd/MM/yyyy"
            value={value}
            minDate={minDate}
            onChange={onChange}
            renderInput={(params) => <TextField fullWidth {...params} />}
          />
        )}
        <Select defaultValue={options[0]}>
          {options.map((option) => (
            <MenuItem key={option} value={option}>
              {option}
            </MenuItem>
          ))}
        </Select>
      </Stack>
    </>
  )
}
