import {
  AppBar,
  Box,
  IconButton,
  SwipeableDrawer,
  Toolbar,
} from '@mui/material'
import { useState } from 'react'
import Logo from './Logo'
import MenuIcon from '@mui/icons-material/Menu'
import DrawerNavigation from './DrawerNavigation'
import HeaderNavigation from './HeaderNavigation'
import LoginSignUpModal from './LoginSignUpModal'

export default function Header() {
  const [mobileOpen, setMobileOpen] = useState(false)
  const [LoginSignUpModalOpen, setLoginSignUpModelOpen] = useState(false)
  const drawerWidth = 240

  const toggleDrawer =
    (open: boolean) => (event: React.KeyboardEvent | React.MouseEvent) => {
      if (
        event &&
        event.type === 'keydown' &&
        ((event as React.KeyboardEvent).key === 'Tab' ||
          (event as React.KeyboardEvent).key === 'Shift')
      ) {
        return
      }

      setMobileOpen(open)
    }

  const container =
    window !== undefined ? () => window.document.body : undefined

  return (
    <Box>
      <AppBar
        color="default"
        component="nav"
        sx={{
          boxShadow: 'none',
        }}
      >
        <Toolbar
          sx={{
            display: 'flex',
            justifyContent: 'space-between',
            height: '80px',
          }}
        >
          <Logo />

          {/* Desktop navigation */}
          <HeaderNavigation onLogin={() => setLoginSignUpModelOpen(true)} />

          {/* Mobile drawer button */}
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={toggleDrawer(true)}
            sx={{ mr: 2, display: { md: 'none' } }}
          >
            <MenuIcon />
          </IconButton>
        </Toolbar>
      </AppBar>
      <Box component="nav">
        <SwipeableDrawer
          anchor="right"
          container={container}
          variant="temporary"
          open={mobileOpen}
          onClose={toggleDrawer(false)}
          onOpen={toggleDrawer(true)}
          ModalProps={{
            keepMounted: true, // Better open performance on mobile.
          }}
          sx={{
            display: { sm: 'block', md: 'none' },
            '& .MuiDrawer-paper': {
              boxSizing: 'border-box',
              width: drawerWidth,
            },
          }}
        >
          <DrawerNavigation onLogin={() => setLoginSignUpModelOpen(true)} />
        </SwipeableDrawer>
      </Box>
      <LoginSignUpModal
        open={LoginSignUpModalOpen}
        onClose={() => setLoginSignUpModelOpen(false)}
      />
    </Box>
  )
}
