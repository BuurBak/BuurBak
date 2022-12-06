import './TrailerFormHeader.css'
import Logo from '../constants/header/Logo'
import {
  Stack,
  Step,
  StepConnector,
  stepConnectorClasses,
  StepIconProps,
  StepLabel,
  Stepper,
} from '@mui/material'
import { styled } from '@mui/material/styles'
import {
  TbCaravan,
  TbCurrencyEuro,
  TbDeviceDesktopAnalytics,
  TbFileDescription,
  TbId,
  TbMapPin,
  TbPolaroid,
  TbStars,
} from 'react-icons/tb'

const ColorlibConnector = styled(StepConnector)(({ theme }) => ({
  [`&.${stepConnectorClasses.alternativeLabel}`]: {
    top: 19,
  },
  [`&.${stepConnectorClasses.active}`]: {
    [`& .${stepConnectorClasses.line}`]: {
      backgroundColor: 'var(--primary)',
    },
  },
  [`&.${stepConnectorClasses.completed}`]: {
    [`& .${stepConnectorClasses.line}`]: {
      backgroundColor: 'var(--primary)',
    },
  },
  [`& .${stepConnectorClasses.line}`]: {
    height: 2,
    border: 0,
    backgroundColor:
      theme.palette.mode === 'dark' ? theme.palette.grey[800] : '#eaeaf0',
    borderRadius: 1,
  },
}))

const ColorlibStepIconRoot = styled('div')<{
  ownerState: { completed?: boolean; active?: boolean }
}>(({ theme, ownerState }) => ({
  backgroundColor:
    theme.palette.mode === 'dark' ? theme.palette.grey[700] : '#ccc',
  zIndex: 1,
  color: '#fff',
  width: 40,
  height: 40,
  display: 'flex',
  borderRadius: '50%',
  justifyContent: 'center',
  alignItems: 'center',
  ...(ownerState.active && {
    backgroundColor: 'var(--primary)',
  }),
  ...(ownerState.completed && {
    backgroundColor: 'var(--primary)',
  }),
}))

function ColorlibStepIcon(props: StepIconProps) {
  const { active, completed, className } = props

  const icons: { [index: string]: React.ReactElement } = {
    1: <TbCaravan />,
    2: <TbId />,
    3: <TbFileDescription />,
    4: <TbPolaroid />,
    5: <TbDeviceDesktopAnalytics />,
    6: <TbMapPin />,
    7: <TbCurrencyEuro />,
    8: <TbStars />,
  }

  return (
    <ColorlibStepIconRoot
      ownerState={{ completed, active }}
      className={className}
    >
      {icons[String(props.icon)]}
    </ColorlibStepIconRoot>
  )
}

export default function TrailerFormHeader({ currentStepIndex }) {
  const steps = [
    'Type',
    'Rijbewijs',
    'Beschrijving',
    'Fotos',
    'Statistieken',
    'Locatie',
    'Prijs',
    'Accessoires',
  ]

  return (
    <div className="trailerFormHeaderContainer">
      <Logo />
      {currentStepIndex !== 0 && currentStepIndex !== 9 && (
        <div className="progressBar">
          <Stack sx={{ width: '100%' }} spacing={4}>
            <Stepper
              alternativeLabel
              activeStep={currentStepIndex - 1}
              connector={<ColorlibConnector />}
            >
              {steps.map((label) => (
                <Step key={label}>
                  <StepLabel StepIconComponent={ColorlibStepIcon} />
                </Step>
              ))}
            </Stepper>
          </Stack>
        </div>
      )}
      <button className="saveFormProgress">Concept opslaan</button>
    </div>
  )
}
