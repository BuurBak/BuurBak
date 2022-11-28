import { AppBar } from '@mui/material'
import { Box } from '@mui/system'
import './TrailerFormFooter.css'

export default function TrailerFormFooter() {
  return (
    <Box>
      <AppBar
        color="default"
        sx={{
          position: 'absolute',
          bottom: 0,
          left: 0,
          backgroundColor: 'black',
        }}
      ></AppBar>
    </Box>
  )
}
