import { Box } from '@mui/system'
import { useEffect, useState } from 'react'
import './TrailerFormFooter.css'

export default function TrailerFormFooter({
  isFirstStep,
  next,
  back,
  isLastStep,
  currentStepIndex,
  trailerType,
  license,
  description,
  trailerStats,
  address,
}) {
  const [disableBtn, setDisableBtn] = useState<boolean>(false)

  useEffect(() => {
    if (
      (currentStepIndex === 1 && trailerType.length <= 0) ||
      (currentStepIndex === 2 && license.length <= 0) ||
      (currentStepIndex === 3 && !description) ||
      (currentStepIndex === 5 &&
        !Object.values(trailerStats).every((x) => !!x)) ||
      (currentStepIndex === 6 && !Object.values(address).every((x) => !!x))
    ) {
      setDisableBtn(true)
    } else {
      setDisableBtn(false)
    }
  }, [
    currentStepIndex,
    trailerType,
    license,
    description,
    trailerStats,
    address,
  ])

  return (
    <Box>
      <div className="trailerFormFooterContainer">
        {!isFirstStep && (
          <button type="button" className="backBtn" onClick={back}>
            Vorige stap
          </button>
        )}
        {!isLastStep && (
          <button
            type="button"
            className={disableBtn ? 'nextBtnDisabled' : 'nextBtn'}
            onClick={next}
            disabled={disableBtn ? true : false}
          >
            {isFirstStep ? 'Laten we beginnen!' : 'Volgende stap'}
          </button>
        )}
      </div>
    </Box>
  )
}
