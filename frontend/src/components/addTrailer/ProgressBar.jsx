import './ProgressBar.css'
import Logo from '../../data/images/logo.svg'
import {
  TbArrowsDiagonal2,
  TbCaravan,
  TbCurrencyEuro,
  TbFileDescription,
  TbGridDots,
  TbId,
  TbLocation,
  TbPolaroid,
  TbUser,
} from 'react-icons/tb'

export default function ProgressBar({ formstep, setFormstep }) {
  return (
    <div className="progressBarContainer">
      <img className="logo" alt="" src={Logo} />
      <div className="progressContainer">
        <div className="progressStepDotActive" onClick={() => setFormstep(0)}>
          <TbCaravan size="22px" />
        </div>
        <div
          className={
            formstep >= 1 ? 'progressStepDotActive' : 'progressStepDot'
          }
          onClick={formstep > 1 ? () => setFormstep(1) : null}
        >
          <TbId size="22px" />
        </div>
        <div
          className={
            formstep >= 2 ? 'progressStepDotActive' : 'progressStepDot'
          }
          onClick={formstep > 2 ? () => setFormstep(2) : null}
        >
          <TbPolaroid size="22px" />
        </div>
        <div
          className={
            formstep >= 3 ? 'progressStepDotActive' : 'progressStepDot'
          }
          onClick={formstep > 3 ? () => setFormstep(3) : null}
        >
          <TbFileDescription size="22px" />
        </div>
        <div
          className={
            formstep >= 4 ? 'progressStepDotActive' : 'progressStepDot'
          }
          onClick={formstep > 4 ? () => setFormstep(4) : null}
        >
          <TbArrowsDiagonal2 size="22px" />
        </div>
        <div
          className={
            formstep >= 5 ? 'progressStepDotActive' : 'progressStepDot'
          }
          onClick={formstep > 5 ? () => setFormstep(5) : null}
        >
          <TbLocation size="22px" />
        </div>
        <div
          className={
            formstep >= 6 ? 'progressStepDotActive' : 'progressStepDot'
          }
          onClick={formstep > 6 ? () => setFormstep(6) : null}
        >
          <TbCurrencyEuro size="22px" />
        </div>
        <div
          className={
            formstep >= 7 ? 'progressStepDotActive' : 'progressStepDot'
          }
          onClick={formstep > 7 ? () => setFormstep(7) : null}
        >
          <TbGridDots size="22px" />
        </div>
        <div
          className={
            formstep >= 8 ? 'progressStepDotActive' : 'progressStepDot'
          }
          onClick={formstep > 8 ? () => setFormstep(8) : null}
        >
          <TbUser size="22px" />
        </div>
      </div>
      <button className="formAction">Opslaan & sluiten</button>
    </div>
  )
}
