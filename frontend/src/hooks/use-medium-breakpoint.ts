import { useMediaQuery, useTheme } from '@mui/material'

export default function useMediumBreakpoint() {
  const theme = useTheme()
  return useMediaQuery(theme.breakpoints.up('md'))
}
