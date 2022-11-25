import { useState } from 'react'
import { Modal, Box, Typography } from '@mui/material'

export default function LoginSignUpButton(props) {
  const [showLogin, setShowLogin] = useState(false)

  return (
    <div {...props}>
      <div
        style={{
          textDecoration: 'none',
          color: 'var(--secondary)',
          cursor: 'pointer',
        }}
        onClick={() => setShowLogin(true)}
      >
        Aanmelden
      </div>
      <Modal
        open={showLogin}
        onClose={() => setShowLogin(false)}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box>
          <Typography id="modal-modal-title" variant="h6" component="h2">
            Text in a modal
          </Typography>
          <Typography id="modal-modal-description" sx={{ mt: 2 }}>
            Duis mollis, est non commodo luctus, nisi erat porttitor ligula.
          </Typography>
        </Box>
      </Modal>
    </div>
  )
}
