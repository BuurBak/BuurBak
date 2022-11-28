import {
  Box,
  Modal,
  Slide,
  Typography,
  Stack,
  Toolbar,
  IconButton,
} from '@mui/material'
import CloseIcon from '@mui/icons-material/Close'
import { useState } from 'react'
import Login from './Login'
import Register from './Register'

interface LoginRegisterModalProps {
  open: boolean
  onClose: () => void
}

export default function LoginRegisterModal({
  open,
  onClose,
}: LoginRegisterModalProps) {
  const [login, setLogin] = useState(true)

  const handleChange = () => {
    setLogin((prevState) => !prevState)
  }

  return (
    <Modal
      open={open}
      onClose={onClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
      style={{
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        overflow: 'scroll',
      }}
    >
      <Slide direction="up" in={open} mountOnEnter unmountOnExit>
        <Box
          sx={{
            borderRadius: 4,
            margin: 'auto',
            maxWidth: '30rem',
            bgcolor: 'background.default',
          }}
        >
          <Toolbar>
            <IconButton edge="start" onClick={onClose}>
              <CloseIcon />
            </IconButton>
          </Toolbar>

          <Stack spacing={3} sx={{ p: 4, paddingTop: 0 }}>
            {/* Login / Register part */}
            {login ? (
              <Login onClose={onClose} />
            ) : (
              <Register onClose={onClose} />
            )}

            {/* Switch to register / login part */}
            <Typography
              align="center"
              color="primary"
              sx={{
                textDecoration: 'underline',
                '&:hover': {
                  cursor: 'pointer',
                },
              }}
            >
              <div onClick={handleChange}>
                {login
                  ? 'Nog geen account? Klik hier om een account aan te maken'
                  : 'Heeft u al een account? klik hier om in te loggen'}
              </div>
            </Typography>
          </Stack>
        </Box>
      </Slide>
    </Modal>
  )
}
