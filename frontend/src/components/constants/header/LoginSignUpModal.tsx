import { Modal, Box, Typography, Slide } from '@mui/material'

interface LoginSignUpModalProps {
  open: boolean
  onClose: () => void
}

export default function LoginSignUpModal({
  open,
  onClose,
}: LoginSignUpModalProps) {
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
      }}
    >
      <Slide direction="up" in={open} mountOnEnter unmountOnExit>
        <Box
          sx={{
            borderRadius: 4,
            width: 500,
            bgcolor: 'background.default',
            p: 4,
          }}
        >
          <Typography
            id="modal-modal-title"
            variant="h6"
            component="h2"
            textAlign="center"
          >
            Log in of Meld je aan met je e-mail adres
          </Typography>
          {/* <Typography id="modal-modal-description" sx={{ mt: 2 }}>
          Duis mollis, est non commodo luctus, nisi erat porttitor ligula.
        </Typography> */}
        </Box>
      </Slide>
    </Modal>
  )
}
