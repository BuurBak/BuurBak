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

export default function DrawerNavigation(props) {
  const { user } = useAuth()
  return (
    <Box sx={{ textAlign: 'center' }} {...props}>
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
        {/* Todo open login popup on aanmelden click */}
        {user ? null : (
          <ListItem disablePadding>
            <ListItemButton sx={{ textAlign: 'right' }}>
              <ListItemText primary="Aanmelden" />
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
