import { Button } from '@mui/material'
import { Box } from '@mui/system'
import { useAuth } from '../../../hooks/use-auth'

interface HeaderNavigationProps {
  onLogin: () => void
}

export default function HeaderNavigation({ onLogin }: HeaderNavigationProps) {
  const { user } = useAuth()
  const margin = 1
  return (
    <Box sx={{ display: { xs: 'none', md: 'block' } }}>
      <Button sx={{ m: margin }} href="/aanbod" color="secondary">
        Aanbod
      </Button>
      <Button sx={{ m: margin }} href="/blog" color="secondary">
        Blog
      </Button>
      <Button
        sx={{ m: margin }}
        href="/verhuren"
        color="primary"
        variant="contained"
        disableElevation
      >
        Ik wil Verhuren
      </Button>
      {/* Todo open login popup on aanmelden click */}
      {user ? null : (
        <Button sx={{ m: margin }} color="secondary" onClick={onLogin}>
          Aanmelden
        </Button>
      )}
      <Button sx={{ m: margin }} href="/contact" color="secondary">
        Contact
      </Button>
    </Box>
  )
}
