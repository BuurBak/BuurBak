import { Box, Grid, Typography } from '@mui/material'
import React from 'react'
import { TbArrowsLeftRight } from 'react-icons/tb'
import { GiWeight } from 'react-icons/gi'

interface TrailerDimensionI {
  measurement: string
  dimension: 'Lengte' | 'Breedte' | 'Gewicht'
}

export default function TrailerDimension({
  measurement,
  dimension,
}: TrailerDimensionI) {
  return (
    <Grid container gap={1} justifyContent="space-around">
      <Grid item xs={4}>
        {dimension === 'Lengte' ? <TbArrowsLeftRight size={40} /> : null}
        {dimension === 'Breedte' ? <TbArrowsLeftRight size={40} /> : null}
        {dimension === 'Gewicht' ? <GiWeight size={40} /> : null}
      </Grid>
      <Grid item xs={7}>
        <Box display="flex" flexDirection="column">
          <Typography variant="h6">{dimension}</Typography>
          <Typography variant="body2">{measurement}</Typography>
        </Box>
      </Grid>
    </Grid>
  )
}
