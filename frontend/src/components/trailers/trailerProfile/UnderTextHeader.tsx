import { Box, SxProps, Typography } from '@mui/material'
import React, { ReactNode } from 'react'

interface UnderTextHeaderI {
  icon: ReactNode
  text: ReactNode
  sx?: SxProps
}

export default function UnderTextHeader({ icon, sx, text }: UnderTextHeaderI) {
  return (
    <Box display="flex" alignItems="center" sx={sx}>
      <Box flexShrink={0}>{icon}</Box>
      <Typography noWrap sx={sx}>
        {text}
      </Typography>
    </Box>
  )
}
