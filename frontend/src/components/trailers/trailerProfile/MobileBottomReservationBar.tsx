import { Button, Paper, Typography } from '@mui/material'

interface MobileBottomReservationBarI {
  price: number
}

export default function MobileBottomReservationBar({
  price,
}: MobileBottomReservationBarI) {
  return (
    <Paper
      sx={{
        display: 'flex',
        justifyContent: 'space-around',
        alignItems: 'center',
        height: 80,
        position: 'fixed',
        bottom: 0,
        left: 0,
        right: 0,
      }}
      elevation={3}
    >
      <Typography
        display="flex"
        flexDirection="row"
        alignItems="end"
        variant="body1"
        color="primary.main"
      >
        &euro;{price}
        <Typography variant="body1" fontSize={12}>
          /dag
        </Typography>
      </Typography>
      <Button size="large" variant="contained" color="primary">
        Reserveren
      </Button>
    </Paper>
  )
}
