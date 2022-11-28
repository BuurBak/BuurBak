import TrailerFormFooter from './TrailerFormFooter'
import { useMultistepForm } from './useMultiStepForm'

export default function TrailerForm() {
  const { steps, currentStepIndex, step, isFirstStep, back } = useMultistepForm(
    [<div>one</div>]
  )

  return (
    <div>
      <TrailerFormFooter />
    </div>
  )
}
