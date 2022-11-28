import { Button } from '@mui/material'
import { Box } from '@mui/system'
import { useAuth } from '../../../hooks/use-auth'

interface HeaderNavigationProps {
  onLoginRegister: () => void
}

export default function HeaderNavigation({
  onLoginRegister,
}: HeaderNavigationProps) {
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

      {user ? (
        <Button sx={{ m: margin }} color="secondary">
          Profiel
        </Button>
      ) : (
        <Button sx={{ m: margin }} color="secondary" onClick={onLoginRegister}>
          Aanmelden/ Inloggen
        </Button>
      )}

      <Button sx={{ m: margin }} href="/contact" color="secondary">
        Contact
      </Button>
    </Box>
  )
}
