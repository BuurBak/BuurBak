import styles from './Header.module.scss'
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

export default function Header() {
  const [mobileOpen, setMobileOpen] = useState(false)
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
          className={styles.tooblbar}
        >
          <Logo />

          {/* Desktop navigation */}
          <HeaderNavigation />

          {/* Mobile drawer button */}
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={toggleDrawer(true)}
            sx={{ mr: 2, display: { md: 'none' } }}
          >
            <MenuIcon className={styles.menuIcon} />
          </IconButton>
        </Toolbar>
      </AppBar>
      <Box component="nav">
        <SwipeableDrawer
          anchor="right"
          className={styles.drawer}
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
          <DrawerNavigation />
        </SwipeableDrawer>
      </Box>
    </Box>
  )
}
