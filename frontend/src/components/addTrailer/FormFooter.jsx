import { TbVideo } from 'react-icons/tb'
import './FormFooter.css'

export default function FormFooter({
  formstep,
  setFormstep,
  trailerType,
  license,
  description,
}) {
  const checkForUserInput = () => {
    if (formstep === 0) {
      setFormstep(formstep + 1)
    }
    if (formstep === 1) {
      if (!trailerType) {
        return
      } else {
        setFormstep(formstep + 1)
      }
    }
    if (formstep === 2) {
      if (!license) {
        return
      } else {
        setFormstep(formstep + 1)
      }
    }
    if (formstep === 3) {
      if (!license) {
        console.log('Trailertype not null')
      } else {
        setFormstep(formstep + 1)
      }
    }
    if (formstep === 4) {
      if (!description) {
        console.log('Trailertype not null')
      } else {
        setFormstep(formstep + 1)
      }
    }
    if (formstep === 5) {
      if (!license) {
        console.log('Trailertype not null')
      } else {
        setFormstep(formstep + 1)
      }
    }
    if (formstep === 6) {
      if (!license) {
        console.log('Trailertype not null')
      } else {
        setFormstep(formstep + 1)
      }
    }
    if (formstep === 7) {
      if (!license) {
        console.log('Trailertype not null')
      } else {
        setFormstep(formstep + 1)
      }
    }
  }

  return (
    <div className="formFooter">
      {formstep === 0 ? (
        <button className="introductionAction">
          <TbVideo size="20px" />
          <p>Bekijk de uitleg video</p>
        </button>
      ) : (
        <button
          className="secondaryBtn"
          onClick={() => setFormstep(formstep - 1)}
        >
          Vorige stap
        </button>
      )}
      {formstep === 7 ? (
        <button
          className="primaryBtn"
          onClick={() => setFormstep((formstep) => formstep + 1)}
        >
          Aanhanger plaatsen
        </button>
      ) : (
        <button className="primaryBtn" onClick={() => checkForUserInput()}>
          {formstep === 0 ? 'Laten we beginnen!' : 'Volgende stap'}
        </button>
      )}
    </div>
  )
}
