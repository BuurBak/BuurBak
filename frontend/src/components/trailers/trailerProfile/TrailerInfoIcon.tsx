import { Box, Grid, Typography } from '@mui/material'
import React, { ReactNode } from 'react'

interface TrailerInfoIconI {
  subHeader: string
  title: string
  icon: ReactNode
}

export default function TrailerInfoIcon({
  subHeader,
  title,
  icon,
}: TrailerInfoIconI) {
  return (
    <Grid container gap={1} alignItems="center" justifyContent="center">
      <Grid item xs={4} textAlign="center">
        {icon}
      </Grid>
      <Grid item xs={8}>
        <Box
          display="flex"
          flexDirection="column"
          justifyContent="start"
          alignItems="center"
        >
          <Typography variant="h6" textAlign="center">
            {title}
          </Typography>
          <Typography variant="body2" textAlign="center">
            {subHeader}
          </Typography>
        </Box>
      </Grid>
    </Grid>
  )
}
