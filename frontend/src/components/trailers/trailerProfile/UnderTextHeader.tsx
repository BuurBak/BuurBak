import { SxProps, Typography } from '@mui/material'
import React, { ReactNode } from 'react'

interface UnderTextHeaderI {
  children: ReactNode
  sx?: SxProps
}

export default function UnderTextHeader({ children, sx }: UnderTextHeaderI) {
  return (
    <Typography
      noWrap
      sx={{
        display: 'flex',
        whiteSpace: 'nowrap',
        alignItems: 'center',
        overflow: 'hidden',
        textOverflow: 'ellipsis',
        width: 'initial',
        ...sx,
      }}
    >
      {children}
    </Typography>
  )
}
