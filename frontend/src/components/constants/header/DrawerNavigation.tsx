import {
  Box,
  Divider,
  List,
  ListItem,
  ListItemButton,
  ListItemText,
} from '@mui/material'
import { useAuth } from '../../../hooks/use-auth'
import Logo from './Logo'

interface DrawerNavigationProps {
  onLoginRegister: () => void
}

export default function DrawerNavigation({
  onLoginRegister,
}: DrawerNavigationProps) {
  const { user } = useAuth()

  return (
    <Box sx={{ textAlign: 'center' }}>
      <Logo />
      <Divider />
      <List>
        <ListItem disablePadding>
          <ListItemButton href="/aanbod" sx={{ textAlign: 'right' }}>
            <ListItemText primary="Aanbod" />
          </ListItemButton>
        </ListItem>
        <ListItem disablePadding>
          <ListItemButton href="/blog" sx={{ textAlign: 'right' }}>
            <ListItemText primary="Blog" />
          </ListItemButton>
        </ListItem>
        <ListItem disablePadding>
          <ListItemButton href="/verhuren" sx={{ textAlign: 'right' }}>
            <ListItemText primary="Ik wil verhuren" />
          </ListItemButton>
        </ListItem>

        {user ? (
          <ListItem disablePadding>
            <ListItemButton href="/profiel" sx={{ textAlign: 'right' }}>
              <ListItemText primary="Profiel" />
            </ListItemButton>
          </ListItem>
        ) : (
          <ListItem disablePadding>
            <ListItemButton
              sx={{ textAlign: 'right' }}
              onClick={onLoginRegister}
            >
              <ListItemText primary="Aanmelden/ Inloggen" />
            </ListItemButton>
          </ListItem>
        )}

        <ListItem disablePadding>
          <ListItemButton href="/contact" sx={{ textAlign: 'right' }}>
            <ListItemText primary="Contact" />
          </ListItemButton>
        </ListItem>
      </List>
    </Box>
  )
}
