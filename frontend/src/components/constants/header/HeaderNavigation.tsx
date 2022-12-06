import {
  Avatar,
  Button,
  IconButton,
  Link,
  Menu,
  MenuItem,
  Stack,
} from '@mui/material'
import { Box } from '@mui/system'
import PersonIcon from '@mui/icons-material/Person'
import { useAuth } from '../../../hooks/use-auth'
import {
  usePopupState,
  bindTrigger,
  bindMenu,
} from 'material-ui-popup-state/hooks'

interface HeaderNavigationProps {
  onLoginRegister: () => void
}

export default function HeaderNavigation({
  onLoginRegister,
}: HeaderNavigationProps) {
  const { user, logout } = useAuth()
  const popupState = usePopupState({ variant: 'popover', popupId: 'account' })

  return (
    <Box sx={{ display: { xs: 'none', md: 'block' } }}>
      <Stack spacing={4} direction="row">
        <Button href="/aanbod" color="secondary">
          Aanbod
        </Button>
        <Button href="/blog" color="secondary">
          Blog
        </Button>
        <Button
          href="/verhuren"
          color="primary"
          variant="contained"
          disableElevation
        >
          Ik wil verhuren
        </Button>
        <Button href="/contact" color="secondary">
          Contact
        </Button>

        {user ? (
          <div>
            <Button
              {...bindTrigger(popupState)}
              color="secondary"
              endIcon={
                user.profile_picture_url ? (
                  <Avatar
                    sizes="small"
                    alt={user.name}
                    src={user.profile_picture_url}
                  />
                ) : (
                  <Avatar sizes="small">
                    <PersonIcon />
                  </Avatar>
                )
              }
            >
              {user.name ? user.name : 'Account'}
            </Button>
            <Menu {...bindMenu(popupState)}>
              <MenuItem onClick={popupState.close}>
                <Link underline="none" href="/account" color="inherit">
                  Mijn account
                </Link>
              </MenuItem>
              <MenuItem
                onClick={() => {
                  popupState.close()
                  logout()
                }}
              >
                Uitloggen
              </MenuItem>
            </Menu>
          </div>
        ) : (
          <Button color="secondary" onClick={onLoginRegister}>
            Aanmelden/ Inloggen
          </Button>
        )}
      </Stack>
    </Box>
  )
}
