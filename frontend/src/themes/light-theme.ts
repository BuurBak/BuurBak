import { createTheme, ThemeOptions } from '@mui/material/styles'

const themeOptions: ThemeOptions = {
  palette: {
    primary: {
      main: '#ee7b46',
      contrastText: '#ffffff',
    },
    secondary: {
      main: '#43938f',
    },
    background: {
      default: '#ffffff',
      paper: '#fafafa',
    },
    text: {
      primary: '#373530',
    },
  },
  typography: {
    button: {
      textTransform: 'none',
    },
  },
}

export const theme = createTheme(themeOptions)
