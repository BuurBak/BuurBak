import { Button } from '@mui/material'
import { Box } from '@mui/system'
import { useAuth } from '../../../hooks/use-auth'

export default function HeaderNavigation() {
  const { user } = useAuth()
  return (
    <Box sx={{ display: { xs: 'none', md: 'block' } }}>
      <Button href="/aanbod">Aanbod</Button>
      <Button href="/blog">Blog</Button>
      <Button href="/verhuren">Ik wil Verhuren</Button>
      {/* Todo open login popup on aanmelden click */}
      {user ? null : <Button>Aanmelden</Button>}
      <Button href="/contact">Contact</Button>
    </Box>
  )
}
