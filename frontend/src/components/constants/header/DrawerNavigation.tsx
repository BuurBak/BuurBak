import {
  Box,
  Collapse,
  Divider,
  List,
  ListItemAvatar,
  ListItemButton,
  ListItemText,
} from '@mui/material'
import ExpandLess from '@mui/icons-material/ExpandLess'
import ExpandMore from '@mui/icons-material/ExpandMore'
import { useAuth } from '../../../hooks/use-auth'
import Logo from './Logo'
import { useState } from 'react'
import ProfilePicture from '../../util/ProfilePicture'

interface DrawerNavigationProps {
  onLoginRegister: () => void
}

export default function DrawerNavigation({
  onLoginRegister,
}: DrawerNavigationProps) {
  const { user, logout } = useAuth()
  const [accountOpen, setAccountOpen] = useState(true)

  return (
    <Box sx={{ textAlign: 'center' }}>
      <Logo />
      <Divider />
      <List>
        <ListItemButton
          href="/aanbod"
          sx={{ display: 'flex', justifyContent: 'flex-end' }}
        >
          <ListItemText primary="Aanbod" />
        </ListItemButton>

        <ListItemButton
          href="/blog"
          sx={{ display: 'flex', justifyContent: 'flex-end' }}
        >
          <ListItemText primary="Blog" />
        </ListItemButton>

        <ListItemButton
          href="/verhuren"
          sx={{ display: 'flex', justifyContent: 'flex-end' }}
        >
          <ListItemText primary="Ik wil verhuren" />
        </ListItemButton>

        <ListItemButton
          href="/contact"
          sx={{ display: 'flex', justifyContent: 'flex-end' }}
        >
          <ListItemText primary="Contact" />
        </ListItemButton>

        {user ? (
          <div>
            <ListItemButton
              onClick={() => setAccountOpen(!accountOpen)}
              sx={{ display: 'flex', justifyContent: 'end' }}
            >
              <ListItemAvatar>
                <ProfilePicture user={user} />
              </ListItemAvatar>
              <ListItemText primary={user.name ? user.name : 'Account'} />
              {accountOpen ? <ExpandLess /> : <ExpandMore />}
            </ListItemButton>
            <Collapse in={accountOpen} timeout="auto" unmountOnExit>
              <List component="div">
                <ListItemButton href="/account" sx={{ pl: 4 }}>
                  Mijn account
                </ListItemButton>
                <ListItemButton onClick={logout} sx={{ pl: 4 }}>
                  Uitloggen
                </ListItemButton>
              </List>
            </Collapse>
          </div>
        ) : (
          <ListItemButton
            sx={{ display: 'flex', justifyContent: 'flex-end' }}
            onClick={onLoginRegister}
          >
            <ListItemText primary="Aanmelden/ Inloggen" />
          </ListItemButton>
        )}
      </List>
    </Box>
  )
}
